package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BlFacade;
import domain.Event;
import domain.Question;
import enums.QuestionTypes;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EventInfoGUI extends JFrame {

	private JPanel contentPane;
	private Event event;
	private CreateQuestionGUI previous;
	private BlFacade businessLogic;

	/**
	 * Create the frame.
	 */
	public EventInfoGUI(BlFacade bl, Event e, CreateQuestionGUI cqg) {
		
		event = e;
		previous = cqg;
		businessLogic = bl;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel eventLabel = new JLabel("Event:");
		
		JLabel dateLabel = new JLabel("Date:");
		
		JTextArea dateTextArea = new JTextArea();
		dateTextArea.setBackground(SystemColor.menu);
		dateTextArea.setEditable(false);
		dateTextArea.setText(event.getEventDate().toString());
		
		JTextArea eventTextArea = new JTextArea();
		eventTextArea.setBackground(SystemColor.menu);
		eventTextArea.setEditable(false);
		eventTextArea.setText(event.toString());
		
		JComboBox<Question> questionComboBox = new JComboBox<Question>();
		for(Question q : event.getQuestions())
			questionComboBox.addItem(q);
		
		JLabel questionLabel = new JLabel("Question:");
		
		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		
		JButton checkQuestionButton = new JButton("Check Question");
		
		JTextArea warningTextArea = new JTextArea();
		warningTextArea.setBackground(SystemColor.menu);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(warningTextArea, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(eventLabel)
							.addGap(18)
							.addComponent(eventTextArea, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(dateLabel)
							.addGap(18)
							.addComponent(dateTextArea, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(questionLabel)
							.addGap(18)
							.addComponent(questionComboBox, 0, 323, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(checkQuestionButton)
							.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
							.addComponent(returnButton)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(eventLabel)
						.addComponent(dateLabel)
						.addComponent(dateTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(eventTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(questionLabel)
						.addComponent(questionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(warningTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkQuestionButton)
						.addComponent(returnButton))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		checkQuestionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((Question) questionComboBox.getSelectedItem()).getType() == QuestionTypes.DYNAMIC) {
					CheckAnswersGUI cag = new CheckAnswersGUI(businessLogic, (Question) questionComboBox.getSelectedItem(), getThis());
					warningTextArea.setText("");
					cag.setVisible(true);
					close();
				}
				else
					warningTextArea.setText("The selected question is not dynamic type. Question type: " + ((Question) questionComboBox.getSelectedItem()).getType());
			}
		});
	}
	
	public EventInfoGUI getThis() {
		return this;
	}
	
	public void close() {
		this.setVisible(false);
	}
	
	public void goBack() {
		this.setVisible(false);
		previous.setVisible(true);
	}
}
