package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import domain.Question;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class CheckAnswersGUI extends JFrame {

	private JPanel contentPane;
	private JTextField answerTextField;
	private Question question;
	private BlFacade businessLogic;
	private EventInfoGUI previous;
	private JTextField warningTextField;
	private JComboBox<String> answerBox;

	/**
	 * Create the frame.
	 */
	public CheckAnswersGUI(BlFacade bl, Question q, EventInfoGUI eig) {
		question = q;
		previous = eig;
		businessLogic = bl;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel newAnswerLabel = new JLabel("New answer:");
		newAnswerLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		answerTextField = new JTextField();
		answerTextField.setColumns(10);
		
		JButton addAnswerBtn = new JButton("Add answer");
		
		JButton goBackBtn = new JButton("Go back");
		
		warningTextField = new JTextField();
		warningTextField.setEditable(false);
		warningTextField.setBackground(SystemColor.menu);
		warningTextField.setColumns(10);
		
		JButton removeBtn = new JButton("Remove answer");
		
		JLabel lblNewLabel = new JLabel("Existing answers:");
		
		answerBox = new JComboBox<String>();
		if(q.getAnswers() != null) {
			Iterator<String> it = q.getAnswers().iterator();
			while (it.hasNext())
				answerBox.addItem(it.next());
		}
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(warningTextField, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(newAnswerLabel)
							.addGap(18)
							.addComponent(answerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(answerBox, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addAnswerBtn)
							.addGap(18)
							.addComponent(removeBtn)
							.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
							.addComponent(goBackBtn, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(answerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel)
							.addComponent(answerBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(newAnswerLabel))
					.addGap(26)
					.addComponent(warningTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addAnswerBtn)
						.addComponent(removeBtn)
						.addComponent(goBackBtn))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		addAnswerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!answerTextField.getText().equals("")) {
					String answer = answerTextField.getText();
					if(businessLogic.addAnswerToQuestion(question, answer)) {
						answerBox.addItem(answer);
						warningTextField.setText("Answer added successfully");
					}
					else
						warningTextField.setText("This answer already exists");
				}
				else
					warningTextField.setText("Please insert something in the text area");
			}
		});
		
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String answer = (String) answerBox.getSelectedItem();
					q.removeAnswer(answer);
					answerBox.removeItem(answer);;
				}
				catch(NullPointerException j){
					warningTextField.setText("You haven't selected any answer to delete");
				}
			}
		});
		
		goBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
	}
	
	public void close() {
		this.setVisible(false);
		previous.setVisible(true);
	}
}
