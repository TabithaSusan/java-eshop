package business;

public class Employee {

    private String firstName;
    private String surname;
    private String street;
    private int houseNumber;
    private int postcode;
    private String city;
    private String email;
    private String birthdate;

    public Employee(String firstName, String surname, String street, int houseNumber, int postcode, String city, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.birthdate = birthdate;
    }

    /**
     * Method to fetch the first name of the employee
     * @return string first name of employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to fetch the surname of the employee
     * @return string surname of employee
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method to fetch the Street of the employee
     * @return string street of employee
     */
    public String getStreet() { return street; }

    /**
     * Method to fetch the house number of employee
     * @return int house number of employee
     */
    public int getHouseNumber() { return houseNumber; };

    /**
     * Method to fetch the city of the employee
     * @return string city of employee
     */
    public String getCity() { return city; }

    /**
     * Method to fetch the postcode of the employee
     * @return int postcode of employee
     */
    public int getPostcode() { return postcode; };

    /**
     * Method to fetch the email of the employee
     * @return string email of employee
     */
    public String getEmail() { return email; }

    /**
     * Method to fetch the birthdate of the employee
     * @return string birthdate of employee
     */
    public String getBirthdate() { return birthdate; }


}
