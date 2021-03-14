package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;

import businessLogic.BlFacade;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BetInfoGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton closeBtn = new JButton("Close");
	private JButton btnRemoveBet = new JButton("Remove Bet");
	private BlFacade businessLogic;

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	/**
	 * Create the application.
	 */
	public BetInfoGUI(BlFacade bl) 
	{
		businessLogic = bl;
		try 
		{
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void jbInit() throws Exception
	{
		frame = new JFrame();
		this.setSize(new Dimension(400, 300));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString(""));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton_actionPerformed(e);
			}
		});
		
		JLabel lbBetQuestion = new JLabel("Question: ");
		
		JTextArea textBetQuestion = new JTextArea();
		textBetQuestion.setEditable(false);
		
		JLabel lbAmountBet = new JLabel("Amount Bet:");
		
		JTextArea textBetAmount = new JTextArea();
		textBetAmount.setEditable(false);
		
		JLabel lbEventDate = new JLabel("Event Date:");
		
		JTextArea textBetDate = new JTextArea();
		textBetDate.setEditable(false);
		
		JLabel lbAnswer = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BetInfoGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		JTextArea textBetAnswer = new JTextArea();
		textBetAnswer.setEditable(false);
		textBetAnswer.setText(ResourceBundle.getBundle("Etiquetas").getString("BetInfoGUI.textArea_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbAmountBet)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textBetAmount, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbBetQuestion)
									.addGap(18)
									.addComponent(textBetQuestion, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lbAnswer)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textBetAnswer))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lbEventDate)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textBetDate, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
									.addGap(43)
									.addComponent(btnRemoveBet))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(closeBtn)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbBetQuestion)
						.addComponent(textBetQuestion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbAmountBet)
						.addComponent(textBetAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbEventDate)
						.addComponent(textBetDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbAnswer)
						.addComponent(textBetAnswer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemoveBet))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(closeBtn)
					.addGap(30))
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
