package business;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Order> listOrders = new ArrayList<>();
    private int id;
    private String firstName;
    private String surname;
    private String street;
    private int houseNumber;
    private String city;
    private int postcode;
    private String country;
    private String email;
    //TODO: birthdate als Date einlesen bzw. speichern
    private String birthdate;
    private int payMethodeID;
    private int shippingMethodID;
    private boolean unlocked;
    private String passHash;


    public Customer(int id, String firstName, String surname, String street, int houseNumber, String city, int postcode,
                    String country, String email, String birthdate, int payMethodeID, int shippingMethodID, boolean unlocked, String passHash) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.email = email;
        this.birthdate = birthdate;
        this.payMethodeID = payMethodeID;
        this.shippingMethodID = shippingMethodID;
        this.unlocked = unlocked;
        this.passHash = passHash;

    }

    /**
     * Method to fetch the ID of the customer
     * @return int ID of customer
     */
    public int getId() { return id; }

    /**
     * Method to fetch the first name of the customer
     * @return string first name of customer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to fetch the surname of the customer
     * @return string surname of customer
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method to fetch the street of the customer
     * @return string street of customer
     */
    public String getStreet() { return street; }

    /**
     * Method to fetch the house number of the customer
     * @return int house number of customer
     */
    public int getHouseNumber() { return houseNumber; };

    /**
     * Method to fetch the city of the customer
     * @return string city of customer
     */
    public String getCity() { return city; }

    /**
     * Method to fetch the postcode of the customer
     * @return int postcode of customer
     */
    public int getPostcode() { return postcode; };

    /**
     * Method to fetch the country of the customer
     * @return string country of the customer
     */
    public String getCountry() { return country; }

    /**
     * Method to fetch the email of the customer
     * @return string email of customer
     */
    public String getEmail() { return email; }

    /**
     * Method to fetch the birthdate of the customer
     * @return string birthday of customer
     */
    public String getBirthdate() { return birthdate; }

    /**
     * Method to fetch the pay method ID of the customer
     * @return int pay method ID of customer
     */
    public int getPayMethodeID() { return payMethodeID; }

    /**
     * Method to fetch the shipping method ID of the customer
     * @return int shipping method ID of customer
     */
    public int getShippingMethodID() { return shippingMethodID; }

    /**
     * Method to fetch wether the customer is unlocked or not
     * @return boolean if unlocked or not
     */
    public Boolean getUnlocked() { return unlocked; }

    /**
     * Method to fetch the hashed password of the customer
     * @return string of hashed password of customer
     */
    public String getPassHash() { return passHash; }


}
