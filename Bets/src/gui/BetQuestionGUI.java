package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class BetQuestionGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton closeBtn = new JButton("Close");
	private BlFacade businessLogic;
	private Event ev;
	private Question q;
	private JTextField textField;

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}
	
	/**
	 * Create the application.
	 */
	public BetQuestionGUI(BlFacade bl, Event ev, Question q) 
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
		this.setSize(new Dimension(400, 350));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString(""));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton_actionPerformed(e);
			}
		});
		
		JLabel lbBetQuestion = new JLabel("Question:");
		
		JTextArea textBetQuestion = new JTextArea();
		textBetQuestion.setEditable(false);
		
		JLabel lbAmountBet = new JLabel("Amount Bet:");
		
		JTextArea textArea = new JTextArea();
		
		JLabel lbEventDate = new JLabel("Event Date:");
		
		JTextArea textDate = new JTextArea();
		textDate.setEditable(false);
		
		JLabel lbAnswer = new JLabel("Answer:");
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		
		textField = new JTextField();
		textField.setText(ResourceBundle.getBundle("Etiquetas").getString("BetQuestionGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textField.setColumns(10);
		
		JLabel lbAmountLeft = new JLabel("Amount Left:"); //$NON-NLS-1$ //$NON-NLS-2$
		
		JButton btnPlaceBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BetQuestionGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbAnswer)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbBetQuestion)
									.addGap(18)
									.addComponent(textBetQuestion, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbAmountBet)
									.addGap(18)
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbAmountLeft)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(29, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbEventDate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textDate, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(141, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPlaceBet)
						.addComponent(closeBtn))
					.addGap(166))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbBetQuestion)
						.addComponent(textBetQuestion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lbEventDate))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(textDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbAmountBet)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbAmountLeft)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbAnswer)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(btnPlaceBet, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(closeBtn)
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