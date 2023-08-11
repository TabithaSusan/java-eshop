package business;

import java.sql.SQLException;
import java.util.List;

public interface ILoginBusiness {
    boolean login(String email, String passHash) throws SQLException; // allgemeinere exception

    /**
     * Method to check if typed in information to login matched the saved information in database
     * @param email email of user that tries to login
     * @param passHash password of the user that tries to login, already hashed from Login/Overview
     * @return true if matches with an entry, flase if not
     * @throws SQLException
     */
    boolean loginEmployee(String email, String passHash) throws SQLException;

    /**
     * Method to check if typed in information to login matched the saved information in database
     * @param email email of user that tries to login
     * @param passHash password of the user that tries to login, already hashed from Login/Overview
     * @return true if matches with an entry, flase if not
     * @throws SQLException
     */
    boolean loginAdmin(String email, String passHash) throws SQLException;

    /**
     * Method to hash the password so that the plain password of the login process can be compared to the hashed password that has been saved in the database table Costumer
     * @param base plain password of login process
     * @return will return the hashed String of base
     */
    public String sha256(final String base);
    List<FailedLogin> getFailedLogins();
}
