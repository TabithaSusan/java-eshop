package presentation;

import business.Article;
import business.Cart;
import business.IArticleBusiness;
import business.ILoginBusiness;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

public class CartView extends JPanel implements ActionListener, ChangeListener {
    private JPanel cartFrame;
    private JTable tableForCartContent;
    private JButton buyBtn;
    private JButton deleteFromCartBtn;
    private JButton changeAmountBtn;
    private JButton emptyCartBtn;
    private JLabel totalWithoutVAT;
    private JLabel totalWithVAT;
    private JSpinner amountInCartSpinner;

    private List<Article> articlesInCart;
    private IArticleBusiness b;
    private Overview overview;
    private CheckoutView checkoutView;
    Article selectedArticle;
    ILoginBusiness loginBusiness;

    public CartView(IArticleBusiness b, Overview overview, ILoginBusiness loginBusiness) {
        this.loginBusiness = loginBusiness;
        this.b = b;
        this.overview = overview;
        add(cartFrame);

        buyBtn.addActionListener(this);
        changeAmountBtn.addActionListener(this);
        deleteFromCartBtn.addActionListener(this);
        emptyCartBtn.addActionListener(this);

        amountInCartSpinner.addChangeListener(this);

        totalWithoutVAT.setText(String.valueOf(Cart.getTotalWithoutVAT())); //TODO method to fetch total of cart input
        totalWithVAT.setText(String.valueOf(Cart.getTotalWithVAT())); //TODO method to fetch total with VAT of cart input
    }

    /**
     * Method to create Article Table to display every Article that is saved in the database, through ArticleBusiness all the needed Parameters are provided.
     */
    private void createUIComponents() {

        String[] columnNames = {"Artikelname", "Preis"};
        articlesInCart = Cart.getArticlesInCart();
        String[][] data = new String[articlesInCart.size()][columnNames.length];

        for (int row = 0; row < articlesInCart.size(); row++) {
            for (int column = 0; column < columnNames.length; column++) {
                if (column % 2 == 0) {
                    data[row][column] = articlesInCart.get(row).getArticleName();
                } else {
                    data[row][column] = String.valueOf(articlesInCart.get(row).getPrice());
                }

            }
        }

        tableForCartContent = new JTable(data, columnNames) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        tableForCartContent.setAutoCreateRowSorter(true);

        tableForCartContent.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                onClick(mouseEvent);
            }
        });
    }

    private void onClick (MouseEvent mouseEvent) {
        int row = tableForCartContent.rowAtPoint(mouseEvent.getPoint());
        String articleName = (String) tableForCartContent.getModel().getValueAt(tableForCartContent.convertRowIndexToModel(row),0);
        selectedArticle = b.getArticleByName(articleName);
        if(mouseEvent.getClickCount() == 2 && tableForCartContent.getSelectedRow() != -1) {
            Overview frame = ((Overview) SwingUtilities.getAncestorOfClass(Overview.class, this));
            if (frame != null) {
                frame.switchMainPanel(new ArticleDetailView(row, selectedArticle, b));
            }
        }
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buyBtn) {
            checkoutView = new CheckoutView(b, overview, loginBusiness);
            overview.switchMainPanel(checkoutView);
        } else if (e.getSource()==changeAmountBtn) {
            Cart.addToCart(selectedArticle, (Integer) amountInCartSpinner.getValue());
            overview.switchMainPanel(new CartView(b, overview, loginBusiness));
        } else if (e.getSource()== deleteFromCartBtn) {
            Cart.removeFromCart(selectedArticle);
            overview.switchMainPanel(new CartView(b, overview, loginBusiness));
        } else if (e.getSource()==emptyCartBtn) {
            Cart.emptyCart();
            overview.switchMainPanel(new CartView(b, overview, loginBusiness));
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
