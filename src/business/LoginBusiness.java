package business;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import persistence.ILoginPersistence;

public class LoginBusiness implements ILoginBusiness {

	private ILoginPersistence persistence;
	private static Customer currentCust;
	private static Employee currentEmployee;
	private static Admin currentAdmin;

	public LoginBusiness(ILoginPersistence persistence) {
		this.persistence = persistence;
	}

	/**
	 * Method to fetch the current customer
	 * @return current logged in customer
	 */
	public static Customer getCurrentCust(){
		return currentCust;
	}

	/**
	 * Method to fetch the current employee
	 * @return current logged in employee
	 */
	public static Employee getCurrentEmployee(){
		return currentEmployee;
	}

	/**
	 * Method to fetch the current admin
	 * @return current logged in admin
	 */
	public static Admin getCurrentAdmin(){
		return currentAdmin;
	}

	public static void setCurrentCustNull() {
		currentCust = null;
	}
	public static void setCurrentEmployeeNull() {
		currentEmployee = null;
	}
	public static void setCurrentAdminNull() {
		currentAdmin = null;
	}
	/**
	 * Method to login a customer
	 * @param email string email of customer
	 * @param passHash string hashed password of customer
	 * @return true if hashed password and email are in database
	 * @throws SQLException
	 */

	@Override
	public boolean login(String email, String passHash) throws SQLException {
		String passHashFromDatabase = persistence.findCustomerPassHashByEMail(email);
		if(passHash.equals(passHashFromDatabase)) {
			currentCust = persistence.getCustomerByEMail(email);
			System.out.println(getCurrentCust().getFirstName());
			return true;
		}

	return false;

	}

	/**
	 * Method to check if typed in information to login matched the saved information in database
	 * @param email email of user that tries to login
	 * @param passHash password of the user that tries to login, already hashed from Login/Overview
	 * @return true if matches with an entry, flase if not
	 * @throws SQLException
	 */
	public boolean loginEmployee(String email, String passHash) throws SQLException {
		String passHashFromDatabase = persistence.findEmployeePassHashByEMail(email);
		if(passHash.equals(passHashFromDatabase)) {
			currentEmployee = persistence.getEmployeeByEMail(email);
			System.out.println(getCurrentEmployee().getFirstName());
			return true;
		}
	return false;

	}

	/**
	 * Method to check if typed in information to login matched the saved information in database
	 * @param email email of user that tries to login
	 * @param passhash password of the user that tries to login, already hashed from Login/Overview
	 * @return true if matches with an entry, flase if not
	 * @throws SQLException
	 */
	public boolean loginAdmin(String email, String passhash) throws SQLException {
		String passHashFromDatabase = persistence.findAdminPassHashByEMail(email);
		if(passhash.equals(passHashFromDatabase)) {
			currentAdmin = persistence.getAdminByEMail(email);
			return true;
		}
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu.MM.dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	String datetime = dtf.format(now);
	insertFailedLogin(email,datetime);
	return false;
	}

	/**
	 * Method to hash the password
	 * @param base plain password of login process
	 * @return string of hashed password
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

	public void insertFailedLogin(String email, String datetime){
		persistence.insertFailedLogin(email,datetime);
	}

	public List<FailedLogin> getFailedLogins(){
		return persistence.getFailedLogins();
	}

}
