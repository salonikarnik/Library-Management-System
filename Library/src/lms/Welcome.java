

  package lms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JMenuBar;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome {

	private JFrame frmWelcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.getContentPane().setBackground(new Color(238, 232, 170));
		frmWelcome.setTitle("Welcome");
		frmWelcome.setBounds(100, 100, 599, 300);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20dlu"),
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
		
		JLabel lblLibraryManagementSystem = new JLabel("RICHARDSON PUBLIC LIBRARY");
		lblLibraryManagementSystem.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		frmWelcome.getContentPane().add(lblLibraryManagementSystem, "4, 1, 7, 1, center, center");
		
		JButton btnHome = new JButton("Exit");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frmWelcome.getContentPane().add(btnHome, "2, 2");
		
		JButton btnNewButton = new JButton("Inventory");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BookSearch s = new BookSearch();
				BookSearch.main(null);
			}
		});
		frmWelcome.getContentPane().add(btnNewButton, "4, 2");
		
		JButton btnBookTransactions = new JButton("Issue Book");
		btnBookTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckIn c = new CheckIn();
				CheckIn.main(null);
				
			}
		});
		frmWelcome.getContentPane().add(btnBookTransactions, "6, 2");
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckOut c = new CheckOut();
				CheckOut.main(null);
			}
		});
		frmWelcome.getContentPane().add(btnReturnBook, "8, 2");
		
		JButton btnNewButton_1 = new JButton("Member Details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Members c = new Members();
				Members.main(null);
			}
		});
		frmWelcome.getContentPane().add(btnNewButton_1, "10, 2");
		
		JButton btnFines = new JButton("Fines");
		btnFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fines c = new Fines();
				Fines.main(null);
			}
		});
		frmWelcome.getContentPane().add(btnFines, "12, 2");
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setIcon(new ImageIcon(Welcome.class.getResource("/lms/library (1).jpg")));
		frmWelcome.getContentPane().add(label_3, "2, 4, 7, 14");
		
		JLabel lblLibraryHours = new JLabel("Library Hours");
		frmWelcome.getContentPane().add(lblLibraryHours, "10, 4, 3, 1, center, default");
		
		JLabel lblNewLabel = new JLabel("Monday");
		frmWelcome.getContentPane().add(lblNewLabel, "10, 5, center, default");
		
		JLabel lblNewLabel_1 = new JLabel("10 a.m. to 9 p.m.");
		frmWelcome.getContentPane().add(lblNewLabel_1, "12, 5");
		
		JLabel lblTuesday = new JLabel("Tuesday");
		frmWelcome.getContentPane().add(lblTuesday, "10, 7, center, default");
		
		JLabel label = new JLabel("10 a.m. to 9 p.m.");
		frmWelcome.getContentPane().add(label, "12, 7");
		
		JLabel lblWednesday = new JLabel("Wednesday");
		frmWelcome.getContentPane().add(lblWednesday, "10, 9, center, default");
		
		JLabel label_1 = new JLabel("10 a.m. to 9 p.m.");
		frmWelcome.getContentPane().add(label_1, "12, 9");
		
		JLabel lblThursday = new JLabel("Thursday");
		frmWelcome.getContentPane().add(lblThursday, "10, 11, center, default");
		
		JLabel label_2 = new JLabel("10 a.m. to 9 p.m.");
		frmWelcome.getContentPane().add(label_2, "12, 11");
		
		JLabel lblFriday = new JLabel("Friday");
		frmWelcome.getContentPane().add(lblFriday, "10, 13, center, default");
		
		JLabel lblAmTo_1 = new JLabel("10 a.m. to 6 p.m.");
		frmWelcome.getContentPane().add(lblAmTo_1, "12, 13");
		
		JLabel lblSaturday = new JLabel("Saturday");
		frmWelcome.getContentPane().add(lblSaturday, "10, 15, center, default");
		
		JLabel lblAmTo = new JLabel("10 a.m. to 6 p.m.");
		frmWelcome.getContentPane().add(lblAmTo, "12, 15");
		
		JLabel lblSunday = new JLabel("Sunday");
		frmWelcome.getContentPane().add(lblSunday, "10, 17, center, default");
		
		JLabel lblPmTo = new JLabel("2 p.m. to 6 p.m.");
		frmWelcome.getContentPane().add(lblPmTo, "12, 17");
	}

}
