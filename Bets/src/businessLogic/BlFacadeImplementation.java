package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import enums.QuestionTypes;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;


/**
 * Implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BlFacade")
public class BlFacadeImplementation implements BlFacade {

	DataAccess dbManager;
	ConfigXML config = ConfigXML.getInstance();
	private User user = null;

	public BlFacadeImplementation()  {		
		System.out.println("Creating BlFacadeImplementation instance");
		boolean initialize = config.getDataBaseOpenMode().equals("initialize");
		dbManager = new DataAccess(initialize);
		if (initialize)
			dbManager.initializeDB();
		dbManager.close();
	}

	public BlFacadeImplementation(DataAccess dam)  {
		System.out.println("Creating BlFacadeImplementation instance with DataAccess parameter");
		if (config.getDataBaseOpenMode().equals("initialize")) {
			dam.open(true);
			dam.initializeDB();
			dam.close();
		}
		dbManager = dam;		
	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @param type Indicates the format of the answer when making a bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@Override
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum, QuestionTypes type) 
			throws EventFinished, QuestionAlreadyExist {

		//The minimum bid must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").
					getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum, type);		
		dbManager.close();
		return qry;
	}

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@Override
	@WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@Override
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	
	public boolean validateLogin(String username, String password)
	{
		dbManager.open(false);
		this.user = dbManager.getUserWithUsernamePassword(username, password);
		dbManager.close();
		return this.user != null;
	}
	
	public boolean registerUser(String username, String password)
	{
		dbManager.open(false);
		this.user = dbManager.createUser(username, password);
		dbManager.close();
		return this.user != null;
	}
	
	public User getRegisteredUser(String username, String password) 
	{
		dbManager.open(false);
		User u = dbManager.getUserWithUsernamePassword(username, password);
		User ret = u != null ? u : null;
		dbManager.close();
		return ret;
	}
	
	public User changeUsername(String username, String password, String newUsername)
	{
		dbManager.open(false);
		this.user = dbManager.changeUsername(username, password, newUsername);
		dbManager.close();
		this.refreshUser();
		return this.user;
	}
	
	public User changePassword(String username, String password, String newPassword)
	{
		dbManager.open(false);
		this.user = dbManager.changePasswordOfUser(username, password, newPassword);
		dbManager.close();
		this.refreshUser();
		return this.user;
	}
	
	/**
	 * Places a bet in the indicated question with the indicated amount and registers it if the amount is no less than the minimum
	 * @param question Question in which place the bet
	 * @param bet Amount to put
	 * @return True if the bet was placed successfully, false otherwise
	 */
	public boolean placeBet(Question question, double bet, String answer) 
	{
		dbManager.open(false);
		boolean betState = dbManager.placeBet(this.user, question, bet, answer);
		dbManager.close();
		this.refreshUser();
		return betState;
	}
	
	public boolean removeBet(Bet bet) {
		dbManager.open(false);
		boolean removeState = dbManager.removeBet(this.user, bet);
		dbManager.close();
		this.refreshUser();
		return removeState;
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public void refreshUser()
	{
		this.user = this.getRegisteredUser(this.user.getUsername(), this.getUser().getPassword());
	}
	
	public double addMoneyToUser(int id, double amount) 
	{
		dbManager.open(false);
		double ret = dbManager.addMoneyToUser(id, amount);
		dbManager.close();
		this.refreshUser();
		return ret;
	}
	
	public void close() {
		dbManager.close();
	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod	
	public void initializeBD(){
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@Override
	public boolean addAnswerToQuestion(Question q, String answer) {
		return dbManager.addAnswer(q, answer);
	}

	@Override
	public boolean removeAnswerFromQuestion(Question q, String answer) {
		return dbManager.removeAnswer(q, answer);
	}

	@Override
	public Iterable<String> getAnswersOfQuestion(Question q) {
		return dbManager.getAnswerList(q);
	}
}