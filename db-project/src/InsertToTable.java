import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InsertToTable implements ActionListener{//, PropertyChangeListener {

	private String TableName; // Name of the Array that we insert
	private int ColNum; // Number of Columns of the array
	private char[] ContentType;
	
	
	private JLabel[] Label;
	private JTextField[] textField; // JTextField for each table field
	private JTextArea FeedbackText;

	private DatabaseConnection Dbconn;
	private Connection conn;
	
	//JFormattedTextField numField;

	public InsertToTable(String TableName) {
		
		this.Dbconn = Main.Dbconn;
		this.conn = Main.conn;
		this.TableName = TableName;
		
		String[] ColNames = null;

		if(TableName.equals("employees")){
			ColNum = 9;
			String[] temp1 = { "SSN *", "FirstName *", "LastName *",
					"Addr_StreetName", "Addr_StreetNo", "Addr_PostalCode",
					"Salary *", "WorkPhoneNumber *", "MobilePhoneNumber" };
			ColNames = temp1;
			char[] temp2 = {'I','C','C','C','I','I','I','I','I','I'};
			ContentType = temp2;
		}
		else if(TableName.equals("supervises")){
			ColNum = 2;
			String[] temp1 = { "SupervisorSSN *", "SupervisedSSN *" };
			ColNames = temp1;
			char[] temp2 = {'I','I'};
			ContentType = temp2;
		}
		else if(TableName.equals("property_types")){
			ColNum = 3;
			String[] temp1 = {"PropertyTypeID *", "Description", "Rooms *"};
			ColNames = temp1;
			char[] temp2 = {'I', 'C', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("clients")){
			ColNum = 10;
			String[] temp1 = {"clientRegistrationNo *",
					"FirstName *",
					"LastName *",
					"Addr_StreetName",
					"Addr_StreetNo",
					"Addr_PostalCode",
					"RegistrationDate *",
					"MaxRent",
					"RegisteredBy",
					"PrefferedTypeID"};
			ColNames = temp1;
			char[] temp2 = {'I', 'C', 'C', 'C', 'I', 'I', 'D', 'I', 'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("client_phones")){
			ColNum = 2;
			String[] temp1 = {"ClientRegistrationNo *", "PhoneNumber *"};
			ColNames = temp1;
			char[] temp2 = {'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("owners")){
			ColNum = 4;
			String[] temp1 = {"SSN *", "Addr_StreetName ", "Addr_StreetNo ", "Addr_PostalCode "};
			ColNames = temp1;
			char[] temp2 = {'I', 'C', 'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("owner_phones")){
			ColNum = 2;
			String[] temp1 = {"PhoneNumber *", "SSN *"};
			ColNames = temp1;
			char[] temp2 = {'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("private_owners")){
			ColNum = 3;
			String[] temp1 = { "SSN *", "FirstName *", "LastName *" };
			ColNames = temp1;
			char[] temp2 = {'I','C','C'};
			ContentType = temp2;
		}
		else if(TableName.equals("business_owners")){
			ColNum = 5;
			String[] temp1 = { "SSN *", "Business Name *", "Business Type", "Contact First Name", "Contact Last Name"};
			ColNames = temp1;
			char[] temp2 = {'I','C','C','C','C'};
			ContentType = temp2;
		}
		else if(TableName.equals("properties")){
			ColNum = 10;
			String[] temp1 = {"PropertyRegistrationNo *", "Addr: Street Name *", "Addr: Street No *", "Addr: Postal Code *", "Size *", "Rent *", "Floor *", "PropertyTypeID *", "OwnerSSN *", "ManagerSSN" };
			ColNames = temp1;
			char[] temp2 = {'I','C','I','I','I','I','I','I','I','I'};
			ContentType = temp2;
		}
		else if(TableName.equals("visits")){
			ColNum = 4;
			String[] temp1 = {"ClientRegistrationNo *", "PropertyRegistrationNo *", "Date *", "Comments"};
			ColNames = temp1;
			char[] temp2 = {'I','I','D','C'};
			ContentType = temp2;
		}
		else if(TableName.equals("contracts")){
			ColNum = 7;
			String[] temp1 = {"ContractNo *", "Rent *", "Payment Type *", "Rent Start *", "Rent Finish *","ClientRegistratoinNo *", "PropertyRegistrationNo *"};
			ColNames = temp1;
			char[] temp2 = {'I','I','C','D', 'D', 'I', 'I'};
			ContentType = temp2;
		}
		else if(TableName.equals("client_info")){
			ColNum = 4;
			String[] temp1 = {"ClientRegistrationNo *", "FirstName *", "LastName *", "RegistrationDate *"};
			ColNames = temp1;
			char[] temp2 = {'I','C','C','D'};
			ContentType = temp2;
		}
		
		JFrame frame = new JFrame("Insert to " + TableName);
		frame.setSize(600, 25 * ColNum + 150);
		frame.setLocation(600, 400);

		JPanel BasicPan = new JPanel();// FlawLayout default in BasicPan
		BasicPan.setBackground(new Color(222, 239, 255));
		
		JPanel TablePanel = new JPanel();
		TablePanel.setLayout(new GridLayout(0, 2)); // Layout Manager to have 2
													// elements in one line
		TablePanel.setSize(400, 30 * ColNum);
		
		Label = new JLabel[ColNum];
		textField = new JTextField[ColNum];

		for (int i = 0; i < ColNum; i++) {
			if(ContentType[i] == 'C'){
				ColNames[i] += " (string)";
			}
			else if(ContentType[i] == 'D'){
				ColNames[i] += " (YYYY-MM-DD)";
			}
			else if(ContentType[i] == 'I'){
				ColNames[i] += " (integer)";
			}
			Label[i] = new JLabel(ColNames[i]);
			textField[i] = new JTextField(20);
			textField[i].addActionListener(this);
			TablePanel.add(Label[i]);
			TablePanel.add(textField[i]);
		}
		BasicPan.add(TablePanel);
		
		FeedbackText = new JTextArea();
		FeedbackText.setEditable(false);
		JScrollPane ScrollPane = new JScrollPane(FeedbackText);
		ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane.setPreferredSize(new Dimension(500, 100));
		BasicPan.add(ScrollPane);

		frame.add(BasicPan);
		frame.setVisible(true);
		
		if(TableName.equals("client_total_info")){
			TablePanel.setVisible(false);
			FeedbackText.setText("This information is Non-Updateable.");
			return;
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		String[] Content = new String[ColNum];

		// Add first Content[0] to the SQL statement
		Content[0] = textField[0].getText();
		if (textField[0].getText().equals("")) {
			Content[0] = "NULL";
		} else if (ContentType[0] == 'C') {
			Content[0] = "'" + Content[0] + "'";
		}

		String insertString = "INSERT INTO " + TableName + " VALUES ("
				+ Content[0];

		for (int i = 1; i < ColNum; i++) {
			Content[i] = textField[i].getText();
			if (textField[i].getText().equals("")) {// If field is empty we need
													// to write NULL to the SQL
													// statement
				Content[i] = "NULL";
			} else if (ContentType[i] == 'C' || ContentType[i] == 'D') {
				Content[i] = "'" + Content[i] + "'";
			}
			insertString = insertString + ", " + Content[i];
		}

		insertString = insertString + ")";
		System.out.println(insertString);// for Debugging

		try {
			Dbconn.executeUpdate(conn, insertString);
			System.out.println("Added to table");
			FeedbackText.append("Added to table\n");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not add to the table");
			FeedbackText.append("ERROR: Could not add to the table. All fields with (*) must be filled.\n");
		}
	}
}
