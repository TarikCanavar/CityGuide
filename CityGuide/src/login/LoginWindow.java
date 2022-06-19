package login;
import java.awt.EventQueue;
import CityGuide.CityGuide;
import windows.MainWindow;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;

public class LoginWindow {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// read files and create classes
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 803, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAppName = new JLabel("City Guide");
		lblAppName.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblAppName.setBounds(240, 38, 321, 51);
		frame.getContentPane().add(lblAppName);
		
		JLabel lblUsername = new JLabel("Mail adress");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblUsername.setBounds(62, 155, 240, 51);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPassword.setBounds(62, 249, 135, 40);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(392, 249, 321, 39);
		frame.getContentPane().add(passwordField);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameField.setBounds(392, 166, 321, 40);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mailAddress = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());

				char valid = CityGuide.getInstance().validateLogin(mailAddress, password);
				if(valid == 'u') {
					password = null; //Security measure.
					MainWindow.main("user");
					frame.dispose(); 
				}
				else if(valid == 'a') {
					password = null; //Security measure.
					MainWindow.main("admin");
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid username or password.","Invalid login!",JOptionPane.ERROR_MESSAGE);		
				}
				usernameField.setText(null);
				passwordField.setText(null);
			}
		});
		btnLogin.setBounds(93, 368, 116, 33);
		frame.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame exit = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(exit, "Do you want to exit?","City Guide",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				 
			}
		});
		btnExit.setBounds(543, 368, 116, 33);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 323, 767, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 120, 767, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow.main(null);
				frame.dispose();
				
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.setBounds(324, 368, 116, 33);
		frame.getContentPane().add(btnRegister);
	}
}
