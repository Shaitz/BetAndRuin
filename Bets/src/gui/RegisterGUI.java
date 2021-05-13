package gui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.LayoutStyle.ComponentPlacement;

import businessLogic.BlFacade;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;

public class RegisterGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField textUsernameReg;
	private JPasswordField textPasswordReg;
	JButton buttonRegister = new JButton("REGISTER");
	private BlFacade businessLogic;
	private LoginGUI previous;
	private JTextField eMailTextField;
	private JPasswordField cPasswordField;

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	public RegisterGUI(LoginGUI login, BlFacade bl)
	{
		businessLogic = bl;
		previous = login;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jbInit() throws Exception
	{	
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(400, 300));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("LoginRegister"));
		
		JLabel labelRegister = new JLabel("Register/Login");
		labelRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel labelUsernameReg = new JLabel("Username:");
		
		JLabel labelPasswordReg = new JLabel("Password:");
		
		textUsernameReg = new JTextField();
		textUsernameReg.setColumns(10);
		
		textPasswordReg = new JPasswordField();
		
		
		JTextArea validText = new JTextArea();
		validText.setBackground(new Color(240, 240, 240));
		validText.setEditable(false);
		
		this.buttonRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String username = textUsernameReg.getText();
				String password = String.valueOf(textPasswordReg.getPassword());
				String cPassword = String.valueOf(cPasswordField.getPassword());
				String eMail = eMailTextField.getText();
				
				if(username.isEmpty() || password.isEmpty() || cPassword.isEmpty() || eMail.isEmpty())
					validText.setText("At least one compulsory area was not filled correctly");
				else {
					if(password.equals(cPassword)) {
						if(checkEmail(eMail)) {
							if (businessLogic.registerUser(username, password, eMail)) {
								previous.previous.setUser(businessLogic.getRegisteredUser(username, password));
								close();
							}
							else
								validText.setText("Error. Try again.");
						}
						else
							validText.setText("Invalid eMail.");
					}
					else
						validText.setText("Passwords don't match.");
				}
			}
		});
		
		JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		
		JLabel cPasswordLable = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RepeatPassword"));
		
		eMailTextField = new JTextField();
		eMailTextField.setColumns(10);
		
		JLabel eMailLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("eMail")); //$NON-NLS-1$ //$NON-NLS-2$
		
		cPasswordField = new JPasswordField();
		
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(eMailLabel)
								.addComponent(labelUsernameReg)
								.addComponent(labelPasswordReg)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(buttonRegister, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addComponent(cPasswordLable)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cPasswordField, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
								.addComponent(textPasswordReg, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
								.addComponent(labelRegister, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addComponent(textUsernameReg, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
								.addComponent(eMailTextField, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(closeBtn))
						.addComponent(validText, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelRegister)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textUsernameReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelUsernameReg))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPasswordReg)
						.addComponent(textPasswordReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cPasswordLable)
						.addComponent(cPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(eMailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(eMailLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(validText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonRegister)
						.addComponent(closeBtn))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		this.getContentPane().setLayout(groupLayout);
	}
	
	private boolean checkEmail(String eMail){
		boolean ret = false;
		if(eMail.contains("@")) {
			String[] split = eMail.split("@");
			if(split.length == 2 && split[0].length() > 1 && split[1].contains(".")) {
				ret = true;
			}
		}
		return ret;
	}
	
	private void goBack() {
		this.setVisible(false);
		previous.setVisible(true);
	}
	
	private void close() {
		this.setVisible(false);
	}
}
