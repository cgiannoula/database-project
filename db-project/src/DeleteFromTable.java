import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class DeleteFromTable implements ActionListener {
	
	private String TableName; // Name of the Array that we delete from
	String[] ColNames;
	private char[] ContentType;
	
	private JLabel IdFieldLabel;
	private JComboBox<String> FieldsList;

	private JTextField IdTextField;
	private JTextArea FeedbackText;

	private DatabaseConnection Dbconn;
	private Connection conn;
	
	public DeleteFromTable(String TableName){
		
		this.Dbconn = Main.Dbconn;
		this.conn = Main.conn;
		this.TableName = TableName;

		int ColNum = 0;
		ColNames = null;
		
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
		
		
		JFrame frame = new JFrame("Delete from " + TableName);
		frame.setSize(600, 200);
		frame.setLocation(600, 400);

		JPanel BasicPan = new JPanel();// FlawLayout default in BasicPan
		BasicPan.setBackground(new Color(222, 239, 255));
		
		IdFieldLabel = new JLabel("Delete record(s) with:");
		BasicPan.add(IdFieldLabel);
		
		FieldsList = new JComboBox<String>();
		for (int i = 0; i < ColNum; i++) {
			FieldsList.addItem(ColNames[i]);
		}
		BasicPan.add(FieldsList);
		
		IdTextField = new JTextField(20);
		IdTextField.addActionListener(this);
		BasicPan.add(IdTextField);
		
		FeedbackText = new JTextArea();
		FeedbackText.setEditable(false);
		JScrollPane ScrollPane = new JScrollPane(FeedbackText);
		ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane.setPreferredSize(new Dimension(500, 100));
		BasicPan.add(ScrollPane);
		
		frame.add(BasicPan);
		frame.setVisible(true);
		
		if(TableName.equals("client_total_info")){
			IdTextField.setVisible(false);
			IdFieldLabel.setVisible(false);
			FieldsList.setVisible(false);
			FeedbackText.setText("This information is Non-Updateable.");
			return;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String Field = FieldsList.getSelectedItem().toString();
		String Target = IdTextField.getText();
		
		int i = 0;
		
		// We determine whether or not we need to add '' to the Field variable.
		while (Field != ColNames[i]){
			i++;
		}
		if(ContentType[i]=='C'||ContentType[i]=='D'){
			Target = "'" + Target + "'";
		}
		String deleteString = "DELETE FROM " + TableName + " WHERE " + Field + "=" + Target;
		
		System.out.println(deleteString);// for Debugging
		try {
			Dbconn.executeUpdate(conn, deleteString);
			System.out.println("Deleted record from table (if it exists).");
			FeedbackText.append("Deleted record from table (if it exists).\n");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not delete this record from the table");
			FeedbackText.append("ERROR: Could not delete this record from the table\n");
		}
	}
}
