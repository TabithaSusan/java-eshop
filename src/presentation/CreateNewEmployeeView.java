package presentation;

import business.Admin;
import business.ILoginBusiness;
import business.IRegistBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewEmployeeView extends JPanel implements ActionListener {
    private JPanel createNewEmployeeFrame;
    private JTextField firstNameEntry;
    private JTextField surnameEntry;
    private JTextField streetEntry;
    private JTextField houseNumberEntry;
    private JTextField postcodeEntry;
    private JTextField cityEntry;
    private JTextField eMailEntry;
    private JButton cancelBtn;
    private JButton createEmployeeBtn;
    private JPasswordField passwordFirstEntry;
    private JPasswordField passwordSecondEntry;
    private AdminOverview adminOverview;
    private Overview overview;
    private IRegistBusiness registBusiness;
    private String firstName;
    private String surname;
    private String street;
    private int houseNumber;
    private int postcode;
    private String city;
    private String eMail;
    private String password;
    private String passwordHash;
    private ILoginBusiness loginBusiness;


    public CreateNewEmployeeView(Overview overview, IRegistBusiness registBusiness, ILoginBusiness loginBusiness) {
        add(createNewEmployeeFrame);

        this.overview = overview;
        this.registBusiness = registBusiness;
        this.loginBusiness = loginBusiness;

        createEmployeeBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
    }

    public boolean checkIfPasswordsAreTheSame(String passwordFirstTry, String passwordSecondTry) {
        System.out.println(passwordFirstTry + "\n" + passwordSecondTry + "\n" + passwordFirstTry.equals(passwordSecondTry));
        return passwordFirstTry.equals(passwordSecondTry);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== createEmployeeBtn) {
            firstName = firstNameEntry.getText();
            surname = surnameEntry.getText();
            street = streetEntry.getText();
            houseNumber = Integer.parseInt(houseNumberEntry.getText());
            postcode = Integer.parseInt(postcodeEntry.getText());
            city = cityEntry.getText();
            eMail = eMailEntry.getText();
            password = String.valueOf(passwordFirstEntry.getPassword());
            passwordHash = String.valueOf(registBusiness.sha256(passwordFirstEntry.toString()));
            if(checkIfPasswordsAreTheSame(String.valueOf(passwordFirstEntry.getPassword()),String.valueOf(passwordSecondEntry.getPassword()))){
                System.out.println(houseNumber);
                registBusiness.createEmployee(firstName, surname, street, houseNumber, postcode, city, eMail, passwordHash);
                JOptionPane.showMessageDialog(this, "Mitarbeiter wurde erfolgreich angelegt", "Erfolg", JOptionPane.PLAIN_MESSAGE);
                adminOverview = new AdminOverview(overview, registBusiness, loginBusiness);
                overview.switchMainPanel(adminOverview);
            }else {
                JOptionPane.showMessageDialog(this, "Die eingegebenen Passwörter sind nicht identisch.", "Passwortfehler", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==cancelBtn) {
            adminOverview = new AdminOverview(overview, registBusiness, loginBusiness);
            overview.switchMainPanel(adminOverview);
        }
    }
}
