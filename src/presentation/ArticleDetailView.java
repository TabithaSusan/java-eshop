package presentation;

import business.Article;
import business.Cart;
import business.IArticleBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ArticleDetailView extends JPanel implements ActionListener {
    private JPanel articleDetailView;
    private JButton addToCartBtn;
    private JLabel articleName;
    private JLabel priceWithoutVAT;
    private JLabel priceWithVAT;
    private JLabel specialVAT;
    private JLabel availability;
    private JSpinner setAmountOfArticle;
    private IArticleBusiness business;
    private final Article article;
    private int amountOfArticle;

    public ArticleDetailView (int index, Article article, IArticleBusiness business) {
        this.business = business;
        this.article = article;
        add(articleDetailView);

        articleName.setText(article.getArticleName());
        priceWithoutVAT.setText(String.valueOf(article.getPrice()));
        priceWithVAT.setText(calculatedPriceWithVAT(index));
        availability.setText(isInStock(article, index));

        addToCartBtn.addActionListener(this);
    }

    private String isInStock(Article article, int index) {

        if(article.getStock() != 0) {
            return "Ist Verfügbar";
        }
        return "Ist nicht Verfügbar";
    }

    private String calculatedPriceWithVAT(int index) {
        double VAT = article.getVat();
        double endprice;
        double result;

        endprice = article.getPrice()/100 * VAT;
        result = endprice + article.getPrice();

        DecimalFormat df = new DecimalFormat("#.##");

        return df.format(result);
    }

    private boolean haveSpecialVAT(Article article, int index) {

        //TODO missing special VAT fetching from Database

        specialVAT.setText("Keine abweichende MwSt");
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addToCartBtn) {
            amountOfArticle = (int) setAmountOfArticle.getValue();
            Cart.addToCart(article, amountOfArticle);
            JOptionPane.showMessageDialog(this, amountOfArticle+ " " + article.getArticleName() + " wurde/n dem Warenkorb hinzugefügt", "Erfolg", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
