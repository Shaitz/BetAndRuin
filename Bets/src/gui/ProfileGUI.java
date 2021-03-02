package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import businessLogic.BlFacade;
import domain.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class ProfileGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private BlFacade businessLogic;
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	private JButton buttonChange = new JButton("Change username and/or password");
	private JButton buttonCancelBet = new JButton("Cancel Bet");
	private JButton buttonAccept = new JButton("Accept");
	private JComboBox comboBets = new JComboBox();
	private JTextField textID;
	private JTextField textUsername;
	private JTextField textPassword;
	private MainGUI previous;
	private User loggedUser;
	
	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	public ProfileGUI(MainGUI main, BlFacade bl) 
	{
		businessLogic = bl;
		previous = main;
		loggedUser = previous.getUser();
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jbInit() throws Exception
	{
		this.setSize(new Dimension(400, 470));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Profile"));

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton_actionPerformed(e);
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textArea.setBackground(SystemColor.menu);
		textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("Profile"));
		
		JLabel labelID = new JLabel("ID: "); //$NON-NLS-1$ //$NON-NLS-2$
		
		JLabel labelUsername = new JLabel("Username: "); //$NON-NLS-1$ //$NON-NLS-2$
		
		JLabel labelPassword = new JLabel("Password: "); //$NON-NLS-1$ //$NON-NLS-2$
		
		textID = new JTextField(loggedUser.getId());
		textID.setEditable(false);
		textID.setColumns(10);
		
		textUsername = new JTextField(loggedUser.getUsername());
		textUsername.setEditable(false);
		textUsername.setColumns(10);
		
		textPassword = new JTextField(loggedUser.getPassword());
		textPassword.setEditable(false);
		textPassword.setColumns(10);
		
		buttonAccept.setVisible(false);
		
		JLabel labelBets = new JLabel("Bets: ");
		
		this.buttonChange.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				buttonChange.setVisible(false);
				
				textUsername.setEditable(true);
				textPassword.setEditable(true);
				
				buttonAccept.setVisible(true);
			}
		});
		
		this.buttonAccept.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				buttonAccept.setVisible(false);
				
				if (textUsername.getText() != null && textUsername.getText() != "" && textUsername.getText() != loggedUser.getUsername())
					businessLogic.changeUsername(loggedUser.getUsername(), loggedUser.getPassword(), textUsername.getText());
				
				if (textPassword.getText() != null && textPassword.getText() != "" && textPassword.getText() != loggedUser.getPassword())
					businessLogic.changePassword(loggedUser.getUsername(), loggedUser.getPassword(), textPassword.getText());
				
				
				textUsername.setEditable(false);
				textPassword.setEditable(false);
				
				buttonChange.setVisible(true);
			}
		});
		
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(92, Short.MAX_VALUE)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(labelBets)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelID, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textID, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(labelUsername)
										.addComponent(labelPassword))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textPassword, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
										.addComponent(textUsername, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
										.addComponent(comboBets, 0, 198, Short.MAX_VALUE)
										.addComponent(buttonCancelBet, Alignment.TRAILING))))
							.addContainerGap(66, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(130, Short.MAX_VALUE)
					.addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(124))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(buttonChange)
					.addContainerGap(95, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(141)
					.addComponent(buttonAccept)
					.addContainerGap(154, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUsername)
						.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPassword)
						.addComponent(textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelBets)
						.addComponent(comboBets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonCancelBet)
					.addGap(18)
					.addComponent(buttonChange)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonAccept)
					.addGap(55)
					.addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		
		

}
	/**
	 * Initialize the contents of the frame.
	 */
	private void jButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
