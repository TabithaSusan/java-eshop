package persistence;

import business.Customer;
import business.Employee;

import java.util.List;

public interface IRegistPersistence {
    void insertCust(Customer cust);
    void insertEmployee(Employee employee);
    void unlockCustomer(Customer cust );
    List<Customer> getLockedCustomerList();
    //String checkPassword(String password);
}
