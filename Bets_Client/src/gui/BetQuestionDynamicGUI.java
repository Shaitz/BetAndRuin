package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;

import businessLogic.BlFacade;
import domain.Event;
import domain.Question;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class BetQuestionDynamicGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton closeBtn = new JButton("Close");
	private JButton btnPlaceBet = new JButton("Place Bet");
	private JLabel lbBetQuestion = new JLabel("Question:");
	private JTextArea textBetQuestion = new JTextArea();
	private JLabel lbAmountBet = new JLabel("Amount Bet:");	
	private JLabel lbEventDate = new JLabel("Event Date:");
	private JLabel lbAnswer = new JLabel("Answer:");
	private JLabel lbAmountLeft = new JLabel("Amount Left:");
	private JTextArea textAmountLeft = new JTextArea();
	private JTextArea textDate = new JTextArea();
	private JTextArea textPrint = new JTextArea();
	private JButton btnCheck = new JButton("Check");
	private BlFacade businessLogic;
	private Event ev;
	private Question q;
	private JTextField textAmount;
	private final JComboBox<String> comboBox = new JComboBox<String>();

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	/**
	 * Create the application.
	 */
	public BetQuestionDynamicGUI(BlFacade bl, Event ev, Question q) 
	{
		businessLogic = bl;
		this.ev = ev;
		this.q = q;
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
		this.setSize(new Dimension(550, 380));
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
		textDate.setEditable(false);
		textAmountLeft.setEditable(false);
		textAmount = new JTextField();
		textAmount.setColumns(10);
		textPrint.setEditable(false);
		textBetQuestion.setText(this.q.getQuestion());
		textDate.setText(ev.getEventDate().toString());
		
		Iterator<String> it = businessLogic.getAnswersOfQuestion(q).iterator();
		while(it.hasNext())
			comboBox.addItem(it.next());
		
		
		this.btnPlaceBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (!textAmount.getText().equals(""))
				{
//					Iterator<Question> it = ev.getQuestions().iterator();
//					boolean found = false;
//					Question actual = null;
//					System.out.println(ev);
//					while (it.hasNext() && !found) {
//						actual = (Question) it.next();
//						if(actual.getQuestionNumber().equals(q.getQuestionNumber()))
//							found = true;
//					}
//					if(found && businessLogic.placeBet(actual, Double.parseDouble(textAmount.getText()), textAnswer.getText()))
					if(businessLogic.placeBet(q, Double.parseDouble(textAmount.getText()), (String) comboBox.getSelectedItem()))
					{
						textPrint.setText("Bet Successful!");
					}
					else
						textPrint.setText("Error. Try Again");			
				}
				else
					textPrint.setText("Error. Try Again");
				businessLogic.refreshUser();
			}
		});
		
		this.btnCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				checkAmount();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbBetQuestion)
									.addGap(18)
									.addComponent(textBetQuestion, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbEventDate)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textDate, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lbAnswer)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lbAmountBet)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(11)
											.addComponent(lbAmountLeft)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textAmountLeft, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
									.addComponent(btnCheck, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(178, Short.MAX_VALUE)
							.addComponent(btnPlaceBet)
							.addGap(26)
							.addComponent(textPrint, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(79, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(208)
					.addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(255, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbBetQuestion)
						.addComponent(textBetQuestion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbEventDate)
						.addComponent(textDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbAmountBet)
						.addComponent(lbAmountLeft)
						.addComponent(textAmountLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCheck))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbAnswer)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlaceBet, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPrint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(closeBtn)
					.addGap(31))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void checkAmount()
	{
		Double currentAmount = businessLogic.getUser().getWallet().getCurrency();
		Double amountBet = Double.parseDouble(textAmount.getText());
		Double answer = currentAmount - amountBet;
		textAmountLeft.setText(Double.toString(answer)); // wallet - amount bet
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void jButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}