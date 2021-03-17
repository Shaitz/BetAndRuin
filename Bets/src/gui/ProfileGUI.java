package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import businessLogic.BlFacade;
import domain.Bet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.Color;

public class ProfileGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private BlFacade businessLogic;
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	private JButton buttonChange = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ChangeUP"));
	private JButton buttonAccept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
	private JButton btnViewBetInfo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ProfileGUI.btnNewButton.text"));
	private JComboBox<String> comboBets = new JComboBox<String>();
	private JTextField textID;
	private JTextField textUsername;
	private JTextField textPassword;
	private MainGUI previous;
	private HashMap<String, Bet> betsAndNames = new HashMap<String, Bet>();
	
	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	public ProfileGUI(MainGUI main, BlFacade bl) 
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
		this.setSize(new Dimension(400, 470));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Profile"));

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton_actionPerformed(e);
			}
		});
		
		this.initializeBrowseQuestionsBtn();
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textArea.setBackground(SystemColor.menu);
		textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("Profile"));
		
		JLabel labelID = new JLabel("ID: "); 
		
		JLabel labelUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username")); 
		
		JLabel labelPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password")); 
		
		textID = new JTextField(String.valueOf(businessLogic.getUser().getId()));
		textID.setEditable(false);
		textID.setColumns(10);
		
		textUsername = new JTextField(businessLogic.getUser().getUsername());
		textUsername.setEditable(false);
		textUsername.setColumns(10);
		
		textPassword = new JTextField(businessLogic.getUser().getPassword());
		textPassword.setEditable(false);
		textPassword.setColumns(10);
		
		buttonAccept.setVisible(false);
		
		JLabel labelBets = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Bets"));
		
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
				
				if (textUsername.getText() != null && textUsername.getText() != "" && textUsername.getText() != businessLogic.getUser().getUsername())
					businessLogic.changeUsername(businessLogic.getUser().getUsername(), businessLogic.getUser().getPassword(), textUsername.getText());
				
				if (textPassword.getText() != null && textPassword.getText() != "" && textPassword.getText() != businessLogic.getUser().getPassword())
					businessLogic.changePassword(businessLogic.getUser().getUsername(), businessLogic.getUser().getPassword(), textPassword.getText());
				
				
				textUsername.setEditable(false);
				textPassword.setEditable(false);
				
				buttonChange.setVisible(true);
				
				previous.setUser(businessLogic.getUser());
			}
		});
		
		JTextArea removeText = new JTextArea();
		removeText.setBackground(new Color(240, 240, 240));
		removeText.setText(ResourceBundle.getBundle("Etiquetas").getString("ProfileGUI.removeText.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		

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
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textPassword, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
										.addComponent(textUsername, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
										.addComponent(comboBets, 0, 205, Short.MAX_VALUE)
										.addComponent(btnViewBetInfo))))
							.addContainerGap(66, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(130, Short.MAX_VALUE)
					.addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(124))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(buttonChange)
					.addContainerGap(215, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(141)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(removeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonAccept))
					.addContainerGap(178, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnViewBetInfo)
					.addGap(13)
					.addComponent(buttonChange)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonAccept)
					.addGap(15)
					.addComponent(removeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		// Adds all the bets of the user to the comboBox so it shows a list with bets
		String question;
		for(Bet b : businessLogic.getUser().getBets()) {
			question = b.getQuestion().getQuestion() + " || " + b.getPlacedBet();
			betsAndNames.put(question, b);
			comboBets.addItem(question);
		}
}
	
	private void initializeBrowseQuestionsBtn() {
		btnViewBetInfo = new JButton();
		btnViewBetInfo.setText(ResourceBundle.getBundle("Etiquetas").
				getString("BrowseQuestions"));
		btnViewBetInfo.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BetInfoGUI viewBetInfo = new BetInfoGUI(businessLogic, betsAndNames, (String)comboBets.getSelectedItem());
				viewBetInfo.setVisible(true);
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void jButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
