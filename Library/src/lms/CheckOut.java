package lms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Color;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckOut {

	private JFrame frame;
	static Connection conn = null;
	PreparedStatement preparedStatement = null;
	private String strQuery;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String bookId;
	private String loanId;
	private String branchId;
	private String cardNo;
	private String dateOut;
	private String dueDate;
	int total;
	int max;
	int noOfLoans;
	int loanedBooks;
	int available;
	   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut window = new CheckOut();
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
	public CheckOut() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 230, 140));
		frame.setBounds(100, 100, 497, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("RICHARDSON PUBLIC LIBRARY");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel, "1, 2, 22, 1, center, default");
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome s = new Welcome();
				Welcome.main(null);
			}
		});
		frame.getContentPane().add(btnNewButton_1, "2, 4");
		
		JLabel lblLoanId = new JLabel("Loan ID");
		lblLoanId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblLoanId, "10, 6, right, default");
		
		textField = new JTextField();
		textField.setToolTipText("required");
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField, "12, 6, fill, default");
		textField.setColumns(10);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblBookId, "10, 8, right, default");
		
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try{
					 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
			    	 strQuery="select max(loan_id) from book_loans;";
			    	 Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs=stmt.executeQuery(strQuery);		             		            
		             while (rs.next())
		             {
		            	 max=Integer.parseInt(rs.getString("max(loan_id)"));	            	 
		             }
		             
		             max=max+1;	
		             textField.setText(Integer.toString(max));
				}
				 catch(SQLException ex)
					{
			            System.out.println("Error in connection: " + ex.getMessage());
			        }
				
				
				
				
			}
		});
		textField_1.setToolTipText("required");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_1, "12, 8, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblrequired = new JLabel("* Required");
		lblrequired.setForeground(new Color(255, 0, 0));
		frame.getContentPane().add(lblrequired, "14, 8");
		
		JLabel lblBranchId = new JLabel("Branch ID");
		lblBranchId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblBranchId, "10, 10, right, default");
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("required");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_2, "12, 10, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblrequired_1 = new JLabel("* Required");
		lblrequired_1.setForeground(new Color(255, 0, 0));
		frame.getContentPane().add(lblrequired_1, "14, 10");
		
		JLabel lblCardNo = new JLabel("Card No.");
		lblCardNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblCardNo, "10, 12, right, default");
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Calendar cal = Calendar.getInstance();
 				cal.add(Calendar.DATE, 0);
 				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
 				String formatted = format1.format(cal.getTime());
 				cal.add(Calendar.DATE, 14);
 				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
 				String formatted2 = format2.format(cal.getTime());
 				
 				textField_4.setText(formatted);
 				textField_5.setText(formatted2);
 				
 				dateOut=textField_4.getText();
            	dueDate=textField_5.getText();
			}
		});
		textField_3.setToolTipText("required");
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_3, "12, 12, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblrequired_2 = new JLabel("* Required");
		lblrequired_2.setForeground(new Color(255, 0, 0));
		frame.getContentPane().add(lblrequired_2, "14, 12");
		
		JLabel lblCheckoutDate = new JLabel("Checkout Date");
		lblCheckoutDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblCheckoutDate, "10, 14, right, default");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_4, "12, 14, fill, default");
		textField_4.setColumns(10);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblDueDate, "10, 16, right, default");
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textField_5, "12, 16, fill, default");
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("CHECK OUT");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
			    bookId=textField_1.getText();
			    branchId=textField_2.getText();
			    cardNo=textField_3.getText();
			   			    
				try
			     {
			    	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
			    	 strQuery="select count(loan_id) from book_loans where card_no =?;";
		             PreparedStatement update = conn.prepareStatement(strQuery);
		             update.setString(1,cardNo);
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs = update.executeQuery();			            
		             while (rs.next())
		             {
		            	 noOfLoans=Integer.parseInt(rs.getString("count(loan_id)"));	            	 
		             }
		                          
		             
		             if(noOfLoans>3)
		             {
		            	 JOptionPane.showMessageDialog(null, "Sorry! No more books can be checked out for this user. User has already exceeded maximum number of Book Loans");
		            	 return;		           
		            }
		             else
		             {
		            	 strQuery="select count(no_of_copies) from book_copies where book_id=? and branch_id=?;";
		            	 update = conn.prepareStatement(strQuery);
		            	 update.setString(1,bookId);
		            	 update.setString(2,branchId);
		            	 rs=update.executeQuery();
		            	 while (rs.next())
			             {
			            	 total=Integer.parseInt(rs.getString("count(no_of_copies)"));	
			            	 
			             } 
		            	 
		            	 strQuery="select count(no_of_copies) from book_loans b,book_copies c where date_in = '0000-00-00' and c.book_id=? and b.branch_id=?;";
		            	 update = conn.prepareStatement(strQuery);
		            	 update.setString(1,bookId);
		            	 update.setString(2,branchId);
		            	 rs=update.executeQuery();
		            	 while (rs.next())
			             {
			            	 loanedBooks=Integer.parseInt(rs.getString("count(no_of_copies)"));	
			            	 
			             }
		            	 
		            	available=total-loanedBooks; 
		            	 if(available==0)
		            	 {
		            		 JOptionPane.showMessageDialog(null, "Sorry! The required book is unavailable currently");
			            	 return;
		            	 }
		            	 else
		            	 {
		            		 strQuery="insert into book_loans(loan_id,book_id,branch_id,card_no,date_out,due_date) values ('?','?','?','?','?','?');";
		            	     update = conn.prepareStatement(strQuery);
			            	 update.setString(1,Integer.toString(max));
			            	 update.setString(2,bookId);
			            	 update.setString(3,branchId);
			            	 update.setString(4,cardNo);
			            	 update.setString(5,dateOut);
			            	 update.setString(6,dueDate);
			            	 rs=update.executeQuery();	
			            	 JOptionPane.showMessageDialog(null, "Success");
			            	 		            		 
		            	 }
		             }
		             
		             
		             
		             
		             
			     }
			     
			     catch(SQLException ex)
					{
			            System.out.println("Error in connection: " + ex.getMessage());
			        }
			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnNewButton, "11, 20, 2, 1, center, default");
	}

}
