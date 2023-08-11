package business;

public class Admin {

    private int id;
    private String firstName;
    private String surname;

    public Admin(int id, String firstName, String surname) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     * Method to return the id from the signed in Admin of the session
     * @return id of signed in Admin
     */
    public int getId() {
        return id;
    }

    /**
     * Method to return the first name of the signed in Admin of the session
     * @return first name of the signed in Admin
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to return the surname of the signed in Admin of the session
     * @return surname of the signed in Admin
     */
    public String getSurname() {
        return surname;
    }

}
