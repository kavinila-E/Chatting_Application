import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class AdminCrud {

	
	Connection c = null;
	public Connection getConnection() {
				try {
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kavi", "root", "Kavi@967761");
				} catch (Exception e) {
					System.out.println(e);
				}
				return c;
	}
			
	public void addques() {

				Connection c = getConnection();
				Scanner sc = new Scanner(System.in);
			    
				System.out.println("Enter ques:");
				String ques = sc.nextLine();
				
				System.out.println("Enter ans:");
				String ans = sc.nextLine();

				try {
					Statement st = c.createStatement();
					st.executeUpdate(
							"Insert into fchat3(ques,ans) values('" +ques+ "','" +ans+ "')");
					System.out.println("Added sucessfully");
					
				} catch (SQLException e) {
					System.out.println(e);
				}

			}
public void addupdate() {
	
	
	
	 Connection c = getConnection(); 
	 try {
	Statement st = c.createStatement();
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	
	ResultSet rs = st.executeQuery("Select * from fchat3" );
	//ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
	//int n=rsmd.getColumnCount();
	//System.out.println("Id\tQuestion\tAnswer");
	System.out.println("Your content is");
	while (rs.next()) {
	System.out.println("\n" + "Id :" + rs.getString(1) + "\t" + "Question"
			+ ": "
		+ rs.getString(2) + "\t" + "answer:" + rs.getString(3) );
	
		//for(int i=1;i<=n;i++)
	//{
	
	//System.out.print(rs.getString(i)+"\n");
	//System.out.println();
	//}
	}
	System.out.println("Enter Id Do you want to Update:");
	int id = sc.nextInt();
	System.out.println("Here update your Question:");
	String ques = sc1.nextLine();

	System.out.println("Here update your Answer");
	String ans = sc1.nextLine();
	
	String sql = ("update fchat3 set ques =?,ans=? where id=? ");
	PreparedStatement pst = c.prepareStatement(sql);
	pst.setString(1, ques);
	pst.setString(2, ans);
	pst.setInt(3, id); 
	pst.executeUpdate();
	System.out.println(" Your question and Answer are Updated successfully");
	} 
	 
catch (SQLException e) {
	System.out.println(e);
	}
	}

public void delete() throws SQLException {
	Connection c = getConnection(); 
	 
	Statement st = c.createStatement();
	Scanner sc = new Scanner(System.in);
	
	ResultSet rs = st.executeQuery("Select * from fchat3" );
	
	System.out.println("Your content is");
	while (rs.next()) {
	System.out.println("\n" + "Id :" + rs.getString(1) + "\t" + "Question"
			+ ": "
		+ rs.getString(2) + "\t" + "answer:" + rs.getString(3) );
	}
	System.out.println("");
	System.out.println("Enter Id Do you want to delete:");
	int id = sc.nextInt();
	
	String sql = ("DELETE FROM fchat3 where id=? ");
	PreparedStatement pst = c.prepareStatement(sql);
	pst.setInt(1,id);
	pst.executeUpdate();
	System.out.println("Deleted sucessfully");
}
	
	public static void main(String[]args) throws SQLException {
		AdminCrud a=new AdminCrud();
		//a.addques();
		a.addupdate();
		//a.delete();
	}
}