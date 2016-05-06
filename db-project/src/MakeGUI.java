import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MakeGUI {

	private JComboBox<String> TablesList;
	private JButton InsertButton;
	private JButton DeleteButton;
	private JButton ShowButton;
	private JButton UpdateButton;
	private JButton QueryButton;
	
	public MakeGUI() {
		JFrame frame = new JFrame("Database App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 100);
		frame.setLocation(300, 300);

		JPanel BasicPan = new JPanel();
		BasicPan.setBackground(new Color(222, 239, 255));
		
		TablesList = new JComboBox<String>(); //InsertList creation
		TablesList.addItem("employees");
		TablesList.addItem("supervises");
		TablesList.addItem("property_types");
		TablesList.addItem("clients");
		TablesList.addItem("client_phones");
		TablesList.addItem("owners");
		TablesList.addItem("owner_phones");
		TablesList.addItem("private_owners");
		TablesList.addItem("business_owners");
		TablesList.addItem("properties");
		TablesList.addItem("visits");
		TablesList.addItem("contracts");
		TablesList.addItem("client_info");
		TablesList.addItem("client_total_info");
		
		BasicPan.add(TablesList);
		
		InsertButton = new JButton(); //InsertButton creation
		InsertButton.setText("Insert");
		BasicPan.add(InsertButton);
		InsertButton.addActionListener(new ActionListener() { // !!! Here we define an inner class!!!
			public void actionPerformed(ActionEvent e) {
				String TableName = TablesList.getSelectedItem().toString();
				new InsertToTable(TableName);
			}
			
		});

		DeleteButton = new JButton();
		DeleteButton.setText("Delete");
		BasicPan.add(DeleteButton);
		DeleteButton.addActionListener(new ActionListener() { // !!! Here we define an inner class!!!
			public void actionPerformed(ActionEvent e) {
				String TableName = TablesList.getSelectedItem().toString();
				new DeleteFromTable(TableName);
			}
		});
		
		ShowButton = new JButton();
		ShowButton.setText("Show Table");
		BasicPan.add(ShowButton);
		ShowButton.addActionListener(new ActionListener() { // !!! Here we define an inner class!!!
			public void actionPerformed(ActionEvent e) {
				String TableName = TablesList.getSelectedItem().toString();
				new ShowTable(TableName);
			}
		});
		
		UpdateButton = new JButton();
		UpdateButton.setText("Update Table");
		BasicPan.add(UpdateButton);
		UpdateButton.addActionListener(new ActionListener() { // !!! Here we define an inner class!!!
			public void actionPerformed(ActionEvent e) {
				String TableName = TablesList.getSelectedItem().toString();
				new UpdateTable(TableName);
			}
		});
		
		QueryButton = new JButton();
		QueryButton.setText("Make query");
		BasicPan.add(QueryButton);
		QueryButton.addActionListener(new ActionListener() { // !!! Here we define an inner class!!!
			public void actionPerformed(ActionEvent e) {
				new MakeQuery();
			}
		});
		

		frame.add(BasicPan);
		frame.setVisible(true);
	}
}
