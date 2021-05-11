package gui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
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

public class LoginGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField textUsernameReg;
	private JPasswordField textPasswordReg;
	JButton buttonLogin = new JButton("LOGIN");
	JButton buttonRegister = new JButton("REGISTER");
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	private BlFacade businessLogic;
	protected MainGUI previous;

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	public LoginGUI(MainGUI main, BlFacade bl)
	{
		businessLogic = bl;
		previous = main;
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
		
		closeBtn.setBounds(new Rectangle(130, 220, 130, 30));

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		
		this.getContentPane().add(closeBtn, null);
		
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
		
		this.buttonLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String username = textUsernameReg.getText();
				String password = String.valueOf(textPasswordReg.getPassword());
				
				if(username.isEmpty() || password.isEmpty()) 
					validText.setText("At least one compulsory area was not filled correctly");
				else {
					if (businessLogic.validateLogin(username, password)) {
						previous.setUser(businessLogic.getRegisteredUser(username, password));
						goBack();
					}
					else
						validText.setText("Error. Try again.");
				}
			}
		});
		
		this.buttonRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RegisterGUI rg = new RegisterGUI(getThis(), businessLogic);
				rg.setVisible(true);
				close();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelPasswordReg)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPasswordReg))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelUsernameReg)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textUsernameReg, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addComponent(buttonRegister, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(buttonLogin, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelRegister, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(20, Short.MAX_VALUE)
							.addComponent(validText, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(63, Short.MAX_VALUE))
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
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textPasswordReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPasswordReg))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonLogin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonRegister, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addComponent(validText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		this.getContentPane().setLayout(groupLayout);
	}

	private void goBack() {
		this.setVisible(false);
		previous.setVisible(true);
	}
	
	private void close() {
		this.setVisible(false);
	}
	
	private LoginGUI getThis(){
		return this;
	}
}
