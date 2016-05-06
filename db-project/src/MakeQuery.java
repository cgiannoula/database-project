import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class MakeQuery implements ActionListener{
	private DatabaseConnection Dbconn;
	private Connection conn;
	
	private JButton ExecuteButton;
	private JTextArea FeedbackText;
	public JTable Table;
	private int ColNum; // Number of Columns of the array
	private String[] ColNames;
	private String QueryString;
	
	private ButtonGroup group;
	private JRadioButton jRadioButton1;
	private JRadioButton jRadioButton2;
	private JRadioButton jRadioButton3;
	private JRadioButton jRadioButton4;
	private JRadioButton jRadioButton5;
	private JRadioButton jRadioButton6;
	private JRadioButton jRadioButton7;
	private JRadioButton jRadioButton8;
	private JRadioButton jRadioButton9;
	private JRadioButton jRadioButton10;
	private JRadioButton jRadioButton11;
	private JRadioButton jRadioButton12;
	private JRadioButton jRadioButton13;
	private JRadioButton jRadioButton14;
	private JRadioButton jRadioButton15;
	private JRadioButton jRadioButton16;
	
	public MakeQuery(){
		this.Dbconn = Main.Dbconn;
		this.conn = Main.conn;
		
		
		JFrame frame = new JFrame();
		frame.setSize(800, 700);
		frame.setLocation(200, 300);
		
		JPanel BasicPan = new JPanel();// FlawLayout default in BasicPan
		BasicPan.setBackground(new Color(222, 239, 255));
		
		
		JPanel TablePanel = new JPanel();
		TablePanel.setLayout(new GridLayout(0, 1)); // Layout Manager to have 2
													// elements in one line
		TablePanel.setSize(100, 10 * 15);
		
	
		group = new ButtonGroup();
		jRadioButton1 = new JRadioButton("Query 1: Show private owners with their properties");
		jRadioButton2 = new JRadioButton("Query 2: Show business owners with their properties");
		jRadioButton3 = new JRadioButton("Query 3: Show all the clients with their contracts and rent");
		jRadioButton4 = new JRadioButton("Query 4: Count employees and show sumary of salary, average salary and max salary");
		jRadioButton5 = new JRadioButton("Query 5: Show how many contracts each client has");
		jRadioButton6 = new JRadioButton("Query 6: Show how many clients each employee has registered");
		jRadioButton7 = new JRadioButton("Query 7: Show how many properties each private owner has");
		jRadioButton8 = new JRadioButton("Query 8: Show how many properties each business owner has");
		jRadioButton9 = new JRadioButton("Query 9: Show employees in descending order by salary");
		jRadioButton10 = new JRadioButton("Query 10: Show all the properties, in ascending order by their rent, which don't have any contracts");
		jRadioButton11 = new JRadioButton("Query 11: Show the business owners who have more than 3 properties");
		jRadioButton12 = new JRadioButton("Query 12: Show the private owners who have more than 3 properties");
		jRadioButton13 = new JRadioButton("Query 13: Show the clients who have more than 3 contracts");
		jRadioButton14 = new JRadioButton("Query 14: Show the employees who have salary more than the average salary");
		jRadioButton15 = new JRadioButton("Query 15: Show the clients who have not any contract");
		jRadioButton16 = new JRadioButton("Query 16: Show visits from all clients to all properties");
		
		group.add(jRadioButton1);
		group.add(jRadioButton2);
		group.add(jRadioButton3);
		group.add(jRadioButton4);
		group.add(jRadioButton5);
		group.add(jRadioButton6);
		group.add(jRadioButton7);
		group.add(jRadioButton8);
		group.add(jRadioButton9);
		group.add(jRadioButton10);
		group.add(jRadioButton11);
		group.add(jRadioButton12);
		group.add(jRadioButton13);
		group.add(jRadioButton14);
		group.add(jRadioButton15);
		group.add(jRadioButton16);
		
		
		TablePanel.add(jRadioButton1);
		//JLabel Label1 = new JLabel("Join private owners with their properties");
		//TablePanel.add(Label1);
		TablePanel.add(jRadioButton2);
		//JLabel Label2 = new JLabel("Join business owners with their properties");
		//TablePanel.add(Label2);
		TablePanel.add(jRadioButton3);
		//JLabel Label3 = new JLabel("Join clients with their contracts and rent");
		//TablePanel.add(Label3);
		TablePanel.add(jRadioButton4);
		//JLabel Label4 = new JLabel("Count employees and show sumary of salary, average salary and max salary");
		//TablePanel.add(Label4);
		TablePanel.add(jRadioButton5);
		//JLabel Label5 = new JLabel("Show how many contracts each client has");
		//TablePanel.add(Label5);
		TablePanel.add(jRadioButton6);
		//JLabel Label6 = new JLabel("Show how many clients each employee has registered");
		//TablePanel.add(Label6);
		TablePanel.add(jRadioButton7);
		//JLabel Label7 = new JLabel("Show how many properties each private owner has");
		//TablePanel.add(Label7);
		TablePanel.add(jRadioButton8);
		//JLabel Label8 = new JLabel("Show how many properties each business owner has");
		//TablePanel.add(Label8);
		TablePanel.add(jRadioButton9);
		//JLabel Label9 = new JLabel("Show employees in descending order");
		//TablePanel.add(Label9);
		TablePanel.add(jRadioButton10);
		//JLabel Label10 = new JLabel("Show all the properties which have not contract in ascending order by their rent");
		//TablePanel.add(Label10);
		TablePanel.add(jRadioButton11);
		//JLabel Label11 = new JLabel("Show the business owners who have more than 5 properties");
		//TablePanel.add(Label11);
		TablePanel.add(jRadioButton12);
		//JLabel Label12 = new JLabel("Show the private owners who have more than 5 properties");
		//TablePanel.add(Label12);
		TablePanel.add(jRadioButton13);
		//JLabel Label13 = new JLabel("Show the clients who have more than 5 contracts");
		//TablePanel.add(Label13);
		TablePanel.add(jRadioButton14);
		//JLabel Label14 = new JLabel("Show the employees who have salary more than the average salary");
		//TablePanel.add(Label14);
		TablePanel.add(jRadioButton15);
		//JLabel Label15 = new JLabel("Show the clients who have not any contract");
		//TablePanel.add(Label15);
		TablePanel.add(jRadioButton16);
		
	
		BasicPan.add(TablePanel);
		
		
		Table = new JTable();
		JScrollPane ScrollPane1 = new JScrollPane(Table);

		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// This disables the horizontal cell resize
		
		ScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollPane1.setPreferredSize(new Dimension(500, 100));
		
		BasicPan.add(ScrollPane1);
		
		
		ExecuteButton = new JButton();
		ExecuteButton.setText("Execute Query");
		ExecuteButton.addActionListener(this);
		BasicPan.add(ExecuteButton);
		
		
		FeedbackText = new JTextArea();
		FeedbackText.setEditable(false);
		JScrollPane ScrollPane2 = new JScrollPane(FeedbackText);
		ScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollPane2.setPreferredSize(new Dimension(380, 100));
		BasicPan.add(ScrollPane2);

		frame.add(BasicPan);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(jRadioButton1.isSelected()){
			ColNum = 5;
			String[] temp1 = { "SSN", "FirstName", "LastName", "PropertyRegistrationNo", "Rent"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT owners.SSN, private_owners.FirstName, private_owners.LastName, properties.PropertyRegistrationNo , properties.Rent FROM owners JOIN properties JOIN private_owners ON owners.SSN = properties.OwnerSSN AND private_owners.SSN = owners.SSN" ;
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
		       
			} catch (SQLException e1) {
				System.out.println("ERROR: Could not show the table");
				FeedbackText.append("ERROR: Could not show the table\n");
				e1.printStackTrace();
				//return;
			}
			
		}else if (jRadioButton2.isSelected()){
			ColNum = 4;
			String[] temp1 = { "SSN", "BusinessName", "PropertyRegistrationNo", "Rent"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT owners.SSN, business_owners.BusinessName, properties.PropertyRegistrationNo, properties.Rent FROM owners JOIN properties JOIN business_owners ON owners.SSN = properties.OwnerSSN AND business_owners.SSN = owners.SSN" ;
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
		       
			} catch (SQLException e1) {
				System.out.println("ERROR: Could not show the table");
				FeedbackText.append("ERROR: Could not show the table\n");
				e1.printStackTrace();
				//return;
			}
		}else if (jRadioButton3.isSelected()){
			ColNum = 7;
			String[] temp1 = { "FirstName", "LastName", "Rent", "PropertyRegistrationNo", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT clients.FirstName, clients.LastName, contracts.Rent, properties.PropertyRegistrationNo, properties.Addr_StreetName, properties.Addr_StreetNo, properties.Addr_PostalCode FROM clients JOIN properties JOIN contracts ON contracts.PropertyRegistrationNo = properties.PropertyRegistrationNo AND contracts.ClientRegistrationNo = clients.ClientRegistrationNo" ;
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
		       
			} catch (SQLException e1) {
				System.out.println("ERROR: Could not show the table");
				FeedbackText.append("ERROR: Could not show the table\n");
				e1.printStackTrace();
				//return;
			}
		}else if(jRadioButton4.isSelected()){

			ColNum = 4;
			String[] temp1 = { "COUNT(*)", "SUM(Salary)", "AVG(Salary)", "MAX(Salary)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT COUNT(*), SUM(Salary), AVG(Salary), MAX(Salary) FROM employees" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
				//return;
			}
		
		}else if(jRadioButton5.isSelected()){

			ColNum = 4;
			String[] temp1 = { "ClientRegistrationNo", "FirstName", "LastName", "COUNT(*)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT clients.ClientRegistrationNo, clients.FirstName, clients.LastName ,COUNT(*) FROM clients, contracts WHERE clients.ClientRegistrationNo = contracts.ClientRegistrationNo GROUP BY clients.ClientRegistrationNo" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
				//return;
			}
		}else if(jRadioButton6.isSelected()){
			ColNum = 4;
			String[] temp1 = { "SSN", "FirstName", "LastName", "COUNT(*)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT employees.SSN, employees.FirstName, employees.LastName ,COUNT(*) FROM employees, clients WHERE employees.SSN = clients.RegisteredBy GROUP BY employees.SSN" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
				//return;
			}
		}else if(jRadioButton7.isSelected()){
			ColNum = 4;
			String[] temp1 = { "SSN", "FirstName", "LastName", "COUNT(*)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT owners.SSN, private_owners.FirstName, private_owners.LastName ,COUNT(*) FROM private_owners JOIN owners ON private_owners.SSN = owners.SSN , properties WHERE properties.OwnerSSN = owners.SSN GROUP BY owners.SSN" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
				//return;
			}
		}else if(jRadioButton8.isSelected()){
			ColNum = 3;
			String[] temp1 = { "SSN", "BusinessName", "COUNT(*)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT owners.SSN, business_owners.BusinessName ,COUNT(*) FROM business_owners JOIN owners ON business_owners.SSN = owners.SSN , properties WHERE properties.OwnerSSN = owners.SSN GROUP BY owners.SSN" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
				//return;
			}
		}else if(jRadioButton9.isSelected()){
			ColNum = 3;
			String[] temp1 = { "FirstName", "LastName", "Salary"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT employees.FirstName, employees.LastName, employees.Salary FROM employees ORDER BY employees.Salary DESC" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
				//return;
			}
		}else if(jRadioButton10.isSelected()){
			ColNum = 2;
			String[] temp1 = { "PropertyRegistrationNo", "Rent"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT properties.PropertyRegistrationNo, properties.Rent FROM properties WHERE properties.PropertyRegistrationNo NOT IN (SELECT contracts.PropertyRegistrationNo FROM contracts) ORDER BY properties.Rent ASC" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
				//return;
			}
		}else if(jRadioButton11.isSelected()){
			ColNum = 3;
			String[] temp1 = { "SSN" ,"BusinessName", "COUNT(*)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT owners.SSN, business_owners.BusinessName ,COUNT(*) FROM business_owners JOIN owners ON business_owners.SSN = owners.SSN , properties WHERE properties.OwnerSSN = owners.SSN GROUP BY owners.SSN HAVING COUNT(*) > 3" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
			}
		}else if(jRadioButton12.isSelected()){
			ColNum = 4;
			String[] temp1 = { "SSN", "FirstName", "LastName", "COUNT(*)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT owners.SSN, private_owners.FirstName, private_owners.LastName ,COUNT(*) FROM private_owners JOIN owners ON private_owners.SSN = owners.SSN , properties WHERE properties.OwnerSSN = owners.SSN GROUP BY owners.SSN HAVING COUNT(*) > 3" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
			}
		}else if(jRadioButton13.isSelected()){
			ColNum = 4;
			String[] temp1 = { "ClientRegistrationNo", "FirstName", "LastName", "COUNT(*)"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT clients.ClientRegistrationNo, clients.FirstName, clients.LastName ,COUNT(*) FROM clients, contracts WHERE clients.ClientRegistrationNo = contracts.ClientRegistrationNo GROUP BY clients.ClientRegistrationNo HAVING COUNT(*) > 3" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
			}
		}else if(jRadioButton14.isSelected()){
			ColNum = 3;
			String[] temp1 = { "FirstName", "LastName", "Salary"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT employees.FirstName, employees.LastName, employees.Salary FROM employees WHERE employees.Salary > (SELECT AVG(Salary) FROM employees )" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
			}
		}else if(jRadioButton15.isSelected()){
			ColNum = 2;
			String[] temp1 = { "FirstName", "LastName"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT clients.FirstName, clients.LastName FROM clients WHERE clients.ClientRegistrationNo NOT IN (SELECT DISTINCT contracts.ClientRegistrationNo FROM contracts )" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
			}
		}else if(jRadioButton16.isSelected()){
			ColNum = 9;
			String[] temp1 = { "ClientRegistrationNo", "FirstName", "LastName", "PropertyRegistrationNo", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode", "Date", "Comments"};
			ColNames = temp1;
			
			Object[][] TableData = {};
			Table.setModel(new DefaultTableModel(TableData,ColNames));
			
			for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
			
			QueryString = "SELECT clients.ClientRegistrationNo, clients.FirstName, clients.LastName, properties.PropertyRegistrationNo, properties.Addr_StreetName, properties.Addr_StreetNo, properties.Addr_PostalCode, visits.Date, visits.Comments  FROM visits JOIN properties JOIN clients ON clients.ClientRegistrationNo = visits.ClientRegistrationNo AND visits.PropertyRegistrationNo = properties.PropertyRegistrationNo" ; 
			System.out.println(QueryString);// for Debugging
			
			try {
				TableData = Dbconn.show(conn, QueryString, FeedbackText, ColNum, ColNames);
				System.out.println("Showed the table");
				FeedbackText.append("Showed the table\n");
				
				//Refresh of the table
		        Table.setModel(new DefaultTableModel(TableData,ColNames));
		        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
		        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
		        }
				
			} catch (SQLException exc) {
				System.out.println("ERROR: Could not execute the query");
				FeedbackText.append("ERROR: Could not execute the query\n");
				exc.printStackTrace();
			}
		}else{
			;
		}
	}
}
