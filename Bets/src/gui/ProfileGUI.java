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

import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.Color;

public class ProfileGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private BlFacade businessLogic;
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	private JButton buttonChange = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ChangeUP"));
	private JButton buttonAccept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
	private JButton btnViewBetInfo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BetInfo"));
	private JButton btnAddMoney = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
	private JButton btnPastBets = new JButton("Past Bets");
	private JComboBox<String> comboBets = new JComboBox<String>();
	private JTextField textID;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextArea removeText = new JTextArea();
	private MainGUI previous;
	private HashMap<String, Bet> betsAndNames = new HashMap<String, Bet>();
	private final JLabel eMailLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("eMail")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextField eMailTextField = new JTextField();
	
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
		closeBtn.setBounds(115, 390, 130, 30);

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton_actionPerformed(e);
			}
		});
		
		btnAddMoney.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{
				AddFundsGUI addMoneyGUI = new AddFundsGUI(businessLogic);
				addMoneyGUI.setVisible(true);
				jButton_actionPerformed(e);
			}
		});
		
		btnPastBets.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{
				if(businessLogic.getUser().getPastBets() != null)
					if (businessLogic.getUser().getPastBets().size() > 0)
					{
						PastBetsGUI PastBetsGUI = new PastBetsGUI(businessLogic);
						PastBetsGUI.setVisible(true);
						jButton_actionPerformed(e);
					}
			}
		});
		
		if(businessLogic.getUser().getBets() != null) {
			if (!businessLogic.getUser().getBets().isEmpty())
				this.initializeBrowseQuestionsBtn();
			else
				btnViewBetInfo.setEnabled(false);
		}
		eMailTextField.setEditable(false);
		eMailTextField.setText(businessLogic.getUser().getMail()); //$NON-NLS-1$ //$NON-NLS-2$
		eMailTextField.setBounds(138, 160, 153, 20);
		eMailTextField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(92, 21, 282, 35);
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textArea.setBackground(SystemColor.menu);
		textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("Profile"));
		
		JLabel labelID = new JLabel("ID: ");
		labelID.setBounds(47, 67, 81, 24);
		JLabel lbMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Money"));
		lbMoney.setBounds(47, 213, 74, 14);
		JLabel labelUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		labelUsername.setBounds(47, 102, 81, 14);
		JLabel labelPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		labelPassword.setBounds(47, 132, 81, 14);
		
		textID = new JTextField(String.valueOf(businessLogic.getUser().getId()));
		textID.setBounds(138, 67, 107, 20);
		textID.setEditable(false);
		textID.setColumns(10);
		
		textUsername = new JTextField(businessLogic.getUser().getUsername());
		textUsername.setBounds(138, 98, 153, 20);
		textUsername.setEditable(false);
		textUsername.setColumns(10);
		
		textPassword = new JTextField(businessLogic.getUser().getPassword());
		textPassword.setBounds(138, 129, 153, 20);
		textPassword.setEditable(false);
		textPassword.setColumns(10);
		buttonAccept.setBounds(151, 347, 65, 23);
		
		buttonAccept.setVisible(false);
		
		JLabel labelBets = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Bets"));
		labelBets.setBounds(47, 260, 65, 14);
		buttonChange.setBounds(82, 317, 226, 23);
		
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
				
				String username = null;
				String password = null;
				boolean uChange = false;
				boolean pChange = false;
				
				if (textUsername.getText() != null && !textUsername.getText().equals("") && !textUsername.getText().equals(businessLogic.getUser().getUsername()))
//					businessLogic.changeUsername(businessLogic.getUser().getUsername(), businessLogic.getUser().getPassword(), textUsername.getText());
					username = textUsername.getText();
				
				if (textPassword.getText() != null && !textPassword.getText().equals("") && !textPassword.getText().equals(businessLogic.getUser().getPassword()))
//					businessLogic.changePassword(businessLogic.getUser().getUsername(), businessLogic.getUser().getPassword(), textPassword.getText());
					password = textPassword.getText();
				
				if(username != null && password != null)
					if(businessLogic.getRegisteredUser(username, password) == null) {
						uChange = true;
						pChange = true;
					}
				else if(username != null)
					if(businessLogic.getRegisteredUser(username, businessLogic.getUser().getPassword()) == null)
						uChange = true;
				else if(password != null)
					if(businessLogic.getRegisteredUser(businessLogic.getUser().getUsername(), password) == null)
						pChange = true;
				
				if(uChange)
					businessLogic.changeUsername(businessLogic.getUser().getUsername(), businessLogic.getUser().getPassword(), textUsername.getText());
				if(pChange)
					businessLogic.changePassword(businessLogic.getUser().getUsername(), businessLogic.getUser().getPassword(), textPassword.getText());
				
				textUsername.setEditable(false);
				textPassword.setEditable(false);
				
				buttonChange.setVisible(true);
				
				previous.setUser(businessLogic.getUser());
			}
		});
		
		removeText.setBounds(222, 346, 5, 22);
		
		removeText.setBackground(new Color(240, 240, 240));
		removeText.setText(ResourceBundle.getBundle("Etiquetas").getString("ProfileGUI.removeText.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		
		JTextArea textMoney = new JTextArea(Double.toString(businessLogic.getUser().getWallet().getCurrency()));
		textMoney.setBounds(136, 208, 55, 22);
		textMoney.setEditable(false);
		getContentPane().setLayout(null);
		getContentPane().add(textArea);
		getContentPane().add(labelID);
		getContentPane().add(textID);
		getContentPane().add(labelUsername);
		getContentPane().add(textUsername);
		getContentPane().add(buttonAccept);
		getContentPane().add(removeText);
		getContentPane().add(closeBtn);
		getContentPane().add(lbMoney);
		getContentPane().add(textMoney);
		getContentPane().add(labelPassword);
		getContentPane().add(textPassword);
		getContentPane().add(buttonChange);
		getContentPane().add(labelBets);
		comboBets.setBounds(133, 256, 175, 22);
		getContentPane().add(comboBets);
		btnViewBetInfo.setBounds(201, 289, 107, 23);
		getContentPane().add(btnViewBetInfo);
		
		btnAddMoney.setBounds(201, 209, 107, 23);
		getContentPane().add(btnAddMoney);
		eMailLabel.setBounds(47, 163, 46, 14);
		
		getContentPane().add(eMailLabel);
		
		getContentPane().add(eMailTextField);
		
		btnPastBets.setBounds(82, 289, 109, 23);
		getContentPane().add(btnPastBets);
		
		// Adds all the bets of the user to the comboBox so it shows a list with bets
		String question;
		if(businessLogic.getUser().getBets() != null)
			for(Bet b : businessLogic.getUser().getBets()) {
				question = b.getQuestion().getQuestion() + " || " + b.getPlacedBet();
				betsAndNames.put(question, b);
				comboBets.addItem(question);
			}
}
	
	private void initializeBrowseQuestionsBtn() {
		btnViewBetInfo.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{
				BetInfoGUI viewBetInfo = new BetInfoGUI(businessLogic, betsAndNames, (String)comboBets.getSelectedItem());
				viewBetInfo.setVisible(true);
				jButton_actionPerformed(e);
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
