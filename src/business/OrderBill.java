package business;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.awt.Font.getFont;


public class OrderBill {
    //This methode has to receive the current Customer (loginBusiness getCurrentCust())
    public static void createOrderBill(Customer cust, List<Article> articles) throws IOException {
        PdfWriter writer = new PdfWriter(new FileOutputStream("orderBill.pdf"));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        pdfDoc.setDefaultPageSize(PageSize.A4);

        float col = 280f;
        float colWidth[] = {col, col};
        Table headerTable = new Table(colWidth);

        headerTable.addCell(new Cell().add(new Paragraph("Rechnung"))
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER));
        headerTable.addCell(new Cell().add(new Paragraph("esop\nShopstraße 560\n77788 EStadt"))
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(10f)
                .setMarginRight(10f)
                .setBorder(Border.NO_BORDER));
        headerTable.setBackgroundColor(WebColors.getRGBColor("#2B5250")).setFontColor(WebColors.getRGBColor("#FFFFFF"));

        float colWidth2[] = {80, 300, 100, 80};
        Table custInfoTable = new Table(colWidth2);

        custInfoTable.addCell(new Cell(0,4).add(new Paragraph("Kunden Information")).setBold().setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph("Vorname")).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph(cust.getFirstName())).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph("Rechnungsnr.")).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph(String.valueOf(System.currentTimeMillis()))).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph("Nachname")).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph(cust.getSurname())).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph("Adresse")).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().add(new Paragraph(cust.getStreet() + " " + cust.getHouseNumber()
        + '\n' + cust.getPostcode() + " " + cust.getCity() + '\n' + cust.getCountry())).setBorder(Border.NO_BORDER));
        custInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER));

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        custInfoTable.addCell(new Cell().add(new Paragraph(date.format(formatter)))
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.BOTTOM).setBorder(Border.NO_BORDER));

        float colWidth3[] = {480, 80};
        Table articleTable = new Table(colWidth3);

        articleTable.addCell(new Cell().add(new Paragraph("Artikelname")).setBorder(Border.NO_BORDER).setBold());
        articleTable.addCell(new Cell().add(new Paragraph("Preis in Euro")).setBorder(Border.NO_BORDER).setBold());

        for(Article article : articles){
            articleTable.addCell(new Cell().add(new Paragraph(article.getArticleName())).setBorder(Border.NO_BORDER));
            articleTable.addCell(new Cell().add(new Paragraph(article.getPrice() + " €")).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        }

        String totalCost = Cart.getTotalWithoutVAT();
        String totalCostWithVAT = Cart.getTotalWithVAT();
        articleTable.addCell(new Cell().add(new Paragraph("Gesamtkosten ohne Mehrwertsteuer")).setBorder(Border.NO_BORDER).setBold());
        articleTable.addCell(new Cell().add(new Paragraph(totalCost + " €")).setBorder(Border.NO_BORDER).setBold().setTextAlignment(TextAlignment.RIGHT));

        articleTable.addCell(new Cell().add(new Paragraph("Gesamtkosten mit Mehrwertsteuer")).setBorder(Border.NO_BORDER).setBold());
        articleTable.addCell(new Cell().add(new Paragraph(totalCostWithVAT + " €")).setBorder(Border.NO_BORDER).setBold().setTextAlignment(TextAlignment.RIGHT));

        doc.add(headerTable);
        doc.add(new Paragraph("\n"));
        doc.add(custInfoTable);
        doc.add(new Paragraph("\n"));
        doc.add(articleTable);

        PdfFont courier = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);

        doc.add(new Paragraph("\n Vielen Dank für Ihren Einkauf!").setFont(courier).setFontColor(WebColors.getRGBColor("#2B5250")).setItalic().setTextAlignment(TextAlignment.RIGHT));

        doc.close();

    }
}
