package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;

import businessLogic.BlFacade;
import domain.Bet;

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
	private JTextArea textBetAnswer = new JTextArea();
	private JLabel lbAnswer = new JLabel();
	private JTextArea textBetDate = new JTextArea();
	private JLabel lbEventDate = new JLabel("Event Date:");
	private JTextArea textBetAmount = new JTextArea();
	private JLabel lbAmountBet = new JLabel("Amount Bet:");
	private JLabel lbBetQuestion = new JLabel("Question: ");	
	private JTextArea textBetQuestion = new JTextArea();
	private JLabel lbAns = new JLabel("Answer");
	private BlFacade businessLogic;
	private HashMap<String, Bet> bets;
	private String betQ;
	private JTextArea textRemoved = new JTextArea();

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	/**
	 * Create the application.
	 */
	public BetInfoGUI(BlFacade bl, HashMap<String, Bet> bets, String betQ) 
	{
		businessLogic = bl;
		this.bets = bets;
		this.betQ = betQ;
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
		
		textBetQuestion.setEditable(false);
		textBetAmount.setEditable(false);
		textBetDate.setEditable(false);
		textBetAnswer.setEditable(false);
		textRemoved.setEditable(false);
		
		initializeTexts();
		
		this.btnRemoveBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Bet currentBet = bets.get(betQ);
				if(businessLogic.removeBet(currentBet))
					textRemoved.setText("Removed Successfully.");
				else
					textRemoved.setText("Error.");
			}
		});
		
		
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
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lbEventDate)
										.addComponent(lbAnswer)
										.addComponent(lbAns))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textBetAnswer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textBetDate, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
									.addGap(43)
									.addComponent(btnRemoveBet))
								.addComponent(textRemoved, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(closeBtn)))
					.addGap(26))
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
						.addComponent(btnRemoveBet)
						.addComponent(lbAns)
						.addComponent(textBetAnswer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textRemoved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(closeBtn)
					.addGap(30))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void initializeTexts()
	{
		Bet currentBet = bets.get(betQ);
		textBetQuestion.setText(currentBet.getQuestion().getQuestion());
		textBetAmount.setText(Double.toString(currentBet.getPlacedBet()));
		textBetDate.setText(""); // need date
		textBetAnswer.setText(currentBet.getAnswer());
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void jButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
