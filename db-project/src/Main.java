import java.sql.Connection;
import java.sql.SQLException;

public class Main{
	
	public static DatabaseConnection Dbconn;
	public static Connection conn = null;
	
	public static void main(String[] args) {
		
		Dbconn = new DatabaseConnection();
	
		try {
			conn = Dbconn.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		
		new MakeGUI();	
	}

}
