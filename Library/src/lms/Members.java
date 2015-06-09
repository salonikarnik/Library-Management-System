package lms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;

public class Members {

	static Connection conn = null;
	PreparedStatement preparedStatement = null;
	String strQuery ="";
	private String cardNo;
	private String fname;
	private String lname;
	private String address;
	private String city;
	int num;
	private String state;
	private String phone;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_18;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Members window = new Members();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Members() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "2, 2, fill, fill");
		
		Panel panel_3 = new Panel();
		tabbedPane.addTab("Search Member", null, panel_3, null);
		tabbedPane.setBackgroundAt(0, new Color(240, 230, 140));
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome s = new Welcome();
				Welcome.main(null);
			}
		});
		panel_3.add(btnHome, "2, 2");
		
		JLabel lblEnterMemberCard_2 = new JLabel("Enter Member Card No.");
		lblEnterMemberCard_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblEnterMemberCard_2, "6, 4, right, default");
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(textField_12, "8, 4, left, default");
		textField_12.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				cardNo = textField_12.getText();
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
		             strQuery="SELECT * from borrowers where Card_no =?";
		             preparedStatement = conn.prepareStatement(strQuery);
		             preparedStatement.setString(1,cardNo);		         	 
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs = preparedStatement.executeQuery();
		             while (rs.next()) 
                     {                 
                          fname=rs.getString("fname");
                          textField_13.setText(fname);
                          lname = rs.getString("lname");
                          textField_14.setText(lname);
                          address=rs.getString("address");
                          textField_15.setText(address);
                          phone = rs.getString("phone");
                          textField_18.setText(phone);                 
                   	   	                   	      
          		     }     
		            
		             rs.close();             
		             conn.close();				             
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }
				
				
			}
		});
		
		btnSearch.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_3.add(btnSearch, "12, 4");
		
		JLabel lblMemberDetails = new JLabel("Member Details:");
		lblMemberDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblMemberDetails, "6, 8");
		
		JLabel lblFirstName_2 = new JLabel("First Name:");
		lblFirstName_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblFirstName_2, "6, 12, left, default");
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_13.setColumns(10);
		panel_3.add(textField_13, "8, 12, fill, default");
		
		JLabel lblLastName_2 = new JLabel("Last Name:");
		lblLastName_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblLastName_2, "6, 14, left, default");
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_14.setColumns(10);
		panel_3.add(textField_14, "8, 14, fill, default");
		
		JLabel lblAddress_1 = new JLabel("Address:");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblAddress_1, "6, 16, left, default");
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_15.setColumns(10);
		panel_3.add(textField_15, "8, 16, fill, default");
		
		JLabel lblPhone_2 = new JLabel("Phone:");
		lblPhone_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblPhone_2, "6, 18, left, default");
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_18.setColumns(10);
		panel_3.add(textField_18, "8, 18, fill, default");
		tabbedPane.setEnabledAt(0, true);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Member", null, panel, null);
		tabbedPane.setEnabledAt(1, true);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblCardNo = new JLabel("Card No.");
		lblCardNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblCardNo, "8, 4, left, default");
		
		textField = new JTextField();
		textField.setToolTipText("Auto-generated");
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(textField, "10, 4, left, default");
		textField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblFirstName, "8, 6, left, default");
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(textField_1, "10, 6, left, default");
		textField_1.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblLastName, "8, 8, left, default");
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(textField_2, "10, 8, left, default");
		textField_2.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblAddress, "8, 10, left, default");
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(textField_3, "10, 10, left, default");
		textField_3.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblCity, "8, 12, left, default");
		
		String cities[]={"Dallas","Plano","Richardson"};
		final JComboBox comboBox_1 = new JComboBox(cities);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setEditable(true);
		panel.add(comboBox_1, "10, 12, left, default");
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblState, "8, 14, left, default");
		
		String states[]= {"NY","TX","TN","UT"};
		final JComboBox comboBox = new JComboBox(states);
	
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setSelectedIndex(1);
		comboBox.setEditable(true);
		panel.add(comboBox, "10, 14, left, center");
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblPhone, "8, 16, left, default");
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_5.setToolTipText("Enter only digits");
		panel.add(textField_5, "10, 16, left, default");
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("CREATE");
		btnNewButton.addMouseListener(new MouseAdapter()
		{	
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String strQuery2,city,state;
				fname=textField_1.getText();
			    lname=textField_2.getText();
			    state=(String) comboBox.getSelectedItem();
			    city=(String) comboBox_1.getSelectedItem();
			    address = textField_3.getText() + "," + city + "," + state;
			    phone=textField_5.getText();
			    
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
		             strQuery=" select max(card_no) from borrowers;";
		             strQuery2=" select fname,lname,address from borrowers;";
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs = stmt.executeQuery(strQuery);
		             while(rs.next())
		             {
		            	 num=Integer.parseInt(rs.getString(1));
		             }
		             num+=1;
		             cardNo=String.valueOf(num);
		             rs = stmt.executeQuery(strQuery2);
		             while(rs.next())
		             {
		            	 if(fname.equals(rs.getString("fname"))&lname.equals(rs.getString("lname"))&address.equals(rs.getString("address")))
		            	 {
		            		 JOptionPane optionPane = new JOptionPane("Values are being duplicated", JOptionPane.ERROR_MESSAGE);    
		            		 JDialog dialog = optionPane.createDialog("Failure");
		            		 dialog.setAlwaysOnTop(true);
		            		 dialog.setVisible(true);
		            	 }
		            	 else
		            	 {
		            		 PreparedStatement update = conn.prepareStatement("INSERT INTO BORROWERS values(?,?,?,?,?);");
				             update.setString(1,cardNo);
				             update.setString(2,fname);
				             update.setString(3,lname);
				             update.setString(4,address);
				             update.setString(5,phone);
				             update.execute();
				             textField.setText(cardNo);
		            	 }
		             }
		             rs.close();
		             conn.close();
		        }
		             
		             
		                        
		          					
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }
			}
			
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel.add(btnNewButton, "10, 20, left, default");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Delete Member", null, panel_1, null);
		tabbedPane.setEnabledAt(2, true);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEnterMemberCard = new JLabel("Enter Member Card Number :");
		lblEnterMemberCard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblEnterMemberCard, "8, 6, left, default");
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel_1.add(textField_4, "10, 6, left, default");
		textField_4.setColumns(10);
		
		JButton btnSearch_1 = new JButton("SEARCH");
		btnSearch_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				cardNo = textField_4.getText();
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
		             strQuery="SELECT * from borrowers where Card_no =?";
		             preparedStatement = conn.prepareStatement(strQuery);
		             preparedStatement.setString(1,cardNo);		         	 
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs = preparedStatement.executeQuery(); 
		             
		             while(rs.next())
		             {
		            	 String str = rs.getString("fname") + " " + rs.getString("lname");
		            	 textField_6.setText(str);
		             }
		             rs.close();             
		             conn.close();				             
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }
			}
		});
		btnSearch_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		panel_1.add(btnSearch_1, "12, 6");
		
		JLabel lblMember = new JLabel("Member is");
		lblMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblMember, "8, 10, right, default");
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_6.setEditable(false);
		panel_1.add(textField_6, "10, 10, left, default");
		textField_6.setColumns(10);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				cardNo = textField_4.getText();
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");		              
		             Statement stmt = conn.createStatement();
		             PreparedStatement update = conn.prepareStatement("DELETE from borrowers where card_no=?;");
		             stmt.execute("use library;");		             
		             update.setString(1,cardNo);		            
		             update.execute();		                         
		             conn.close();	
		             textField_4.setText("");
		             textField_6.setText("");
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }
		
			}
		});
		btnDelete.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		panel_1.add(btnDelete, "10, 14");
		
				
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Update Member", null, panel_2, null);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEnterMemberCard_1 = new JLabel("Enter Member Card No.");
		lblEnterMemberCard_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblEnterMemberCard_1, "6, 4, left, default");
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(textField_7, "8, 4, fill, default");
		textField_7.setColumns(10);
		
		JButton button = new JButton("SEARCH");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				cardNo = textField_7.getText();
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
		             strQuery="SELECT * from borrowers where Card_no =?";
		             preparedStatement = conn.prepareStatement(strQuery);
		             preparedStatement.setString(1,cardNo);		         	 
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs = preparedStatement.executeQuery();
		             while (rs.next()) 
                     {                 
                          fname=rs.getString("fname");
                          textField_8.setText(fname);
                          lname = rs.getString("lname");
                          textField_9.setText(lname);
                          address=rs.getString("address");
                          textField_10.setText(address);
                          phone = rs.getString("phone");
                          textField_11.setText(phone);                 
                   	   	                   	      
          		     } 
		            
		             rs.close();             
		             conn.close();				             
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }	
			}
		});
		button.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(button, "10, 4, center, default");
		
		JLabel lblFirstName_1 = new JLabel("First Name");
		lblFirstName_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblFirstName_1, "6, 8, left, default");
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(textField_8, "8, 8, fill, default");
		textField_8.setColumns(10);
		
		JLabel lblLastName_1 = new JLabel("Last Name");
		lblLastName_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblLastName_1, "6, 10, left, default");
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setColumns(10);
		panel_2.add(textField_9, "8, 10, fill, default");
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel, "6, 12, left, default");
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_10.setColumns(10);
		panel_2.add(textField_10, "8, 12, fill, default");
		
		JLabel lblPhone_1 = new JLabel("Phone");
		lblPhone_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblPhone_1, "6, 14, left, default");
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_11.setColumns(10);
		panel_2.add(textField_11, "8, 14, fill, default");
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
			
				cardNo = textField_7.getText();
				fname = textField_8.getText();
				lname = textField_9.getText();
				address = textField_10.getText();
				phone = textField_11.getText();
				
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
		             strQuery="update borrowers set fname=?,lname=?, address=?,phone =? where card_no =?;";
		             PreparedStatement update = conn.prepareStatement("update borrowers set fname=?,lname=?, address=?,phone =? where card_no =?;");
		             update.setString(1,fname);
		             update.setString(2,lname);
		             update.setString(3,address);
		             update.setString(4,phone);
		             update.setString(5,cardNo);
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             update.execute();
		             conn.close();		             
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }
				
			}
		});
		btnUpdate.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		panel_2.add(btnUpdate, "8, 18, left, default");
		tabbedPane.setEnabledAt(3, true);
	}
	

}
