package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginRegisterGUI {

	private JFrame frame;
	private JTextField textUsernameReg;
	private JPasswordField textPasswordReg;
	private JTextField textUsernameLog;
	private JPasswordField textPasswordLog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginRegisterGUI window = new LoginRegisterGUI();
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
	public LoginRegisterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel labelRegister = new JLabel("Register");
		labelRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel labelUsernameReg = new JLabel("Username:");
		
		JLabel labelPasswordReg = new JLabel("Password:");
		
		JLabel labelLogin = new JLabel("Login");
		labelLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textUsernameReg = new JTextField();
		textUsernameReg.setColumns(10);
		
		textPasswordReg = new JPasswordField();
		
		JButton buttonRegister = new JButton("REGISTER");
		
		JLabel labelUsernameLog = new JLabel("Username:");
		
		JLabel labelPasswordLog = new JLabel("Password:");
		
		textUsernameLog = new JTextField();
		textUsernameLog.setColumns(10);
		
		textPasswordLog = new JPasswordField();
		
		JButton btnLogin = new JButton("LOGIN");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(143)
							.addComponent(buttonRegister))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelRegister, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(labelPasswordReg)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textPasswordReg))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(labelUsernameReg)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textUsernameReg, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(labelPasswordLog, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPasswordLog, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(labelUsernameLog, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textUsernameLog, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
								.addComponent(labelLogin)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(144)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelRegister)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUsernameReg)
						.addComponent(textUsernameReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPasswordReg)
						.addComponent(textPasswordReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(buttonRegister)
					.addGap(4)
					.addComponent(labelLogin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUsernameLog)
						.addComponent(textUsernameLog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPasswordLog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPasswordLog))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogin)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
