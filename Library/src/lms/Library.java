package lms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.text.html.ImageView;
import javax.swing.text.Element;
import javax.imageio.ImageIO;

import java.awt.Label;

import javax.swing.JLabel;

import java.awt.Frame;
import java.awt.Color;

import javax.swing.JTextArea;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Library {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnExit;
	private JLabel lblNewLabel_1;
	private JLabel lblLibraryManagementSystem;
	static Connection conn = null;
	String username="";
	String password="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					Library window = new Library();
					window.frmLogin.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Library()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(240, 230, 140));
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("170dlu"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("50dlu"),
				ColumnSpec.decode("center:max(53dlu;default)"),
				ColumnSpec.decode("max(39dlu;default)"),
				ColumnSpec.decode("max(99dlu;default)"),
				ColumnSpec.decode("center:max(118dlu;default)"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("50dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("100dlu"),
				RowSpec.decode("100dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("50dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("50dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
			
			lblLibraryManagementSystem = new JLabel("RICHARDSON PUBLIC LIBRARY");
			lblLibraryManagementSystem.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			frmLogin.getContentPane().add(lblLibraryManagementSystem, "3, 4, 7, 1, center, center");
			
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(Library.class.getResource("/lms/library (1).jpg")));
			frmLogin.getContentPane().add(lblNewLabel_1, "5, 7, 5, 4");
			
			
			JLabel lblNewLabel = new JLabel("Username   ");
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			frmLogin.getContentPane().add(lblNewLabel, "5, 12, center, center");
			
			textField = new JTextField();
			textField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			frmLogin.getContentPane().add(textField, "6, 12, 2, 1, fill, top");
			textField.setColumns(10);
				
				JLabel lblPassword = new JLabel("Password   ");
				System.out.println();
				lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				frmLogin.getContentPane().add(lblPassword, "8, 12, right, center");
				
				passwordField = new JPasswordField();
				passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				frmLogin.getContentPane().add(passwordField, "9, 12, fill, center");
				
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
					
					btnExit = new JButton("Exit");
					btnExit.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked(MouseEvent arg0)
						{
						      System.exit(0);
						}
					});
					
					btnNewButton_1 = new JButton("Reset");
					btnNewButton_1.setForeground(new Color(255, 255, 255));
					btnNewButton_1.setBackground(new Color(218, 165, 32));
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							textField.setText("");
							passwordField.setText("");
							
						}
					});
					btnNewButton = new JButton("Login");
					btnNewButton.addMouseListener(new MouseAdapter()
					{
						@Override
						public void mouseClicked(MouseEvent arg0)
						{
							String text = textField.getText();
							char[] pass=passwordField.getPassword();
							String passcode = new String(pass);
							
							try
					        {
					             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Rooney10");    
					             Statement stmt = conn.createStatement();
					             stmt.execute("use library;");
					             ResultSet rs = stmt.executeQuery("SELECT * FROM login;");
					             while (rs.next()) 
					             {                 
					                 username = rs.getString("username");
					                 password=rs.getString("password");
					                 if(text.equals(username) & passcode.equals(password))
					                 {
					                	 System.out.println("Login successful");
					                 }
					                 else
					                	 System.out.println("Login unsuccessful");
					               }
					             rs.close();
					             conn.close();				             
				              }						
							catch(SQLException ex)
							{
					            System.out.println("Error in connection: " + ex.getMessage());
					        }
							
							Welcome w=new Welcome();
							Welcome.main(null);
						
						}
					});
					btnNewButton.setForeground(new Color(255, 255, 255));
					btnNewButton.setBackground(new Color(218, 165, 32));
					btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
					
						frmLogin.getContentPane().add(btnNewButton, "7, 14, right, default");
					btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
					frmLogin.getContentPane().add(btnNewButton_1, "8, 14, center, default");
					btnExit.setForeground(new Color(255, 255, 255));
					btnExit.setBackground(new Color(218, 165, 32));
					btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
					frmLogin.getContentPane().add(btnExit, "9, 14, left, default");
	}
	
	
}
