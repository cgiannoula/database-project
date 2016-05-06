import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ShowTable implements ActionListener {

	private String TableName;
	private int ColNum; // Number of Columns of the array
	private String[] ColNames;
	
	private JButton ShowButton;
	private JTable Table;
	public JTextArea FeedbackText;
	
	private DatabaseConnection Dbconn;
	private Connection conn;

	public ShowTable(String TableName) {

		this.Dbconn = Main.Dbconn;
		this.conn = Main.conn;
		this.TableName = TableName;

		ColNum = 0;
		ColNames = null;
		
		if(TableName.equals("employees")){
			ColNum = 9;
			String[] temp1 = { "SSN", "FirstName", "LastName", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode", "Salary", "WorkPhoneNumber", "MobilePhoneNumber" };
			ColNames = temp1;
		}
		else if(TableName.equals("supervises")){
			ColNum = 2;
			String[] temp1 = { "SupervisorSSN", "SupervisedSSN" };
			ColNames = temp1;
		}
		else if(TableName.equals("property_types")){
			ColNum = 3;
			String[] temp1 = {"PropertyTypeID", "Description", "Rooms"};
			ColNames = temp1;
		}
		else if(TableName.equals("clients")){
			ColNum = 10;
			String[] temp1 = {"clientRegistrationNo", "FirstName", "LastName", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode", "RegistrationDate", "MaxRent", "RegisteredBy", "PrefferedTypeID"};
			ColNames = temp1;
		}
		else if(TableName.equals("client_phones")){
			ColNum = 2;
			String[] temp1 = {"ClientRegistrationNo", "PhoneNumber"};
			ColNames = temp1;
		}
		else if(TableName.equals("owners")){
			ColNum = 4;
			String[] temp1 = {"SSN", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode"};
			ColNames = temp1;
		}
		else if(TableName.equals("owner_phones")){
			ColNum = 2;
			String[] temp1 = {"PhoneNumber", "SSN"};
			ColNames = temp1;
		}
		else if(TableName.equals("private_owners")){
			ColNum = 3;
			String[] temp1 = { "SSN", "FirstName", "LastName" };
			ColNames = temp1;
		}
		else if(TableName.equals("business_owners")){
			ColNum = 5;
			String[] temp1 = { "SSN", "BusinessName", "BusinessType", "ContactFirstName", "ContactLastName"};
			ColNames = temp1;
		}
		else if(TableName.equals("properties")){
			ColNum = 10;
			String[] temp1 = {"PropertyRegistrationNo", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode", "Size", "Rent", "Floor", "PropertyTypeID", "OwnerSSN", "ManagerSSN" };
			ColNames = temp1;
		}
		else if(TableName.equals("visits")){
			ColNum = 4;
			String[] temp1 = {"ClientRegistrationNo", "PropertyRegistrationNo", "Date", "Comments"};
			ColNames = temp1;
		}
		else if(TableName.equals("contracts")){
			ColNum = 7;
			String[] temp1 = {"ContractNo", "Rent", "PaymentType", "RentStart", "RentFinish", "ClientRegistrationNo", "PropertyRegistrationNo"};
			ColNames = temp1;
		}
		else if(TableName.equals("client_info")){
			ColNum = 4;
			String[] temp1 = {"ClientRegistrationNo", "FirstName", "LastName", "RegistrationDate"};
			ColNames = temp1;
		}
		else if(TableName.equals("client_total_info")){
			ColNum = 6;
			String[] temp1 = {"ClientRegistrationNo", "FirstName", "LastName", "RegistrationDate", "HousesRented", "TotalRent"};
			ColNames = temp1;
		}
		
		JFrame frame = new JFrame(TableName);
		frame.setSize(600, 400);
		frame.setLocation(600, 400);

		JPanel BasicPan = new JPanel();// FlawLayout default in BasicPan
		BasicPan.setBackground(new Color(222, 239, 255));
		
		Object[][] TableData = {};
		Table = new JTable(TableData, ColNames);
		JScrollPane ScrollPane1 = new JScrollPane(Table);
		
		//Table.setPreferredScrollableViewportSize(Table.getPreferredSize());
		//Table.setFillsViewportHeight(true);
		
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// This disables the horizontal cell resize
		for(int i = 0; i < ColNum; i++){// Set the PreferredWidth of each column to 150px
        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
		
		ScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollPane1.setPreferredSize(new Dimension(500, 200));
		BasicPan.add(ScrollPane1);
		
		FeedbackText = new JTextArea();
		FeedbackText.setEditable(false);
		JScrollPane ScrollPane = new JScrollPane(FeedbackText);
		ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane.setPreferredSize(new Dimension(380, 100));
		BasicPan.add(ScrollPane);
		
		ShowButton = new JButton();
		ShowButton.setText("Refresh Table");
		ShowButton.addActionListener(this);
		BasicPan.add(ShowButton);
		
		frame.add(BasicPan);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String SelectString = "SELECT * FROM " + TableName; //+ " WHERE " + IdField

		System.out.println(SelectString);// for Debugging
		try {
			Object[][] TableData = Dbconn.show(conn, SelectString, FeedbackText, ColNum, ColNames);
			System.out.println("Showed the table");
			FeedbackText.append("Showed the table\n");
			
			//Refresh of the table
	        Table.setModel(new DefaultTableModel(TableData,ColNames));
	        for(int i = 0; i < ColNum; i++){// Set the size of each column to 150px
	        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
	        }
	       
		} catch (SQLException e) {
			System.out.println("ERROR: Could not show the table");
			FeedbackText.append("ERROR: Could not show the table\n");
			e.printStackTrace();
			//return;
		}
	}

}
