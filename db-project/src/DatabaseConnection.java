import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

import javax.swing.JTextArea;

public class DatabaseConnection {
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "db1993";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "Company";
	
	/** The name of the table we are testing with */
	//private final String tableName = "customer";
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		
		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}
	
	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public boolean executeQuery(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeQuery(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public Object[][] show(Connection conn, String command, JTextArea FeedbackText, int ColNum, String[] ColNames) throws SQLException {
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(command); // This will throw a SQLException if it fails
	        
	        // Find RowNum
	        int RowNum = 0;
	        if (rs != null){
	          rs.beforeFirst();
	          rs.last();
	          RowNum = rs.getRow();
	          rs.beforeFirst();
	        }
	        Object[][] TableData = new Object[RowNum][ColNum];
	        
	        // Find TableData
	        int i = 0;
	        while(rs.next()){
	        	for(int j = 0; j < ColNum; j++){
	        		TableData[i][j] = rs.getString(ColNames[j]);
	        	}
	        	i++;
		    }
	        return TableData;
	    } finally {
	    	// These will run whether we throw an exception or not
	    	if (rs != null) {
                rs.close();
            }
	        if (stmt != null) {
	        	stmt.close();
	        }
	    }
	}
}
