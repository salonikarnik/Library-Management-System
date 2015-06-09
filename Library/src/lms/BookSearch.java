package lms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookSearch {

	private JFrame frame;
	static Connection conn = null;
	private String strQuery;
	private String bookID;
	private String title;
	private String author;
	private String branchID;
	private String copies;
	private String branchID2;
	private String count;
	private int total;
	DefaultTableModel model ;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearch window = new BookSearch();
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
	public BookSearch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 230, 140));
		frame.setBounds(100, 100, 654, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("max(84dlu;min)"),}));
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome s = new Welcome();
				Welcome.main(null);
			}
		});
		frame.getContentPane().add(btnHome, "2, 2");
		
		JLabel lblNewLabel = new JLabel("RICHARDSON PUBLIC LIBRARY");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel, "8, 2, 3, 1, center, default");
		
		JLabel lblCriteria = new JLabel("CRITERIA");
		lblCriteria.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblCriteria, "4, 4");
		
		JLabel lblFillInAtleast = new JLabel("Fill in atleast one field below");
		lblFillInAtleast.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblFillInAtleast, "4, 6, 3, 1");
		
		JLabel lblBookId = new JLabel("Book ID");
		frame.getContentPane().add(lblBookId, "4, 10, right, default");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "6, 10, left, default");
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				bookID = textField.getText();
				title=textField_1.getText();
				author=textField_2.getText();
				try
		        {
		             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");		           
		             strQuery="select DISTINCT b.Book_id,title,author_name,branch_id,no_of_copies from book b join book_authors ba on b.book_id=ba.book_id join book_copies c on b.book_id=c.book_id where b.book_id like '%" + bookID + "%' or title like '%" + title + "%' or author_name like '%" + author + "%';";
		             PreparedStatement update = conn.prepareStatement(strQuery);		          		             
		             Statement stmt = conn.createStatement();
		             stmt.execute("use library;");
		             ResultSet rs = update.executeQuery();			            
		             while (rs.next())
		             {
		            	 bookID=rs.getString("book_id");
		            	 title=rs.getString("title");
		            	 author= rs.getString("author_name");
		            	 branchID=rs.getString("branch_id");
		            	 copies= rs.getString("no_of_copies");	
		            	 String[] data={bookID,title,author,branchID,copies,count};
		            	 model.addRow(data);
		            	 
		             }
		             
		             String strQuery2="select branch_id,count(*) as count from book_loans where date_in is null group By branch_id;";
		             ResultSet rs2 = stmt.executeQuery(strQuery2);
		             while (rs2.next())
		             {
		            	 	if(rs.getString("branch_id").equals(rs2.getString("branchID2")))
		            	 {
		            		 total = Integer.parseInt(copies)-Integer.parseInt(count);
		            	 }
		            	 count=Integer.toString(total);
		              }
		             
		             rs.close();
		             rs2.close();
		             conn.close();		             
		          }						
				catch(SQLException ex)
				{
		            System.out.println("Error in connection: " + ex.getMessage());
		        }
				
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnSearch, "8, 10");
		
		JLabel lblTitle = new JLabel("Title  ");
		frame.getContentPane().add(lblTitle, "4, 12, 2, 1, right, default");
		
		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1, "6, 12, left, default");
		textField_1.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author");
		frame.getContentPane().add(lblAuthor, "4, 14, right, default");
		
		textField_2 = new JTextField();
		frame.getContentPane().add(textField_2, "6, 14, left, default");
		textField_2.setColumns(10);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(btnReset, "8, 14");
		
		JLabel lblResults = new JLabel("RESULTS");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 14));
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		frame.getContentPane().add(scrollPane, "4, 18, 15, 1, left, top");
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);	
		frame.getContentPane().add(lblResults, "4, 16");
		
				model = new DefaultTableModel();
				model.addColumn("Book ID");
		        model.addColumn("Title");
		        model.addColumn("Author Name");
		        model.addColumn("Branch ID");
		        model.addColumn("Copies Inventoried at each Branch");
		        model.addColumn("Available Copies");
		        table.setModel(model);
	}    
}
