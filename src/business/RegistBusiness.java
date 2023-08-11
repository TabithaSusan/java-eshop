package business;

import persistence.IRegistPersistence;

import java.security.MessageDigest;
import java.util.List;

public class RegistBusiness implements IRegistBusiness{
    private final IRegistPersistence registPersistence;
    private Customer cust;
    private Employee employee;


    public RegistBusiness(IRegistPersistence registPersistence) {
        this.registPersistence = registPersistence;
    }

    /**
     * Method to create new customer
     * @param id int id will not be used in sql statement and should be set to 0
     * @param firstName string sets the first name of the new customer
     * @param surname string sets the surname of the new customer
     * @param street string sets the street of the new customer
     * @param houseNumber int sets the house number of the new customer
     * @param city string sets the city of the new customer
     * @param postcode int sets the postcode of the new customer
     * @param country string sets the country of the new customer
     * @param email string sets the email of the new customer
     * @param birthdate string sets the birthdate of the new customer
     * @param payMethodeID int sets the pay method ID of the new customer
     * @param shippingMethodeID int sets the shipping method of the new customer
     * @param unlocked boolean sets if the customer is unlocked, default is locked
     * @param passHash string sets the hashed password of the new customer
     */
    public void createCust(int id, String firstName, String surname, String street, int houseNumber, String city, int postcode,
                           String country, String email, String birthdate, int payMethodeID, int shippingMethodeID, boolean unlocked, String passHash) {
        cust = new Customer(id, firstName, surname, street, houseNumber, city, postcode, country, email, birthdate, payMethodeID, shippingMethodeID, unlocked, passHash);
        registPersistence.insertCust(cust);

    }

    /**
     * Method to create a new employee
     * @param firstName string sets the first name of the new employee
     * @param surname string sets the surname of the new employee
     * @param street string sets the street of the new employee
     * @param houseNumber int sets the house number of the new employee
     * @param postcode int sets the postcode of the new employee
     * @param city string sets the city of the new employee
     * @param email string sets the email of the new employee
     * @param birthdate string sets the birthdate of the new employee
     */
    public void createEmployee(String firstName, String surname, String street, int houseNumber, int postcode, String city,
                               String email, String birthdate) {
        employee = new Employee(firstName, surname, street, houseNumber, postcode, city, email);

    }

    /**
     * Method to unlock a new customer
     * @param cust customer to unlocl
     */
    public void unlockCustomer(Customer cust){
        registPersistence.unlockCustomer(cust);
    }

    /**
     * Method to fetch the list of all locked Customers
     * @return java list of locked customers
     */
    public List<Customer> getLockedCustomerList(){
        return registPersistence.getLockedCustomerList();
    }

    /**
     * Method to search for certain customer in the list of locked customers
     * @param surname string surname of searched customer
     * @return customer that has been searched for
     */
    public Customer findCustInLockedCustomerList(String surname){
        Customer cust = null;
        for (Customer c: getLockedCustomerList()) {
            if(c.getSurname().equals(surname)){
                cust = c;
            }
        }
        return cust;
    }

    /**
     * Method to hash the password
     * @param base plain password as string
     * @return string hashed password
     */
    public String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
