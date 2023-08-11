package presentation;

import business.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

public class CheckoutView extends JPanel implements ActionListener {
    private JPanel checkoutFrame;
    private JComboBox paymentMethodEntry;
    private JComboBox shippmentMethodEntry;
    private JTable cartTable;
    private JButton buyBtn;
    private JButton cancelBtn;
    private JLabel firstNameLable;
    private JLabel surnameLable;
    private JLabel streetLable;
    private JLabel houseNumberLable;
    private JLabel postcodeLable;
    private JLabel cityLable;
    private JLabel countryLable;
    private AuthorizePassword authorizePassword;
    private CartView cartView;
    private Overview overview;
    private IArticleBusiness b;
    private ILoginBusiness loginBusiness;
    private List<Article> articlesInCart;

    public CheckoutView(IArticleBusiness b, Overview overview, ILoginBusiness loginBusiness) {
        this.loginBusiness = loginBusiness;
        this.b = b;
        this.overview = overview;
        add(checkoutFrame);

        buyBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        Customer currCust = LoginBusiness.getCurrentCust();

        firstNameLable.setText(currCust.getFirstName());
        surnameLable.setText(currCust.getSurname());
        streetLable.setText(currCust.getStreet());
        houseNumberLable.setText(String.valueOf(currCust.getHouseNumber()));
        postcodeLable.setText(String.valueOf(currCust.getPostcode()));
        cityLable.setText(currCust.getCity());
        countryLable.setText(currCust.getCountry());
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==buyBtn) {
            authorizePassword = new AuthorizePassword(overview,loginBusiness);
            overview.switchMainPanel(authorizePassword);

        } else if(e.getSource()==cancelBtn) {
            cartView = new CartView(b, overview, loginBusiness);
            overview.switchMainPanel(cartView);
        }
    }

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

        cartTable = new JTable(data, columnNames) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        cartTable.setAutoCreateRowSorter(true);

    }
}
