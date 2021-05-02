package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AddAnswerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField answerTextField;
	private BlFacade businessLogic;
	private Question question;
	private JTextField warningTextField;

	/**
	 * Create the frame.
	 */
	public AddAnswerGUI(BlFacade bl, Question q) {
		question = q;
		businessLogic = bl;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel newAnswerLabel = new JLabel("New answer");
		newAnswerLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		answerTextField = new JTextField();
		answerTextField.setColumns(10);
		
		JButton addAnswerBtn = new JButton("Add answer");
		
		JButton goBackBtn = new JButton("Go back");
		
		warningTextField = new JTextField();
		warningTextField.setBackground(SystemColor.menu);
		warningTextField.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(warningTextField, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(newAnswerLabel)
							.addGap(18)
							.addComponent(answerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addAnswerBtn)
							.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
							.addComponent(goBackBtn)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(answerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(newAnswerLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(warningTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addAnswerBtn)
						.addComponent(goBackBtn))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		addAnswerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!answerTextField.getText().equals("")) {
					if(question.addAnswer(answerTextField.getText()))
						close();
					else
						warningTextField.setText("This answer already exists");
				}
				else
					warningTextField.setText("Please insert something in the text area");
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
	}
}
