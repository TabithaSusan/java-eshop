package presentation;

import business.Customer;
import business.IArticleBusiness;
import business.ILoginBusiness;
import business.LoginBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizePassword extends JPanel implements ActionListener {
    private JPanel authorizeFrame;
    private JPasswordField passwordEntry;
    private JButton authorizeBuyBtn;
    private JButton cancelBtn;
    private Overview overview;
    private OrderCompleteView orderCompleteView;
    private CartView cartView;
    private IArticleBusiness articleBusiness;
    private ILoginBusiness loginBusiness;
    public AuthorizePassword(Overview overview, ILoginBusiness loginBusiness) {
        add(authorizeFrame);
        this.loginBusiness = loginBusiness;
        this.overview = overview;

        authorizeBuyBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==authorizeBuyBtn) {
            if(checkPassword()==true) {
                orderCompleteView = new OrderCompleteView(overview, articleBusiness);
                overview.switchMainPanel(orderCompleteView);
            } else {
                JOptionPane.showMessageDialog(this, "Das eingegeben Passwort ist nicht korrekt", "Passwortfehler", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==cancelBtn) {
            cartView = new CartView(articleBusiness,overview, loginBusiness);
            overview.switchMainPanel(cartView);
        }
    }

    private boolean checkPassword() {

        String hashedPasswort = loginBusiness.sha256(String.valueOf(passwordEntry.getPassword()));
        if(hashedPasswort.equals(LoginBusiness.getCurrentCust().getPassHash())) {
            return true;
        }
        return false;
    }
}
