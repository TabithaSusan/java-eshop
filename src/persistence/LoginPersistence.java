package persistence;

import business.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginPersistence implements ILoginPersistence {

	private final Connection conn;

	public LoginPersistence(Connection conn) {
		this.conn = conn;
	}

	public String findCustomerPassHashByEMail(String email) throws SQLException {
		String passHash = null;
		String sql = "SELECT * FROM Customers WHERE EMail = ?";

		if (conn!=null) {
			try {
				System.out.println("try" );
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				passHash = rs.getString("PasswordHash");
				System.out.println("passHash =" + passHash);
			} catch (SQLException e) {
				System.out.println("catch LoginPersistence" );
				System.out.println(e.getMessage());
			}
		}
		return passHash;
	}

	/**
	 * Method to fetch hashed password from database based on the mail that has been provided by user
	 * @param email provided by user
	 * @return the hashed password
	 * @throws SQLException
	 */
	public String findEmployeePassHashByEMail(String email) throws SQLException {
		String passHash = null;
		String sql = "SELECT * FROM Employees WHERE EMail = ?";

		if (conn!=null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				passHash = rs.getString("PasswordHash");
				System.out.println("passHash = " + passHash);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return passHash;
	}

	/**
	 * Method to fetch hashed password from database based on the mail that has been provided by user
	 * @param email provided by user
	 * @return the hashed password
	 * @throws SQLException
	 */
	public String findAdminPassHashByEMail(String email) throws SQLException{
		String passHash = null;
		String sql = "SELECT * FROM Admins WHERE EMail =?";

		if (conn!=null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				passHash = rs.getString("PasswordHash");
				System.out.println("passHash = " + passHash);
			} catch (SQLException e){
				System.out.println(e.getMessage());
			}

		}
		return passHash;
	}

	/**
	 * Method to fetch customer by the saved email in database
	 * @param email string of email that will be searched with
	 * @return customer that has been searched for
	 */
	public Customer getCustomerByEMail(String email){
		String sql = "SELECT * FROM Customers WHERE EMail =?";
		Customer cust = null;
		if (conn!=null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				int id = rs.getInt("id");
				String firstName = rs.getString("Firstname");
				String surname = rs.getString("Surname");
				String street = rs.getString("Street");
				int houseNumber = rs.getInt("housenumber");
				String city = rs.getString("City");
				int postcode = rs.getInt("Postcode");
				String country = rs.getString("Country");
				String birthdate = rs.getString("Birthdate");
				int payMethodeID = rs.getInt("PaymethodeID");
				int shippingMethodeID = rs.getInt("ShippingmethodeID");
				boolean unlocked = rs.getBoolean("Unlocked");
				String passHash = rs.getString("PasswordHash");
				cust = new Customer(id, firstName, surname, street, houseNumber, city, postcode, country, email,
						birthdate, payMethodeID, shippingMethodeID, unlocked, passHash);
			} catch (SQLException e){
				System.out.println(e.getMessage());
			}

		}
		return cust;
	}

	/**
	 * Method to fetch employee by the saved email in the database
	 * @param email string of email that will be searched with
	 * @return employee that has been searched for
	 */
	public Employee getEmployeeByEMail(String email){
		String sql = "SELECT * FROM Employees WHERE EMail =?";
		Employee employee = null;
		if (conn!=null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				String firstName = rs.getString("FirstName");
				String surname = rs.getString("LastName");
				String street = rs.getString("Street");
				int houseNumber = rs.getInt("Housenumber");
				String city = rs.getString("City");
				int postcode = rs.getInt("Postcode");
				employee = new Employee(firstName, surname, street, houseNumber, postcode, city, email);
			} catch (SQLException e){
				System.out.println(e.getMessage());
			}

		}
		return employee;
	}

	/**
	 * Method to fetch admin by the saved email in the database
	 * @param email string of email that will be searched with
	 * @return admin tact has been searched for
	 */
	public Admin getAdminByEMail(String email){
		String sql = "SELECT * FROM Admins WHERE EMail =?";
		Admin admin = null;
		if (conn!=null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				int id = rs.getInt("AdminID");
				String firstName = rs.getString("FirstName");
				String surname = rs.getString("LastName");
				admin = new Admin(id, firstName, surname);
			} catch (SQLException e){
				System.out.println(e.getMessage());
			}

		}
		return admin;
	}

	public void insertFailedLogin(String email, String datetime) {
		String sql = "INSERT INTO FailedLogins (Datetime, EMail) VALUES (?,?)";
		if(conn!=null){
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				pstmt.setString(1, datetime);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				pstmt.setString(2, email);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public List<FailedLogin> getFailedLogins() {
		List<FailedLogin> failedLoginsList = new ArrayList<>();

		String datetime;
		String email;
		String sql = "SELECT * FROM FailedLogins";
		String sqlCount = "SELECT COUNT(*) AS count FROM FailedLogins;";

		if (conn != null) {
			try {
				Statement stmtCount = conn.createStatement();
				ResultSet rsCount = stmtCount.executeQuery(sqlCount);
				int count = rsCount.getInt("count");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				for (int i = 1; i <= count; i++) {
					while (rs.next()) {
						datetime = rs.getString("Datetime");
						email = rs.getString("EMail");
						FailedLogin failedLogin = new FailedLogin(datetime, email);
						failedLoginsList.add(failedLogin);
					}

				}

			} catch (SQLException e) {
				System.out.println("catch ArticlePersistence");
				System.out.println(e.getMessage());
			}
		}
		return failedLoginsList;
	}

}
