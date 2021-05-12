package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import domain.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AddFundsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField bankAccount;
	private JTextField amountField;
	private BlFacade businessLogic;

	/**
	 * Launch the application.
	 */
	public AddFundsGUI(BlFacade bl)
	{
		businessLogic = bl;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the frame.
	 */
	public void jbInit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(416, 160));
		setBounds(100, 100, 300, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton addFundsBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
		
		JButton goBackBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		
		JLabel lblNewLabel = new JLabel("Bank Account");
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Funds"));
		
		bankAccount = new JTextField();
		bankAccount.setColumns(10);
		
		amountField = new JTextField();
		amountField.setColumns(10);
		
		JTextArea errorCheck = new JTextArea();
		errorCheck.setEditable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(amountField)
						.addComponent(bankAccount, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
					.addContainerGap(64, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(addFundsBtn)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(goBackBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(errorCheck, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(bankAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addFundsBtn)
						.addComponent(goBackBtn)
						.addComponent(errorCheck, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		addFundsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String amount = amountField.getText();
				int accountLength = bankAccount.getText().toString().length();
				if(!amount.equals("") && accountLength == 16) {
					double toAdd = -1;
					try {
						toAdd = businessLogic.addMoneyToUser(businessLogic.getUser().getId(),Double.parseDouble(amount));
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					if(toAdd == -1) {
						System.out.println("Something went wrong");
					}
					else {
						closeTab();
					}
				}
				else
					errorCheck.setText("Invalid Card");
			}
		});
		
		goBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeTab();
			}
		});
	}
	
	public void closeTab() {
		this.setVisible(false);
	}
}
