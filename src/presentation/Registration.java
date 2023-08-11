package presentation;

import business.IArticleBusiness;
import business.IRegistBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JPanel implements ActionListener{
    private JTextField firstnameEntry;
    private JTextField surnameEntry;
    private JTextField eMailEntry;
    private JButton registrationBtn;
    private JButton cancelBtn;
    private JPasswordField firstPasswordEntry;
    private JPasswordField secondPasswordEntry;
    private JTextField streetEntry;
    private JLabel streetLabel;
    private JTextField housenumberEntry;
    private JLabel housenumberLabel;
    private JTextField zipCodeEntry;
    private JLabel zipcodeLabel;
    private JTextField cityEntry;
    private JLabel cityLabel;
    private JTextField birthdateEntry;
    private ArticleOverview articleOverview;
    private Overview overview;
    private IArticleBusiness b2;
    private IRegistBusiness registBusiness;
    private JPanel regFrame;
    private JTextField countryEntry;
    private JComboBox choosenPaymethod;
    private JLabel Bezahlmethode;
    private JComboBox choosenShippingmethod;
    private String firstname;
    private String surname;
    private String email;
    private String password;
    private String street;
    private int housnumber;
    private int postcode;
    private String city;
    private String country;
    private String birthdate;
    private int paymethodeID;
    private int shippingmethodeID;
    private boolean unlocked;
    private int id;
    private String passHash;


    public Registration(Overview overview, IRegistBusiness b1, IArticleBusiness b2) {
        add(regFrame);
        this.overview = overview;
        this.registBusiness = b1;
        this.b2 = b2;

        registrationBtn.addActionListener(this);
        cancelBtn.addActionListener(this); //TODO currently running into an ERROR since b is null going back to articleoverview l 29


    }



    /**
     * Method to check if the two entered password are the same.
     * @param passwordFirstTry first user entry of password
     * @param passwordSecondTry second user entry of password
     * @return true if the same, false if not
     */
    public boolean checkIfPasswordsAreTheSame(String passwordFirstTry, String passwordSecondTry) {
        System.out.println(passwordFirstTry + "\n" + passwordSecondTry + "\n" + passwordFirstTry.equals(passwordSecondTry));
        return passwordFirstTry.equals(passwordSecondTry);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==registrationBtn) {
            firstname = firstnameEntry.getText();
            surname = surnameEntry.getText();
            email = eMailEntry.getText();
            password = String.valueOf(firstPasswordEntry.getPassword());
            passHash = String.valueOf(registBusiness.sha256(firstPasswordEntry.toString()));
            street = streetEntry.getText();
            housnumber = Integer.parseInt(housenumberEntry.getText());
            postcode = Integer.parseInt(zipCodeEntry.getText());
            city = cityEntry.getText();
            country = countryEntry.getText();
            //TODO for Payment and shipping a fetching from the database is needed. preferable as an array for further usage
            birthdate = birthdateEntry.getText();
            if(checkIfPasswordsAreTheSame(String.valueOf(firstPasswordEntry.getPassword()),String.valueOf(secondPasswordEntry.getPassword()))){
                System.out.println(housnumber);
                registBusiness.createCust(id, firstname, surname, street, housnumber, city, postcode, country, email, birthdate, paymethodeID, shippingmethodeID, unlocked, passHash);
                JOptionPane.showMessageDialog(this, "Du hast dich erfolgreich registriert.", "Erfolg", JOptionPane.PLAIN_MESSAGE);
                articleOverview = new ArticleOverview(b2);
                overview.switchMainPanel(articleOverview);
            }else {
                JOptionPane.showMessageDialog(this, "Die eingegebenen Passwörter sind nicht identisch.", "Passwortfehler", JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(e.getSource()==cancelBtn) {
            articleOverview = new ArticleOverview(b2);
            overview.switchMainPanel(articleOverview);
        }
    }
}
