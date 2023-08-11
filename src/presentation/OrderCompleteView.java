package presentation;

import business.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import static business.OrderBill.createOrderBill;

public class OrderCompleteView extends JPanel implements ActionListener {
    private JPanel orderCompleteFrame;
    private JButton createBillBtn;
    private JButton backHomeBtn;
    private ArticleOverview articleOverview;
    private IArticleBusiness articleBusiness;
    private Overview overview;
    private Customer customer;

    public OrderCompleteView(Overview overview, IArticleBusiness articleBusiness) {

        customer = LoginBusiness.getCurrentCust();
        this.overview = overview;
        this.articleBusiness = articleBusiness;

        createBillBtn.addActionListener(this);
        backHomeBtn.addActionListener(this);
        add(orderCompleteFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createBillBtn) {
            try {
                createOrderBill(customer,Cart.getArticlesInCart());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==backHomeBtn) {
            articleOverview = new ArticleOverview(articleBusiness);
            overview.switchMainPanel(articleOverview);
        }
    }
}
