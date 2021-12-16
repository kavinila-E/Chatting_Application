import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	Connection c = null;

	public Connection getConnection() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kavi", "root", "Kavi@967761");
		} catch (Exception e) {
			System.out.println(e);
		}
		return c;
	}

	public static boolean isValid(String s) {

		Pattern p = Pattern.compile("^\\d{10}$");

		Matcher m = p.matcher(s);

		return (m.matches());
	}

	public void signup() throws ClassNotFoundException, SQLException {
		Main m1 = new Main();
		Connection c = getConnection();
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Username:");
		String name = sc.nextLine();

		while (!(name.matches(".*[a-z]{1,}.*") && name.matches(".*[A-Z]{1,}.*") && name.matches(".*[0-9]{1,}.*")
				&& name.matches(".*[@#$()!~%^&|*?.,]{1,}.*"))) {
			System.out.println("Plz give Strong Username");
			name = sc.nextLine();
		}
		while (true) {
			System.out.println("Enter Mobilenum:");
			String mobilenum = sc.next();

			Statement st1 = c.createStatement();
			ResultSet rs = st1.executeQuery("Select * from Cchat2 where mobilenum='" + mobilenum + "'");
			while (rs.next()) {
				String out = rs.getString(3);
				if (out.contains(mobilenum))
					System.out.println("Username or mobilenum already register,plz enter another one");
				m1.signup();

			}
			if (isValid(mobilenum)) {

				try {
					Statement st = c.createStatement();
					st.executeUpdate("Insert into Cchat2(name,mobilenum) values('" + name + "','" + mobilenum + "')");
					System.out.println("Login Successfully");
					AuthenticatedChat AuthorizedUser = new AuthenticatedChat();
					AuthorizedUser.chatting();
				} catch (SQLException e) {
					System.out.println(e);
				}

			}

			else {

				System.out.println("Plz enter the mobile num within 10 digits");

			}

		}

	}

	public void signin() throws SQLException, ClassNotFoundException {
		Main m = new Main();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter UserName:");
		String name = sc.next();
		System.out.println("Enter Mobilenum:");
		Long mobilenum = sc.nextLong();
		Connection c = getConnection();
		Statement st = c.createStatement();
		String out = "";
		Long out1 = null;
		ResultSet rs = st
				.executeQuery("Select * from Cchat2 where name='" + name + "' and mobilenum='" + mobilenum + "'");

		while (rs.next()) {
			out = rs.getString(2);
			out1 = rs.getLong(3);
		}

		if (out.equals(name)) {

			if (out1.equals(mobilenum)) {
				System.out.println("Login Successful");
				AuthenticatedChat AuthorizedUser1 = new AuthenticatedChat();
				AuthorizedUser1.chatting();
				
			}

		}

		else {
			System.out.println("Invalid");
			m.signin();
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Main s2 = new Main();
		Admin ad = new Admin();
		AuthenticatedChat AuthorizedUser = new AuthenticatedChat();
		NonAuthenticatedChat ap = new NonAuthenticatedChat();
		System.out.println("1.ADMIN 2.USER ");
		Scanner s = new Scanner(System.in);
		int userop = s.nextInt();
		if (userop == 1) {
			System.out.println("1.SignUp  2.Login ");
			Scanner sc1 = new Scanner(System.in);
			int s1 = sc1.nextInt();
			if (s1 == 1) {
				ad.signup();
			} else {
				ad.signin();
			}

		}

		else {
			System.out.println("1.Authenticated Chat 2.Non Authenticated Chat ");
			Scanner sc1 = new Scanner(System.in);
			int s1 = sc1.nextInt();
			if (s1 == 2) {

				ap.chatting();
			} else {
				System.out.println("1.SignUp 2.Signin");
				Scanner sc2 = new Scanner(System.in);
				int s3 = sc2.nextInt();
				if (s3 == 1) {
					s2.signup();

				} else {
					s2.signin();

					AuthorizedUser.chatting();
				}
			}
		}
	}

}
C:\Users\KavinilaE\Downloads\apache-maven-3.8.4-bin
%MAVEN_HOME%\bin