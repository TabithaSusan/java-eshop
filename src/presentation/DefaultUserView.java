package presentation;

import business.IArticleBusiness;
import business.ILoginBusiness;
import business.IRegistBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DefaultUserView extends JPanel implements ActionListener {
    private JPanel defaultUserFrame;
    private JTextField emailEntry;
    private JPasswordField passwordEntry;
    private JButton loginBtn;
    private JButton registrationBtn;
    private final Overview overview;
    private Registration registrationWindow;
    private CustomerView customerView;
    private EmployeeView employeeView;
    private AdminView adminView;
    private ILoginBusiness loginBusiness;
    private IRegistBusiness registBusiness;
    private IArticleBusiness articleBusiness;
    private int userClassID;

    public DefaultUserView(ILoginBusiness loginBusiness, Overview overview, IRegistBusiness registBusiness, IArticleBusiness articleBusiness) {
        this.registBusiness = registBusiness;
        this.articleBusiness = articleBusiness;
        this.overview = overview;
        add(defaultUserFrame);

        this.loginBusiness = loginBusiness;

        loginBtn.addActionListener(this);
        registrationBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==registrationBtn) { //Listening to Registration Button
            registrationWindow = new Registration(overview, registBusiness, articleBusiness);
            overview.switchMainPanel(registrationWindow);
        } else if(e.getSource()==loginBtn) { //Listening to Registration Button

            String username = emailEntry.getText();
            String hashedPassword = loginBusiness.sha256(String.valueOf(passwordEntry.getPassword()));

            boolean success = false;

            try {
                success = loginBusiness.login(username, hashedPassword); //Will try to login as user
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if(!success) {
                try {
                    loginAsEmployee(username, hashedPassword); //if not successfully, then try to login as employee
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Die Anmeldung war erfolgreich.", "Erfolg", JOptionPane.PLAIN_MESSAGE);
                userClassID =1;
                setUserClassID(userClassID);
                customerView = new CustomerView(overview,articleBusiness,loginBusiness,registBusiness);
                overview.switchUserPanel(customerView);
            }
        }
    }

    public void loginAsEmployee(String username, String hashedPassword) {

        boolean success = false;

        try {
            success = loginBusiness.loginEmployee(username, hashedPassword);
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        if (!success) {
            try {
                loginAsAdmin(username, hashedPassword); //if not successfully, then try to login as admin
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Die Anmeldung war erfolgreich.", "Erfolg", JOptionPane.PLAIN_MESSAGE);
            userClassID=2;
            setUserClassID(userClassID);
            employeeView = new EmployeeView(overview, registBusiness, loginBusiness,articleBusiness);
            overview.switchUserPanel(employeeView);
        }
    }

    public void loginAsAdmin(String username, String hashedPassword) {

        boolean success = false;

        try {
            success = loginBusiness.loginAdmin(username, hashedPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!success) {
            JOptionPane.showMessageDialog(this, "Die Anmeldung ist fehlgeschlagen", "Fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Die Anmeldung war erfolgreich", "Erfolg", JOptionPane.PLAIN_MESSAGE);
            userClassID=3;
            setUserClassID(userClassID);
            adminView = new AdminView(overview, registBusiness, loginBusiness, articleBusiness);
            overview.switchUserPanel(adminView);
        }
    }

    int getUserClassID() {
        return userClassID;
    }

    private void setUserClassID(int userClassID) {
        this.userClassID = userClassID;
    }

}
