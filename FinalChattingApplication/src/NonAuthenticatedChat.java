import java.util.Calendar;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.io.IOException;
import java.sql.*;

public class NonAuthenticatedChat{
	
public static void chatting()throws SQLException, ClassNotFoundException
	{
		
		System.out.println("Hi plz Enter your name :");
		
		Scanner sc1=new Scanner(System.in);
		String name1=sc1.nextLine();
		String pattern;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" HH:mm:ss"); 
		LocalDateTime now = LocalDateTime.now();  		
			
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
		LocalDateTime now1 = LocalDateTime.now();  	
		System.out.println("Welcome "+name1+" You can Start now:");
		while(true) {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kavi", "root","Kavi@967761");
		Scanner sc=new Scanner(System.in);
		Statement stmt = con.createStatement();
		String name=sc.nextLine();
	
		//ResultSet rs = stmt.executeQuery("select* from ek2");
		//ResultSet rs = stmt.executeQuery("select a.id,a.ans,b.ans1 from chatbot a INNER JOIN chatbot1 b on a.id=b.id;");
		//ResultSet rs = stmt.executeQuery("select a.id,a.ans1,b.ans1 from fchat a INNER JOIN fchatk b on a.id=b.id;");
		ResultSet rs = stmt.executeQuery("select* from fchat3");
		int f=0;
		

			while (rs.next()) {

				String Result = rs.getString(2);
				
					if(name.contains(Result))
			
					{
				
					System.out.println(rs.getString(3));
					f++;
					break;
				
			        }
					
				
			}


						
		//ResultSet rs1 = stmt.executeQuery("select* from chatbot2");
						
						
			/*ResultSet rs1 = stmt.executeQuery("select a.id,a.ans,b.ans from chatbot a INNER JOIN kavinilachat b on a.id=b.id  ;");
		      while (rs1.next()) {

					String Result1 = rs1.getString(2);
					if(name.contains(Result1))
							
					{
								
					System.out.println(rs1.getString(3));
					f++;
				    break;
								
							        }
									
							}*/
					if(name.contains("time"))	
					{
							 System.out.println(dtf.format(now)); 
							 f++;
					}
							
					if(name.contains("date"))	
					{
							 System.out.println(dtf1.format(now)); 
							 f++;
					}
						
						
					if(f==0) 
					{
						System.out.println("sorry!!!.. I didn't UnderStand");
					}
					
					
			}
		
	}	
					
				
}

			
			
			


