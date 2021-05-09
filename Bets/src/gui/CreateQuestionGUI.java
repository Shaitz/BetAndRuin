package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BlFacade;
import configuration.UtilDate;
import domain.Event;
import enums.QuestionTypes;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class CreateQuestionGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private BlFacade businessLogic;

	private JComboBox<Event> eventComboBox = new JComboBox<Event>();
	DefaultComboBoxModel<Event> eventModel = new DefaultComboBoxModel<Event>();

	private JLabel listOfEventsLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("ListEvents"));
	private JLabel queryLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("Question"));
	private JLabel minBetLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("MinimumBetPrice"));
	private JLabel eventDateLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").
			getString("EventDate"));

	private JTextField queryText = new JTextField();
	private JTextField priceText = new JTextField();
	private JCalendar calendar = new JCalendar();
	private Calendar previousCalendar = null;
	private Calendar currentCalendar = null;

	private JScrollPane eventScrollPane = new JScrollPane();

	private JButton createBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));
	private JButton closeBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	JRadioButton fixedButton;
	JRadioButton dynamicButton;
	JRadioButton freeButton;
	private JLabel msgLbl = new JLabel();
	private JLabel errorLbl = new JLabel();

	private Vector<Date> datesWithEventsInCurrentMonth = new Vector<Date>();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public void setBusinessLogic(BlFacade bl) {
		businessLogic = bl;		
	}

	public CreateQuestionGUI(BlFacade bl, Vector<domain.Event> v) {
		businessLogic = bl;
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));

		eventComboBox.setModel(eventModel);
		eventComboBox.setBounds(new Rectangle(275, 47, 250, 20));
		listOfEventsLbl.setBounds(new Rectangle(290, 18, 277, 20));
		queryLbl.setBounds(new Rectangle(25, 211, 75, 20));
		queryText.setBounds(new Rectangle(100, 211, 429, 20));
		minBetLbl.setBounds(new Rectangle(25, 243, 75, 20));
		priceText.setBounds(new Rectangle(100, 243, 60, 20));

		calendar.setBounds(new Rectangle(40, 50, 225, 150));
		eventScrollPane.setBounds(new Rectangle(25, 44, 346, 116));

		createBtn.setBounds(new Rectangle(100, 275, 130, 30));
		createBtn.setEnabled(false);

		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		closeBtn.setBounds(new Rectangle(275, 275, 130, 30));
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		msgLbl.setBounds(new Rectangle(275, 182, 305, 20));
		msgLbl.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		errorLbl.setBounds(new Rectangle(275, 182, 305, 20));
		errorLbl.setForeground(Color.red);

		this.getContentPane().add(msgLbl, null);
		this.getContentPane().add(errorLbl, null);

		this.getContentPane().add(closeBtn, null);
		this.getContentPane().add(createBtn, null);
		this.getContentPane().add(queryText, null);
		this.getContentPane().add(queryLbl, null);
		this.getContentPane().add(priceText, null);

		this.getContentPane().add(minBetLbl, null);
		this.getContentPane().add(listOfEventsLbl, null);
		this.getContentPane().add(eventComboBox, null);

		this.getContentPane().add(calendar, null);

		datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
		paintDaysWithEvents(calendar,datesWithEventsInCurrentMonth);

		eventDateLbl.setBounds(new Rectangle(40, 15, 140, 25));
		eventDateLbl.setBounds(40, 16, 140, 25);
		getContentPane().add(eventDateLbl);
		
		JButton addMoneyBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddMoney")); //$NON-NLS-1$ //$NON-NLS-2$
		addMoneyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFundsGUI addFundsGUI = new AddFundsGUI(businessLogic);
				addFundsGUI.setVisible(true);
			}
		});
		addMoneyBtn.setBounds(440, 279, 89, 23);
		getContentPane().add(addMoneyBtn);
		
		JLabel qType = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		qType.setBounds(175, 246, 32, 14);
		getContentPane().add(qType);
		
		freeButton = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.rdbtnNewRadioButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		freeButton.setSelected(true);
		buttonGroup.add(freeButton);
		freeButton.setBounds(213, 245, 47, 23);
		getContentPane().add(freeButton);
		
		dynamicButton = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.rdbtnNewRadioButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		buttonGroup.add(dynamicButton);
		dynamicButton.setBounds(264, 245, 68, 23);
		getContentPane().add(dynamicButton);
		
		fixedButton = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.rdbtnNewRadioButton_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		buttonGroup.add(fixedButton);
		fixedButton.setBounds(334, 245, 60, 23);
		getContentPane().add(fixedButton);
		
		JButton checkEventButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		checkEventButton.setBounds(285, 78, 89, 23);
		getContentPane().add(checkEventButton);


		// Code for JCalendar
		this.calendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					calendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					currentCalendar = (Calendar) propertychangeevent.getOldValue();
					previousCalendar = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: "+currentCalendar.getTime());
					System.out.println("calendarAct: "+previousCalendar.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, calendar.getLocale());

					int monthAnt = currentCalendar.get(Calendar.MONTH);
					int monthAct = previousCalendar.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, 
							// devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							previousCalendar.set(Calendar.MONTH, monthAnt + 1);
							previousCalendar.set(Calendar.DAY_OF_MONTH, 1);
						}

						calendar.setCalendar(previousCalendar);

						datesWithEventsInCurrentMonth = businessLogic.getEventsMonth(calendar.getDate());
					}

					paintDaysWithEvents(calendar,datesWithEventsInCurrentMonth);

					Date firstDay = UtilDate.trim(previousCalendar.getTime());

					try {
						Vector<domain.Event> events = businessLogic.getEvents(firstDay);

						if (events.isEmpty())
							listOfEventsLbl.setText(ResourceBundle.getBundle("Etiquetas").
									getString("NoEvents") + ": " + dateformat1.
									format(previousCalendar.getTime()));
						else
							listOfEventsLbl.setText(ResourceBundle.getBundle("Etiquetas").
									getString("Events") + " : " + dateformat1.
									format(previousCalendar.getTime()));
						eventComboBox.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							eventModel.addElement(ev);
						eventComboBox.repaint();

						if (events.size() == 0)
							createBtn.setEnabled(false);
						else
							createBtn.setEnabled(true);

					} catch (Exception e1) {

						errorLbl.setText(e1.getMessage());
					}
				}
			}
		});
		
		checkEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventComboBox.getSelectedItem() != null) {
					EventInfoGUI eig = new EventInfoGUI(businessLogic, (Event) eventComboBox.getSelectedItem(), getThis());
					eig.setVisible(true);
					jButtonClose_actionPerformed(e);
					msgLbl.setText("");
				}
				else
					msgLbl.setText("Please, select an event.");
			}
		});
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, 
			Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day is changed.

		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d:datesWithEventsCurrentMonth){
			calendar.setTime(d);
			System.out.println(d);

			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish

			Component o = jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) eventComboBox.getSelectedItem());

		try {
			errorLbl.setText("");
			msgLbl.setText("");

			// Displays an exception if the query field is empty
			String inputQuestion = queryText.getText();

			if (inputQuestion.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(priceText.getText());

				if (inputPrice <= 0)
					errorLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				else {
					QuestionTypes type = QuestionTypes.FREE;
					if(dynamicButton.isSelected())
						type = QuestionTypes.DYNAMIC;
					else if (fixedButton.isSelected())
						type = QuestionTypes.FIXED;
					businessLogic.createQuestion(event, inputQuestion, inputPrice, type);
					msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("QuestionCreated"));
				}
			} else
				msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestion"));
		} catch (EventFinished e1) {
			msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + 
					" : " + event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			msgLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestionAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			errorLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private CreateQuestionGUI getThis() {
		return this;
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}