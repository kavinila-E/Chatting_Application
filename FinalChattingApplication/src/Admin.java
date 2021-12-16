import java.util.Scanner;
import java.sql.*;

public class Admin {

	Connection c = null;

	public Connection getConnection() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kavi", "root", "Kavi@967761");
		} catch (Exception e) {
			System.out.println(e);
		}
		return c;
	}

	public void signup() throws SQLException {

		Connection c = getConnection();
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Username:");
		String name = sc.next();

		Statement st1 = c.createStatement();
		ResultSet rs = st1.executeQuery("Select * from CchaT12 where name='" + name + "'");
		while (rs.next()) {
			String out = rs.getString(2);
			if (out.contains(name)) {
				System.out.println("UserName already Exits....plz give another UserName");
				name = sc.next();
			}
		}

		System.out.println("Enter Password:");
		String password = sc.next();
		while (!(password.matches(".*[a-z]{1,}.*") && password.matches(".*[A-Z]{1,}.*")
				&& password.matches(".*[0-9]{1,}.*") && password.matches(".*[@#$()!~%^&|*?.,]{1,}.*")
				&& (!password.contains(" ")))) {
			System.out.println("Plz give Strong Password");
			password = sc.next();

		}

		Statement st2 = c.createStatement();//
		ResultSet rs1 = st1.executeQuery("Select * from CchaT12 where password='" + password + "'");
		while (rs1.next()) {
			String out = rs1.getString(3);
			if (out.contains(password)) {
				System.out.println("Error!!!plz give another password");
				password = sc.next();
			}
		}

		try {
			Statement st = c.createStatement();

			st.executeUpdate("Insert into CchaT12(name,password) values('" + name + "','" + password + "')");
			System.out.println("Welcome to admin portal");
			while (true) {
				System.out.println("1.Do you want to add or 2. update 3.delete ");
				Scanner sc2 = new Scanner(System.in);
				AdminCrud AdminCrud = new AdminCrud();
				int s = sc2.nextInt();
				if (s == 2) {
					AdminCrud.addupdate();

				}
				else if(s == 1) {
					AdminCrud.addques();
				} else {
					AdminCrud.delete();
				}

			}

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void signin() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter UserName:");
		String name = sc.next();
		System.out.println("Enter Password:");
		String password = sc.next();
		Connection c = getConnection();
		Statement st = c.createStatement();
		String out = "";
		String out1 = "";
		ResultSet rs = st
				.executeQuery("Select * from CchaT12 where name='" + name + "' and password='" + password + "'");

		while (rs.next()) {
			out = rs.getString(2);
			out1 = rs.getString(3);
		}

		if (out.equals(name)) {

			if (out1.equals(password)) {
				System.out.println("Login Successful");

				while (true) {
					System.out.println("1.Do you want to add or 2. update  3.delete");
					Scanner sc2 = new Scanner(System.in);
					AdminCrud adminadd1 = new AdminCrud();
					int s = sc2.nextInt();
					if (s == 2) {
						adminadd1.addupdate();

					}
					if (s == 1) {
						adminadd1.addques();
					}

					if (s == 3) {
						adminadd1.delete();
					}

				}

			}

		}

		else
			System.out.println("Invalid One");
		Admin ad = new Admin();
		ad.signin();

	}

	public static void main(String[] args) throws SQLException {
		Admin s2 = new Admin();
		System.out.println("1.signup  2.login ");
		Scanner sc1 = new Scanner(System.in);
		int s1 = sc1.nextInt();
		if (s1 == 1) {
			s2.signup();
		} else {
			s2.signin();
		}

	}

}
