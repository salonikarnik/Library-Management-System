package lms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckIn
{

	private JFrame frame;
	static Connection conn = null;
	private String strQuery;
	private String bookID;
	private String cardNumber;
	private String borrower;
	private String dateOut;
	private String dateIn;
	private String loanID;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	String value="0000-00-00";
	Object val;	
	DefaultTableModel model;
	String cell;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckIn window = new CheckIn();
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
	public CheckIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 230, 140));
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
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
				RowSpec.decode("25dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("5dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblRichardsonPublicLibrary = new JLabel("RICHARDSON PUBLIC LIBRARY");
		lblRichardsonPublicLibrary.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		frame.getContentPane().add(lblRichardsonPublicLibrary, "1, 2, 22, 1, center, default");
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome s = new Welcome();
				Welcome.main(null);
			}
		});
		frame.getContentPane().add(btnHome, "2, 4");
		
		JLabel lblNewLabel = new JLabel("Book ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel, "6, 6, right, default");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField, "8, 6, left, default");
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				bookID = textField.getText();
				cardNumber=textField_1.getText();
				borrower=textField_2.getText();
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
		             strQuery="select loan_id,book_id,c.card_no,fname,lname,date_out,date_in from book_loans c natural join borrowers b where c.card_no=b.card_no and (book_id like '%"+bookID+"%' or c.card_no like '%" + cardNumber+ "%' or fname like '%" + borrower+"%' or lname like '%"+borrower+"%');";
		             PreparedStatement update = conn.prepareStatement(strQuery);		          		             
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs = update.executeQuery();			            
		             while (rs.next())
		             {
		            	 loanID=rs.getString("loan_id");
		            	 bookID=rs.getString("book_id");
		            	 cardNumber=rs.getString("card_no");
		            	 borrower =rs.getString("fname")+rs.getString("lname");
		            	 dateOut=rs.getString("date_out");
		            	 dateIn=rs.getString("date_in");		            	 
		            	 if(dateIn.compareTo(value)==0)
		            		  dateIn="NULL"; 
		            	 String[] data={loanID,bookID,cardNumber,borrower,dateOut,dateIn};
		            	 model.addRow(data);		            	 
		             }
		             
		             rs.close();
		             conn.close();		             
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }   
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 0);
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				String formatted = format1.format(cal.getTime());
				textField_3.setText(formatted);
				 			
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnNewButton_1, "12, 6");
		
		JLabel lblCardNo = new JLabel("Card No.");
		lblCardNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblCardNo, "6, 8, right, default");
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_1, "8, 8, left, default");
		textField_1.setColumns(10);
		
		JLabel lblBorrowerName = new JLabel("Borrower Name");
		lblBorrowerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblBorrowerName, "6, 10, right, default");
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_2, "8, 10, left, default");
		textField_2.setColumns(10);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnReset, "12, 10");
		
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "6, 13, 13, 11, default, fill");
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				    int row = table.rowAtPoint(e.getPoint());
			        int col = table.columnAtPoint(e.getPoint());
			        if (row >= 0 && col >= 0)
			        {
			        	val=table.getModel().getValueAt(row,0);
			        }
			   
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setColumnHeaderView(table);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("Loan ID");
        model.addColumn("Book ID");
        model.addColumn("Card No.");
        model.addColumn("Borrower");
        model.addColumn("Date Out");
        model.addColumn("Date In");
		table.setModel(model);
		
		JLabel lblCheckInDate = new JLabel("Check In Date");
		lblCheckInDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblCheckInDate, "6, 26");
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_3, "8, 26, left, default");
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("CHECK IN");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
			     try
			        {
			             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
			             strQuery="update book_loans set date_in = curdate() where loan_id ="+ val + ";";
			             PreparedStatement update = conn.prepareStatement(strQuery);		          		             
			             Statement stmt = conn.createStatement();
			             stmt.execute("use library;");
			             update.executeUpdate();
			             conn.close();		             
			          }						
					catch(SQLException ex)
					{
			            System.out.println("Error in connection: " + ex.getMessage());
			        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnNewButton, "7, 30, 2, 1, center, default");
		frame.setBounds(100, 100, 694, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
