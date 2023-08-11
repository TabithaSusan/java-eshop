package persistence;

import business.Customer;
import business.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistPersistence implements IRegistPersistence {
    private final Connection conn;
    int id;
    String firstName;
    String surname;
    String street;
    int houseNumber;
    String city;
    int postcode;
    String country;
    String email;
    String birthdate;
    int payMethodeID;
    int shippingMethodeID;
    boolean unlocked;
    String passHash;

    public RegistPersistence(Connection conn) {

        this.conn = conn;
    }

    /**
     * Method to insert a new customer into the database
     * @param cust the new customer that will be inserted
     */
    public void insertCust(Customer cust) {
        String sql = "INSERT INTO Customers(Firstname, Surname, Street, Housenumber, City, PostCode, Country, EMail, " +
                "Birthdate, Unlocked, PasswordHash) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        if (conn!=null) {
            try{
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, cust.getFirstName());
                pstmt.setString(2, cust.getSurname());
                pstmt.setString(3, cust.getStreet());
                pstmt.setInt(4, cust.getHouseNumber());
                pstmt.setString(5, cust.getCity());
                pstmt.setInt(6, cust.getPostcode());
                pstmt.setString(7, cust.getCountry());
                pstmt.setString(8, cust.getEmail());
                pstmt.setString(9, cust.getBirthdate());
                pstmt.setBoolean(10, cust.getUnlocked());
                pstmt.setString(11,cust.getPassHash());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to insert a new employee into the database
     * @param employee the new employee that will be inserted
     */
    public void insertEmployee(Employee employee) {
        String sql = "INSERT INTO Employees(firstname, surname, street, housnumber, city, postcode, email, birthdate) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        if (conn!=null) {
            try{
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, employee.getFirstName());
                pstmt.setString(2, employee.getSurname());
                pstmt.setString(3, employee.getStreet());
                pstmt.setInt(4, employee.getHouseNumber());
                pstmt.setInt(5, employee.getPostcode());
                pstmt.setString(6, employee.getCity());
                pstmt.setString(7, employee.getEmail());
                pstmt.setString(8, employee.getBirthdate());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Method to unlock and save the state of a customer in the database
     * @param cust the customer that will be unlocked
     */
    public void unlockCustomer(Customer cust) {
        String sql = "UPDATE Customers SET Unlocked = TRUE WHERE ID = ?;";

        if (conn!=null) {
            try{
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, cust.getId());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Fetch the list of all locked customers out of the database
     * @return java list of locked customers
     */
    public List<Customer> getLockedCustomerList(){
        List<Customer> lockedCustomerList = new ArrayList<>();
        String sql = "SELECT * FROM Customers WHERE Unlocked = 0;";
        String sqlCount = "SELECT COUNT(*) AS count FROM Customers WHERE Unlocked = 0;";
        if (conn!=null) {
            try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                Statement stmtCount = conn.createStatement();
                ResultSet rsCount = stmtCount.executeQuery(sqlCount);
                int count = rsCount.getInt("count");

                for(int i = 1; i <= count; i++){
                    while (rs.next()) {
                        id = rs.getInt("ID");
                        firstName = rs.getString("Firstname");
                        surname = rs.getString("Surname");
                        street = rs.getString("Street");
                        houseNumber = rs.getInt("Housenumber");
                        city = rs.getString("City");
                        postcode = rs.getInt("PostCode");
                        country = rs.getString("Country");
                        email = rs.getString("EMail");
                        birthdate = rs.getString("Birthdate");
                        passHash = rs.getString("PasswordHash");
                        unlocked = rs.getBoolean("Unlocked");
                        payMethodeID = rs.getInt("PaymethodeID");

                        Customer cust = new Customer(id, firstName, surname, street, houseNumber, city, postcode,
                                country, email, birthdate, payMethodeID, shippingMethodeID, unlocked, passHash);
                        lockedCustomerList.add(cust);
                    }

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return lockedCustomerList;
    }

}
