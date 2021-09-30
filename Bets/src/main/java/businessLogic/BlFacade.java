package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import enums.QuestionTypes;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BlFacade  {

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	Question createQuestion(Event event, String question, float betMinimum, QuestionTypes type) 
			throws EventFinished, QuestionAlreadyExist;
		
	/**
	 * This method retrieves all the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates in a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method checks if the login is successful or not
	 * 
	 * @param username of the account to be checked
	 * @param password of the account to be checked
	 * @return true if the authentication was successful
	 */
	@WebMethod public boolean validateLogin(String username, String password);
	
	/**
	 * This method registers a new user to the database
	 * 
	 * @param username of the account to be created
	 * @param password of the account to be created
	 * @return true if the register was successful
	 */
	@WebMethod public boolean registerUser(String username, String password, String eMail);
	
	/**
	 * This method returns the user that contains the same username and password as the stated one
	 * 
	 * @param username of the account
	 * @param password of the account
	 * @return user containing the information. If there's not, returns null
	 */
	@WebMethod public User getRegisteredUser(String username, String password);
	
	/**
	 * This method changes the desired user's username
	 * 
	 * @param username of the account
	 * @param password of the account
	 * @return user with changed username
	 */
	@WebMethod public User changeUsername(String username, String password, String newUsername);
	
	/**
	 * This method changes the desired user's password
	 * 
	 * @param username of the account
	 * @param password of the account
	 * @return user with new password
	 */
	@WebMethod public User changePassword(String username, String password, String newPassword);
	
	/**
	 * This method gets the logged user in the application
	 * 
	 * @return logged user
	 */
	@WebMethod public User getUser();
	
	/**
	 * This method returns a concrete user indicated by ID. If the user doesn't exist, returns null
	 * 
	 * @param id Id of the user to search
	 * @return logged user
	 */
	@WebMethod public double addMoneyToUser(int id, double amount);
	
	/**
	 * Places a bet in the indicated question with the indicated amount and registers it if the amount is no less than the minimum
	 * @param question Question in which place the bet
	 * @param bet Amount to put
	 * @return True if the bet was placed successfully, false otherwise
	 */
	@WebMethod public boolean placeBet(Question question, double bet, String answer);
	
	/**
	 * Removes a bet previously registered by the user and returns the status of the operation
	 * @param bet Bet to be removed
	 * @return True if the operation has been made succesfully. False otherwise
	 */
	@WebMethod public boolean removeBet(Bet bet);
	
	@WebMethod public boolean addAnswerToQuestion(Question q, String answer);
	
	@WebMethod public boolean removeAnswerFromQuestion(Question q, String answer);
	
	@WebMethod public ArrayList<String> getAnswersOfQuestion(Question q);
	
	@WebMethod 	public void setResult(Question q, String s);
	
	@WebMethod public void refreshUser();
	
	@WebMethod public void giveRewards(Question q);
	
	@WebMethod public Question retrieveQuestion(Question q);
}