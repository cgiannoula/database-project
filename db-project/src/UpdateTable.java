import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UpdateTable implements ActionListener{
	
	private String TableName; // Name of the Array that we delete from
	
	private int ColNum;
	private String[] ColNames;
	private int PrimaryKeyNum;
	private char[] ContentType;
	
	private JLabel Title;
	private JLabel[] IdFieldLabel;
	private JTextField[] IdTextField;
	public JTable Table;
	private JTextArea FeedbackText;
	private JButton UpdateButton;

	private DatabaseConnection Dbconn;
	private Connection conn;
	
	public UpdateTable(String TableNames){
		
		this.Dbconn = Main.Dbconn;
		this.conn = Main.conn;
		this.TableName = TableNames;

		ColNum = 0;
		ColNames = null;
		PrimaryKeyNum = 1;
		
		if(TableName.equals("employees")){
			ColNum = 9;
			String[] temp1 = { "SSN", "FirstName", "LastName", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode", "Salary", "WorkPhoneNumber", "MobilePhoneNumber" };
			ColNames = temp1;
			char[] temp2 = {'I','C','C','C','I','I','I','I','I','I'};
			ContentType = temp2;
		}
		else if(TableName.equals("supervises")){
			ColNum = 2;
			String[] temp1 = { "SupervisorSSN", "SupervisedSSN" };
			ColNames = temp1;
			char[] temp2 = {'I','I'};
			ContentType = temp2;
		}
		else if(TableName.equals("property_types")){
			ColNum = 3;
			String[] temp1 = {"PropertyTypeID", "Description", "Rooms"};
			ColNames = temp1;
			char[] temp2 = {'I', 'C', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("clients")){
			ColNum = 10;
			String[] temp1 = {"clientRegistrationNo", "FirstName", "LastName", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode", "RegistrationDate", "MaxRent", "RegisteredBy", "PrefferedTypeID"};
			ColNames = temp1;
			char[] temp2 = {'I', 'C', 'C', 'C', 'I', 'I', 'D', 'I', 'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("client_phones")){
			ColNum = 2;
			String[] temp1 = {"ClientRegistrationNo", "PhoneNumber"};
			ColNames = temp1;
			char[] temp2 = {'I', 'I'};
			ContentType = temp2;
			PrimaryKeyNum = 2;
		}
		else if(TableName.equals("owners")){
			ColNum = 4;
			String[] temp1 = {"SSN", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode"};
			ColNames = temp1;
			char[] temp2 = {'I', 'C', 'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("owner_phones")){
			ColNum = 2;
			String[] temp1 = {"PhoneNumber", "SSN"};
			ColNames = temp1;
			char[] temp2 = {'I', 'I'};
			ContentType = temp2;
			PrimaryKeyNum = 2;
		}
		else if(TableName.equals("private_owners")){
			ColNum = 3;
			String[] temp1 = { "SSN", "FirstName", "LastName" };
			ColNames = temp1;
			char[] temp2 = {'I','C','C'};
			ContentType = temp2;
		}
		else if(TableName.equals("business_owners")){
			ColNum = 5;
			String[] temp1 = { "SSN", "BusinessName", "BusinessType", "ContactFirstName", "ContactLastName"};
			ColNames = temp1;
			char[] temp2 = {'I','C','C','C','C'};
			ContentType = temp2;
		}
		else if(TableName.equals("properties")){
			ColNum = 10;
			String[] temp1 = {"PropertyRegistrationNo", "Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode", "Size", "Rent", "Floor", "PropertyTypeID", "OwnerSSN", "ManagerSSN" };
			ColNames = temp1;
			char[] temp2 = {'I','C','I','I','I','I','I','I','I','I'};
			ContentType = temp2;
		}
		else if(TableName.equals("visits")){
			ColNum = 4;
			String[] temp1 = {"ClientRegistrationNo", "PropertyRegistrationNo", "Date", "Comments"};
			ColNames = temp1;
			char[] temp2 = {'I','I','D','C'};
			ContentType = temp2;
			PrimaryKeyNum = 3;
		}
		else if(TableName.equals("contracts")){
			ColNum = 7;
			String[] temp1 = {"ContractNo", "Rent", "PaymentType", "RentStart", "RentFinish", "ClientRegistrationNo", "PropertyRegistrationNo"};
			ColNames = temp1;
			char[] temp2 = {'I','I','C','D', 'D', 'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("client_info")){
			ColNum = 4;
			String[] temp1 = {"ClientRegistrationNo", "FirstName", "LastName", "RegistrationDate"};
			ColNames = temp1;
			char[] temp2 = {'I','C','C','D'};
			ContentType = temp2;
		}
	
		JFrame frame = new JFrame("Update " + TableName);
		frame.setSize(600, 400);
		frame.setLocation(600, 400);
	
		JPanel BasicPan = new JPanel();// FlawLayout default in BasicPan
		BasicPan.setBackground(new Color(222, 239, 255));
		
		if(TableName.equals("client_total_info")){
			frame.setSize(600, 170);
			FeedbackText = new JTextArea();
			FeedbackText.setEditable(false);
			JScrollPane ScrollPane = new JScrollPane(FeedbackText);
			ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			ScrollPane.setPreferredSize(new Dimension(380, 100));
			
			FeedbackText.setText("This information is Non-Updateable.");
			BasicPan.add(ScrollPane);
			frame.add(BasicPan);
			frame.setVisible(true);
			return;
		}
		
		JPanel TablePanel = new JPanel();
		TablePanel.setLayout(new GridLayout(0, 2)); // Layout Manager to have 2
													// elements in one line
		TablePanel.setSize(400, 30 * ColNum);
		
		Title = new JLabel("Update record with: ");
		TablePanel.add(Title);
		JLabel Title1 = new JLabel("");
		TablePanel.add(Title1);
		
		IdFieldLabel = new JLabel[PrimaryKeyNum];
		IdTextField = new JTextField[PrimaryKeyNum];
		
		for (int i = 0; i < PrimaryKeyNum; i++) {
			if(ContentType[i] == 'C'){
				IdFieldLabel[i] = new JLabel(ColNames[i] + " * (string)");
			}
			else if(ContentType[i] == 'D'){
				IdFieldLabel[i] = new JLabel(ColNames[i] + " * (YYYY-MM-DD)");
			}
			else if(ContentType[i] == 'I'){
				IdFieldLabel[i] = new JLabel(ColNames[i] + " * (integer)");
			}
			TablePanel.add(IdFieldLabel[i]);
			
			IdTextField[i] = new JTextField(20);
			IdTextField[i].addActionListener(this);
			TablePanel.add(IdTextField[i]);
		}
		
		BasicPan.add(TablePanel);
		
		Object[][] TableData = {};
		Table = new JTable(TableData, ColNames);
		JScrollPane ScrollPane1 = new JScrollPane(Table);
		
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// This disables the horizontal cell resize
		for(int i = 0; i < ColNum; i++){// Set the PreferredWidth of each column to 150px
        	Table.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
		
		ScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScrollPane1.setPreferredSize(new Dimension(500, 100));
		
		BasicPan.add(ScrollPane1);
		
		FeedbackText = new JTextArea();
		FeedbackText.setEditable(false);
		JScrollPane ScrollPane = new JScrollPane(FeedbackText);
		ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane.setPreferredSize(new Dimension(380, 100));
		BasicPan.add(ScrollPane);
		
		UpdateButton = new JButton();
		UpdateButton.setText("Update Table");
		UpdateButton.addActionListener(new ActionListener() { // !!! Here we define an inner class!!!
			public void actionPerformed(ActionEvent e) {
				
				if(Table.getModel().getRowCount()>0){
					String[] Content1 = new String[ColNum];
					
					for (int i = 0; i < ColNum; i++) {
						Content1[i] = ""+Table.getModel().getValueAt(0, i);
						if (Content1[i].equals( "null")) {// If field is empty we need
														// to write NULL to the SQL
														// statement
							Content1[i] = "NULL";
						} else if (ContentType[i] == 'C' || ContentType[i] == 'D') {
							Content1[i] = "'" + Content1[i] + "'";
						}
	
					}
					
					String UpdateString = "Update " + TableName + " SET " + ColNames[0] + "=" + Content1[0];
					
					for(int i = 1; i < ColNum; i++){
						UpdateString = UpdateString + ", " + ColNames[i] + "=" + Content1[i];
					}
					
					String[] Content2 = new String[ColNum];
					for (int i=0; i < PrimaryKeyNum; i++){
						Content2[i] = IdTextField[i].getText();
						if (IdTextField[i].getText().equals("")) {// If field is empty we need
																// to write NULL to the SQL
																// statement
							Content2[i] = "NULL";
						} else if (ContentType[i] == 'C' || ContentType[i] == 'D') {
							Content2[i] = "'" + Content2[i] + "'";
						}
					}
					
					UpdateString = UpdateString + " WHERE " + ColNames[0] + " = " + Content2[0];
					
					for (int i=1; i < PrimaryKeyNum; i++){
						UpdateString = UpdateString + " AND " + ColNames[i] + " = " + Content2[i];
					}
					
					System.out.println(UpdateString);// for Debugging
					try {
						Dbconn.executeUpdate(conn, UpdateString);
						System.out.println("Updated the table");
						FeedbackText.append("Updated the table\n");
					} catch (SQLException exc) {
						System.out.println("ERROR: Could not update the table");
						FeedbackText.append("ERROR: Could not update the table\n");

					}
				}else{
					
				}
			}
		});
		BasicPan.add(UpdateButton);
		
		frame.add(BasicPan);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		String[] Content = new String[ColNum];
		
		for (int i=0; i < PrimaryKeyNum; i++){
			Content[i] = IdTextField[i].getText();
			if (IdTextField[i].getText().equals("")) {// If field is empty we need
													// to write NULL to the SQL
													// statement
				Content[i] = "NULL";
			} else if (ContentType[i] == 'C' || ContentType[i] == 'D') {
				Content[i] = "'" + Content[i] + "'";
			}
		}
		
		String SelectString = "SELECT * FROM " + TableName + " WHERE " + ColNames[0] + " = " + Content[0];
		
		for (int i=1; i < PrimaryKeyNum; i++){
			SelectString = SelectString + " AND " + ColNames[i] + " = " + Content[i];
		}

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
		}
	}
}
