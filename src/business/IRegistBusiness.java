package business;

import java.util.List;

public interface IRegistBusiness {

    void createCust(int id, String firstname, String surname, String street, int housnumber, String city, int postcode,
                           String country, String email, String birthdate, int paymethodeID, int shippingmethodeID, boolean unlocked, String passHash);

    void createEmployee(String firstname, String surname, String street, int housnumber, int postcode, String city,
                        String email, String birthdate);
    String sha256(final String base);
    void unlockCustomer(Customer cust);
    List<Customer> getLockedCustomerList();
    Customer findCustInLockedCustomerList(String surname);
}
