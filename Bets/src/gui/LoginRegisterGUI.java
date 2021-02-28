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

public class LoginRegisterGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField textUsernameReg;
	private JPasswordField textPasswordReg;
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	private BlFacade businessLogic;

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	public LoginRegisterGUI(BlFacade bl)
	{
		businessLogic = bl;
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
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("LoginRegister"));
		
		closeBtn.setBounds(new Rectangle(274, 419, 130, 30));

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton_actionPerformed(e);
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
		
		JButton buttonLogin = new JButton("LOGIN");
		
		JTextArea validText = new JTextArea();
		validText.setEditable(false);
		
		JButton buttonRegister = new JButton("REGISTER");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void jButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
