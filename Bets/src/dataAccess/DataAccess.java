package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import enums.QuestionTypes;
import exceptions.QuestionAlreadyExist;

/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess  {

	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML config = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode)  {
		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + 
				config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());
		open(initializeMode);
	}

	public DataAccess()  {	
		this(false);
	}


	/**
	 * This method initializes the database with some trial events and questions. 
	 * It is invoked by the business logic when the option "initialize" is used 
	 * in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();

		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			int aux = month + 1;
			if(today.get(Calendar.DAY_OF_MONTH) > 17) {
				month++;
			}
			int year = today.get(Calendar.YEAR);
			if (month == 12) { month = 0; year += 1;}  

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 25));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, aux, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, aux, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, aux, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, aux, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, aux, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, aux, 1));


			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q1.setEvent(ev1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q2.setEvent(ev1);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q3.setEvent(ev11);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q4.setEvent(ev11);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q5.setEvent(ev17);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
				q6.setEvent(ev17);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q1.setEvent(ev1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q2.setEvent(ev1);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q3.setEvent(ev11);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q4.setEvent(ev11);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q5.setEvent(ev17);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
				q6.setEvent(ev17);
			}			
			else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q1.setEvent(ev1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q2.setEvent(ev1);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q3.setEvent(ev11);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q4.setEvent(ev11);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q5.setEvent(ev17);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
				q6.setEvent(ev17);
			}
			
			q1.setType(QuestionTypes.FIXED);
			q2.setType(QuestionTypes.FIXED);
			q3.setType(QuestionTypes.FIXED);
			q4.setType(QuestionTypes.FREE);
			q5.setType(QuestionTypes.FIXED);
			q6.setType(QuestionTypes.DYNAMIC);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			

			db.getTransaction().commit();
			System.out.println("The database has been initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum, QuestionTypes type) 
			throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event = " + event + " question = " +
				question + " minimum bet = " + betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.doesQuestionExist(question)) throw new QuestionAlreadyExist(
				ResourceBundle.getBundle("Etiquetas").getString("ErrorQuestionAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		q.setEvent(ev);
		q.setType(type);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added
		// in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;
	}
	
	public Question getQuestion(Question q) {
		return db.createQuery("SELECT q FROM Question q WHERE q.questionNumber = " + q.getQuestionNumber(), Question.class).getSingleResult();
	}
	
	public User createUser(String username, String password) {
		User nU = null;
		if(getUserWithUsernamePassword(username, password) == null) {
			nU = new User(username, password);
			db.getTransaction().begin();
			db.persist(nU);
			db.getTransaction().commit();
		}
		return nU;
	}
	
	public List<User> getAllUsers(){
		return db.createQuery("SELECT u FROM User u", User.class).getResultList();
	}
	
	public User getUserByID(Integer id) {
		User ret;
		List<User> checkList = db.createQuery("SELECT u FROM User u WHERE u.id = " + id, User.class).getResultList();
		try {
			ret = checkList.get(0);
		}
		catch (Exception e) {
			ret = null;
		}
		return ret;
	}
	
	public List<User> getUserWithUsername(String username){
		return db.createQuery("SELECT u FROM User u WHERE u.username == \"" + username + "\"", User.class).getResultList();
	}
	
	public User getUserWithUsernamePassword(String username, String password){
		User ret;
		List<User> checkList = db.createQuery("SELECT u FROM User u WHERE u.username = \"" + username + "\" and u.password = \"" + password + "\"", User.class).getResultList();
		try {
			ret = checkList.get(0);
		}
		catch(Exception e) {
			ret = null;
		}
		return ret;
	}
	
	public User changeUsername(String username, String password, String newUsername)
	{
		User user = this.getUserWithUsernamePassword(username, password);
		
		db.getTransaction().begin();
		user.setUsername(newUsername);
		db.getTransaction().commit();
		
		return user;
	}
	
	public User changePasswordOfUser(String username, String password, String nPassword) {
		try {
			User toChange = getUserWithUsernamePassword(username, password);
			db.getTransaction().begin();
			toChange.setPassword(nPassword);
			db.getTransaction().commit();
			return toChange;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public double addMoneyToUser(int id, double amount) {
		User user = this.getUserByID(id);
		if(user == null)
			return -1;
		db.getTransaction().begin();
		double ret = user.increaseCurrency(amount);
		db.getTransaction().commit();
		return ret;
	}
	
	public boolean placeBet(User user, Question question, double amount, String answer) {
		User userToChange = this.getUserWithUsernamePassword(user.getUsername(), user.getPassword());
		Question q = this.getQuestion(question);
		db.getTransaction().begin();
		boolean ret = userToChange.placeBet(question, amount, answer);
		q.addPool(amount);
		db.getTransaction().commit();
		return ret;
	}
	
	public boolean removeBet(User user, Bet bet) {
		User userToChange = this.getUserWithUsernamePassword(user.getUsername(), user.getPassword());
		List<Bet> betlist = userToChange.getBets();
		Bet userBet = null;
		
		for (Bet b : betlist)
			if (bet.getQuestion().getQuestionNumber().equals(b.getQuestion().getQuestionNumber()))
				userBet = b;
		
		Question q = this.getQuestion(userBet.getQuestion());
		db.getTransaction().begin();
		boolean ret = userToChange.removeBet(userBet);
		if(ret)
		{
			q.addPool(userBet.getPlacedBet() * -1);
			userToChange.increaseCurrency(userBet.getPlacedBet() * 0.75);
		}
		db.getTransaction().commit();
		return ret;
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", 
				Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates in a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev "
				+ "WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}


	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + 
				config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());

		String fileName = config.getDataBaseFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (config.isDataAccessLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", config.getDataBaseUser());
			properties.put("javax.persistence.jdbc.password", config.getDataBasePassword());

			emf = Persistence.createEntityManagerFactory("objectdb://" + config.getDataAccessNode() +
					":"+config.getDataAccessPort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion => event = " + event + 
				" question = " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.doesQuestionExist(question);
	}
	
	public boolean addAnswer(Question q, String a) {
		boolean ret;
		Question question = getQuestion(q);
		db.getTransaction().begin();
		ret = question.addAnswer(a);
		db.getTransaction().commit();
		return ret;
	}
	
	public boolean removeAnswer(Question q, String a) {
		boolean ret;
		Question question = getQuestion(q);
		db.getTransaction().begin();
		ret = question.removeAnswer(a);
		db.getTransaction().commit();
		return ret;
	}
	
	public Iterable<String> getAnswerList(Question q) {
		Question question = getQuestion(q);
		return question.getAnswers();
	}

	public void close(){
		db.close();
		System.out.println("DataBase is closed");
	}
	
	public void setResult(Question q, String s) {
		db.getTransaction().begin();
		Question question = getQuestion(q);
		question.setResult(s);
		db.getTransaction().commit();
	}
}