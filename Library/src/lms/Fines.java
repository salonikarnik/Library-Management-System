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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fines {

	private JFrame frmFines;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	DefaultTableModel model;
	static Connection conn = null;
	private String strQuery;
	private String loanID;
	private String cardNo;
	private String borrower;
	private String paid;
	private float rate=0.25f;
	String fine;
	float f;
	float totalFine;
	float estFine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fines window = new Fines();
					window.frmFines.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fines() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFines = new JFrame();
		frmFines.setTitle("FINES");
		frmFines.getContentPane().setBackground(new Color(240, 230, 140));
		frmFines.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
				ColumnSpec.decode("max(65dlu;default)"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(67dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50dlu"),
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
				RowSpec.decode("max(36dlu;default)"),}));
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome s = new Welcome();
				Welcome.main(null);
			}
		});
		frmFines.getContentPane().add(btnHome, "2, 2");
		
		JLabel lblRichardsonPublicLibrary = new JLabel("RICHARDSON PUBLIC LIBRARY");
		lblRichardsonPublicLibrary.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		frmFines.getContentPane().add(lblRichardsonPublicLibrary, "3, 2, 16, 1, center, default");
		
		JLabel lblBorrower = new JLabel("Borrower");
		lblBorrower.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(lblBorrower, "8, 6, right, default");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(textField, "10, 6, left, default");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");
		             strQuery="select b.card_no,fname,lname,sum(fine_amt),paid from borrowers b,book_loans l,fines f where card_no like '%?%' or fname like '%?%' or lname like '%?%';";
		             PreparedStatement update = conn.prepareStatement(strQuery);		          		             
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             String query= "select a.Card_no,sum(Fine_amt) from book_loans a,fines b where a.loan_id=b.loan_id and b.Paid =0 group by a.Card_no;";
		             String query2="SELECT DATEDIFF(due_date,date_out) from book_loans where card_no=?;";
		             PreparedStatement update2 = conn.prepareStatement(query2);
		             update.setString(1,textField_1.getText());
		             ResultSet rs = update2.executeQuery();			            
		             while (rs.next())
		             {
		            	 fine=rs.getString("datediff");
		            	 	            	 
		             }
		             
		             totalFine=rate*Integer.parseInt(fine);
		             
		             ResultSet rs1 = update.executeQuery();			            
		             while (rs1.next())
		             {
		            	 
		            	 cardNo=rs1.getString("card_no");
		            	 borrower =rs1.getString("fname")+rs1.getString("lname");
		            	 paid=rs1.getString("paid");
		            	 
		            	 String[] data={cardNo,borrower,Float.toString(totalFine),Float.toString(estFine),paid};
		            	 model.addRow(data);		            	 
		             }
		             
		             String query3 = "select a.Card_no,sum(Fine_amt) from book_loans a,fines b where a.loan_id=b.loan_id and a.Card_no="+cardNo+";";
		             
		             rs1.close();
		             conn.close();		             
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }   
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(btnNewButton, "12, 7, 1, 2, center, center");
		
		JLabel lblCardNo = new JLabel("Card No.");
		lblCardNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(lblCardNo, "8, 8, right, default");
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(textField_1, "10, 8, left, default");
		textField_1.setColumns(10);
		
		JButton btnAddFine = new JButton("ADD FINE");
		btnAddFine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(btnAddFine, "8, 12, center, default");
		
		JButton btnPayFine = new JButton("PAY FINE");
		btnPayFine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(btnPayFine, "10, 12, center, default");
		
		JButton btnUpdateFine = new JButton("REFRESH");
		btnUpdateFine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(btnUpdateFine, "12, 12");
		
		JButton btnFilter = new JButton("FILTER");
		btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFines.getContentPane().add(btnFilter, "14, 12, center, default");
		
		JScrollPane scrollPane = new JScrollPane();
		frmFines.getContentPane().add(scrollPane, "8, 16, 7, 5, fill, fill");
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("Card No.");
        model.addColumn("Borrower");
        model.addColumn("Total Fine");
        model.addColumn("Estimated Fine");
        model.addColumn("Paid");
		table.setModel(model);
		
		frmFines.setBounds(100, 100, 591, 370);
		frmFines.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
