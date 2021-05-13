package gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BlFacade;
import configuration.UtilDate;
import domain.Question;
import enums.QuestionTypes;

import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class BrowseQuestionsGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private BlFacade businessLogic;

	private final JLabel eventDateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("EventDate"));
	private final JLabel questionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Questions")); 
	private final JLabel eventLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Events")); 
	private final JLabel ansLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Answer"));

	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Close"));
	private JTextField AnsTextField = new JTextField();
	private JButton btnSetAns = new JButton(ResourceBundle.getBundle("Etiquetas").
			getString("Set answer")); 


	private JButton btnBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
	// Code for JCalendar
	private JCalendar calendar = new JCalendar();
	private Calendar previousCalendar;
	private Calendar currentCalendar;
	private JScrollPane eventScrollPane = new JScrollPane();
	private JScrollPane questionScrollPane = new JScrollPane();
	
	private Vector<Date> datesWithEventsInCurrentMonth = new Vector<Date>();

	private JTable eventTable= new JTable();
	private JTable questionTable = new JTable();

	private DefaultTableModel eventTableModel;
	private DefaultTableModel questionTableModel;

	private String[] eventColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] questionColumnNames = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QuestionN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Question")
	};
	private JTextArea warningTxtArea;


	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;
	}

	public BrowseQuestionsGUI(BlFacade bl) {
		businessLogic = bl;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));
		
		eventDateLbl.setBounds(new Rectangle(40, 15, 140, 25));
		questionLbl.setBounds(138, 248, 406, 14);
		eventLbl.setBounds(295, 19, 259, 16);
		ansLbl.setBounds(295, 225, 45, 13);
		AnsTextField.setBounds(340, 222, 204, 20);
		btnSetAns.setBounds(554, 345, 105, 33);
		
		AnsTextField.setColumns(10);

		this.getContentPane().add(eventDateLbl, null);
		this.getContentPane().add(questionLbl);
		this.getContentPane().add(eventLbl);
		this.getContentPane().add(ansLbl);
		this.getContentPane().add(AnsTextField);
		this.getContentPane().add(btnSetAns);

		
		if(businessLogic.getUser()!=null&&businessLogic.getUser().isAdmin()) {
			btnBet.setVisible(false);
		}else {
			ansLbl.setVisible(false);
			AnsTextField.setVisible(false);
			btnSetAns.setVisible(false);
		}
		
		closeBtn.setBounds(new Rectangle(274, 419, 130, 30));

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(closeBtn, null);

		calendar.setBounds(new Rectangle(40, 50, 225, 150));

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		CreateQuestionGUI.paintDaysWithEvents(calendar, datesWithEventsInCurrentMonth);

		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

				if (propertyChangeEvent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertyChangeEvent.getNewValue());
				}
				else if (propertyChangeEvent.getPropertyName().equals("calendar")) {
					previousCalendar = (Calendar) propertyChangeEvent.getOldValue();
					currentCalendar = (Calendar) propertyChangeEvent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, calendar.getLocale());
					Date firstDay = UtilDate.trim(new Date(calendar.getCalendar().getTime().getTime()));

					int previousMonth = previousCalendar.get(Calendar.MONTH);
					int currentMonth = currentCalendar.get(Calendar.MONTH);

					if (currentMonth != previousMonth) {
						if (currentMonth == previousMonth + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, 
							// devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							currentCalendar.set(Calendar.MONTH, previousMonth + 1);
							currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
						}						

						calendar.setCalendar(currentCalendar);
						datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.
								getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(calendar,datesWithEventsInCurrentMonth);

					try {
						eventTableModel.setDataVector(null, eventColumnNames);
						eventTableModel.setColumnCount(3); // another column added to allocate ev objects

						Vector<domain.Event> events = businessLogic.getEvents(firstDay);

						if (events.isEmpty() ) eventLbl.setText(ResourceBundle.getBundle("Etiquetas").
								getString("NoEvents") + ": " + dateformat1.format(currentCalendar.
										getTime()));
						else eventLbl.setText(ResourceBundle.getBundle("Etiquetas").
								getString("Events") + ": " + dateformat1.format(currentCalendar.
										getTime()));
						for (domain.Event ev : events){
							Vector<Object> row = new Vector<Object>();
							System.out.println("Events " + ev);
							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); 	// ev object added in order to obtain it with 
							// tableModelEvents.getValueAt(i,2)
							eventTableModel.addRow(row);		
						}
						eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
						eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);
						eventTable.getColumnModel().removeColumn(eventTable.getColumnModel().
								getColumn(2)); // not shown in JTable
					}
					catch (Exception e1) {
						questionLbl.setText(e1.getMessage());
					}
				}
			} 
		});

		this.getContentPane().add(calendar, null);

		eventScrollPane.setBounds(new Rectangle(292, 50, 346, 150));
		questionScrollPane.setBounds(new Rectangle(138, 274, 406, 116));

		eventTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = eventTable.getSelectedRow();
				domain.Event ev = (domain.Event)eventTableModel.getValueAt(i,2); // obtain ev object
				Vector<Question> queries = ev.getQuestions();

				questionTableModel.setDataVector(null, questionColumnNames);

				if (queries.isEmpty())
					questionLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("NoQuestions") + ": " + ev.getDescription());
				else 
					questionLbl.setText(ResourceBundle.getBundle("Etiquetas").
							getString("SelectedEvent") + " " + ev.getDescription());

				for (domain.Question q : queries) {
					Vector<Object> row = new Vector<Object>();
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					questionTableModel.addRow(row);
				}
				questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
				questionTable.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		questionTable.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{	
				if (businessLogic.getUser() != null) {
					btnBet.setEnabled(true);
					btnSetAns.setEnabled(true);
				}else {
					btnBet.setEnabled(false);
					btnSetAns.setEnabled(false);
				}
			}
		});
		
		this.btnBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int i = eventTable.getSelectedRow();
				domain.Event ev = (domain.Event)eventTableModel.getValueAt(i,2); // obtain ev object
				Vector<Question> queries = ev.getQuestions(); // get available questions in event
				
				int j = questionTable.getSelectedRow();
				String currentQ = (String)questionTable.getValueAt(j, 1); // get selected string of question
				domain.Question quest = new Question();
				
				for (domain.Question q: queries)
					if (currentQ.equals(q.getQuestion()))
						quest = q; // get the selected question
				
				if(quest.getEvent().canBet()) {
					if (quest.getType().equals(QuestionTypes.FIXED))
					{
						BetQuestionFixedGUI betBet = new BetQuestionFixedGUI(businessLogic, ev, quest);
						betBet.setVisible(true);
					}
					else if (quest.getType().equals(QuestionTypes.DYNAMIC))
					{
						if(businessLogic.getAnswersOfQuestion(quest) != null) {
							BetQuestionDynamicGUI betBet = new BetQuestionDynamicGUI(businessLogic, ev, quest);
							betBet.setVisible(true);
						}
						else
							warningTxtArea.setText("This question doesn't have answers yet.");
					}
					else
					{
						BetQuestionFreeGUI betBet = new BetQuestionFreeGUI(businessLogic, ev, quest);
						betBet.setVisible(true);
					}
				}
				else {
					warningTxtArea.setText("As this event will occur soon, you are unable to place a bet.");
				}
					
			}
		});
		
		eventScrollPane.setViewportView(eventTable);
		eventTableModel = new DefaultTableModel(null, eventColumnNames);

		eventTable.setModel(eventTableModel);
		eventTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		eventTable.getColumnModel().getColumn(1).setPreferredWidth(268);

		questionScrollPane.setViewportView(questionTable);
		questionTableModel = new DefaultTableModel(null, questionColumnNames);

		questionTable.setModel(questionTableModel);
		questionTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		questionTable.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(eventScrollPane, null);
		this.getContentPane().add(questionScrollPane, null);
		
		btnBet.setEnabled(false);
		btnBet.setBounds(554, 300, 105, 33);
		getContentPane().add(btnBet);
		
		warningTxtArea = new JTextArea();
		warningTxtArea.setBackground(SystemColor.menu);
		warningTxtArea.setText(ResourceBundle.getBundle("Etiquetas").getString("WarningTxtArea")); //$NON-NLS-1$ //$NON-NLS-2$
		warningTxtArea.setBounds(40, 222, 598, 22);
		getContentPane().add(warningTxtArea);
		
		btnSetAns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int i = eventTable.getSelectedRow();
				domain.Event ev = (domain.Event)eventTableModel.getValueAt(i,2); // obtain ev object
				Vector<Question> queries = ev.getQuestions(); // get available questions in event
				
				int j = questionTable.getSelectedRow();
				String currentQ = (String)questionTable.getValueAt(j, 1); // get selected string of question
				domain.Question quest = new Question();
				
				for (domain.Question q: queries)
					if (currentQ.equals(q.getQuestion()))
						quest = q; // get the selected question
				
				businessLogic.setResult(quest, AnsTextField.getText());
				Question quest2 = businessLogic.retrieveQuestion(quest);
				businessLogic.giveRewards(quest2);
			}
		});
		btnSetAns.setEnabled(false);		
		
	}

	
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}