package persistence;

import business.Admin;
import business.Customer;
import business.Employee;
import business.FailedLogin;

import java.sql.SQLException;
import java.util.List;

public interface ILoginPersistence {
    public String findCustomerPassHashByEMail(String email) throws SQLException;

    /**
     * Method to fetch hashed password from database based on the mail that has been provided by user
     * @param email provided by user
     * @return the hashed password
     * @throws SQLException
     */
    public String findEmployeePassHashByEMail(String email) throws SQLException;

    /**
     * Method to fetch hashed password from database based on the mail that has been provided by user
     * @param email provided by user
     * @return the hashed password
     * @throws SQLException
     */
    public String findAdminPassHashByEMail(String email) throws SQLException;

    public Customer getCustomerByEMail(String email);
    Employee getEmployeeByEMail(String email);
    Admin getAdminByEMail(String email);
    void insertFailedLogin(String email, String datetime);
    List<FailedLogin> getFailedLogins();
}
/*
* - Persistence muss für Login Customer oder Mitarbeiter in der Datenbank finden und Daten auslesen
* - Orders werden in Liste geschrieben und nicht in der Datenbank gespeichert
* - Zahlungsmethode etc. muss in Datenbank geschrieben werden und auch ausgelesen werden können
*
* */