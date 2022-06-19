package login;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import CityGuide.CityGuide;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class RegisterWindow {

	private JFrame frame;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField mailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow window = new RegisterWindow();
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
	public RegisterWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 775, 487);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(45, 167, 288, 41);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setBounds(396, 167, 300, 41);
		frame.getContentPane().add(surnameField);
		surnameField.setColumns(10);
		
		mailField = new JTextField();
		mailField.setBounds(45, 284, 288, 41);
		frame.getContentPane().add(mailField);
		mailField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(45, 121, 131, 35);
		frame.getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSurname.setBounds(396, 118, 101, 41);
		frame.getContentPane().add(lblSurname);
		
		JLabel lblEmail = new JLabel("Mail address:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(45, 238, 156, 35);
		frame.getContentPane().add(lblEmail);
		
		JLabel txtrANewJourney = new JLabel();
		txtrANewJourney.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtrANewJourney.setBackground(UIManager.getColor("Button.background"));
		txtrANewJourney.setText("<html>A new journey begins.<br/>And the first step is registering.</html>");
		txtrANewJourney.setBounds(186, 23, 457, 100);
		frame.getContentPane().add(txtrANewJourney);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(396, 238, 101, 32);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(396, 284, 300, 41);
		frame.getContentPane().add(passwordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if(!CityGuide.getInstance().createUser(nameField.getText(), surnameField.getText(), mailField.getText(), String.valueOf(passwordField.getPassword()))){
					JOptionPane.showMessageDialog(frame, "Please check the given informations.");
				}
				else {
					 LoginWindow.main(null);
					 frame.dispose();
				}
				
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.setBounds(62, 369, 622, 41);
		frame.getContentPane().add(btnRegister);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		      LoginWindow.main(null);
		    }
		});
	}
}
