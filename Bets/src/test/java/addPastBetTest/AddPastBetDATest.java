package addPastBetTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import enums.QuestionTypes;
import exceptions.QuestionAlreadyExist;

class AddPastBetDATest {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	//static TestUtilityDataAccess testDA = new TestUtilityDataAccess();
	static DataAccess testDA = new DataAccess();

	private Event ev;

	@Test
	// sut.addPastBet: Usuario es null
	void test1a() throws ParseException, QuestionAlreadyExist {

		// configure the state of the system (create object in the dabatase)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = sdf.parse("05/10/2022");
		String eventText = "Event Text";
		String queryText = "Query Text";
		Float betMinimum = 2f;
		User u = null;
		QuestionTypes type = QuestionTypes.FREE;
		
		testDA.open(false);
		ev = testDA.addEventWithQuestion(eventText, oneDate, "otra", 10.0f);
		testDA.close();
		
		Question q = sut.createQuestion(ev, queryText, betMinimum, type);
		Bet bet = new Bet(q, 5, 2, "Test");
		
		// invoke System Under Test (sut) and Assert
		assertThrows(RuntimeException.class, () -> sut.addPastBet(u, bet, 5));

		// Remove the created objects in the database (cascade removing)
		testDA.open(false);
		boolean b = testDA.removeEvent(ev);
		System.out.println("Removed event " + b);
		testDA.close();

	}
	
	@Test
	// sut.addPastBet: Bet es null
	void test1b() throws ParseException, QuestionAlreadyExist {

		// configure the state of the system (create object in the dabatase)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = sdf.parse("05/10/2022");
		String eventText = "Event Text";
		String queryText = "Query Text";
		Float betMinimum = 2f;
		User u = new User("TestTest", "123456789", "email@email.test");;
		QuestionTypes type = QuestionTypes.FREE;
		
		testDA.open(false);
		ev = testDA.addEventWithQuestion(eventText, oneDate, "otra", 10.0f);
		testDA.close();
		
		Question q = sut.createQuestion(ev, queryText, betMinimum, type);
		Bet bet = null;
		
		// invoke System Under Test (sut) and Assert
		assertThrows(RuntimeException.class, () -> sut.addPastBet(u, bet, 5));

		// Remove the created objects in the database (cascade removing)
		testDA.open(false);
		boolean b = testDA.removeEvent(ev);
		System.out.println("Removed event " + b);
		testDA.close();

	}
	
	@Test
	// sut.addPastBet: BenefitUser es menor que cero
	void test1c() throws ParseException, QuestionAlreadyExist {

		// configure the state of the system (create object in the dabatase)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = sdf.parse("05/10/2022");
		String eventText = "Event Text";
		String queryText = "Query Text";
		Float betMinimum = 2f;
		User u = new User("TestTest", "123456789", "email@email.test");;
		QuestionTypes type = QuestionTypes.FREE;
		
		testDA.open(false);
		ev = testDA.addEventWithQuestion(eventText, oneDate, "otra", 10.0f);
		testDA.close();
		
		Question q = sut.createQuestion(ev, queryText, betMinimum, type);
		Bet bet = new Bet(q, 5, 2, "Test");
		
		// invoke System Under Test (sut) and Assert
		assertThrows(RuntimeException.class, () -> sut.addPastBet(u, bet, -5));

		// Remove the created objects in the database (cascade removing)
		testDA.open(false);
		boolean b = testDA.removeEvent(ev);
		System.out.println("Removed event " + b);
		testDA.close();

	}

	@Test
	// sut.addPastBet: Usuario no en BD
	void test2() throws ParseException, QuestionAlreadyExist {

		// configure the state of the system (create object in the dabatase)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = sdf.parse("05/10/2022");
		String eventText = "Event Text";
		String queryText = "Query Text";
		Float betMinimum = 2f;
		QuestionTypes type = QuestionTypes.FREE;
		
		testDA.open(false);
		ev = testDA.addEventWithQuestion(eventText, oneDate, "otra", 10.0f);
		testDA.close();
		
		Question q = sut.createQuestion(ev, queryText, betMinimum, type);
		Bet bet = new Bet(q, 5, 2, "Test");
		User u = new User("TestTestTest", "1234567899", "email@email.email");
		testDA.open(false);
		User us = testDA.getUserWithUsernamePassword(u.getUsername(), u.getPassword());
		testDA.close();
		
		// invoke System Under Test (sut) and Assert
		assertNull(us);
		assertThrows(RuntimeException.class, () -> sut.addPastBet(u, bet, 5));

		// Remove the created objects in the database (cascade removing)
		testDA.open(false);
		boolean b = testDA.removeEvent(ev);
		System.out.println("Removed event " + b);
		testDA.close();

	}
	
	@Test
	// sut.addPastBet: Usuario no tiene bets
	void test3() throws ParseException, QuestionAlreadyExist {

		// configure the state of the system (create object in the dabatase)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = sdf.parse("05/10/2022");
		String eventText = "Event Text";
		String queryText = "Query Text";
		Float betMinimum = 2f;
		QuestionTypes type = QuestionTypes.FREE;
		
		testDA.open(false);
		ev = testDA.addEventWithQuestion(eventText, oneDate, "otra", 10.0f);
		testDA.close();
		
		Question q = sut.createQuestion(ev, queryText, betMinimum, type);
		Bet bet = new Bet(q, 5, 2, "Test");
		User u = new User("TestTest", "123456789", "email@email.test");
		
		testDA.open(false);
		User user = testDA.createUser(u.getUsername(), u.getPassword(), u.getMail());
		User u1 = testDA.getUserWithUsernamePassword(u.getUsername(), u.getPassword());
		testDA.close();
		// invoke System Under Test (sut) and Assert
		assertNotNull(u1);
		assertThrows(RuntimeException.class, () -> sut.addPastBet(user, bet, 5));

		// Remove the created objects in the database (cascade removing)
		testDA.open(false);
		boolean b = testDA.removeEvent(ev);
		boolean b2 = testDA.removeUser(user);
		System.out.println("Removed event " + b + b2);
		testDA.close();

	}
	
	@Test
	// sut.addPastBet: Usuario tiene bets pero no ha apostado en bet
	void test4() throws ParseException, QuestionAlreadyExist {

		// configure the state of the system (create object in the dabatase)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = sdf.parse("05/10/2022");
		String eventText = "Event Text";
		String queryText = "Query Text";
		String queryText2 = "Query Text2";
		Float betMinimum = 2f;
		QuestionTypes type = QuestionTypes.FREE;
		
		testDA.open(false);
		ev = testDA.addEventWithQuestion(eventText, oneDate, "otra", 10.0f);
		testDA.close();
		
		Question q = sut.createQuestion(ev, queryText, betMinimum, type);
		Question q2 = sut.createQuestion(ev, queryText2, betMinimum, type);
		Bet bet = new Bet(q, 5, 2, "Test");
		
		User u = new User("TestTest", "123456789", "email@email.test");
		testDA.open(false);
		User user = testDA.createUser(u.getUsername(), u.getPassword(), u.getMail());
		User u1 = testDA.getUserWithUsernamePassword(u.getUsername(), u.getPassword());
		testDA.addMoneyToUser(user.getId(), 2000000);
		testDA.placeBet(user, q2, 120, "1");
		testDA.close();
		// invoke System Under Test (sut) and Assert
		assertNotNull(u1);
		assertNotEquals(u1.getBets().size(), 0);
		assertThrows(RuntimeException.class, () -> sut.addPastBet(user, bet, 5));

		// Remove the created objects in the database (cascade removing)
		testDA.open(false);
		boolean b = testDA.removeEvent(ev);
		boolean b2 = testDA.removeUser(user);
		System.out.println("Removed event " + b + b2);
		testDA.close();
		
	}
	
	@Test
	// sut.addPastBet: Usuario tiene bets y ha acertado en la apuesta
	void test5() throws ParseException, QuestionAlreadyExist {

		// configure the state of the system (create object in the dabatase)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = sdf.parse("05/10/2022");
		String eventText = "Event Text";
		String queryText = "Query Text";
		Float betMinimum = 2f;
		QuestionTypes type = QuestionTypes.FREE;
		
		testDA.open(false);
		ev = testDA.addEventWithQuestion(eventText, oneDate, "otra", 10.0f);
		testDA.close();
		
		Question q = sut.createQuestion(ev, queryText, betMinimum, type);
		
		User u = new User("TestTest", "123456789", "email@email.test");
		testDA.open(false);
		User user = testDA.createUser(u.getUsername(), u.getPassword(), u.getMail());
		User u1 = testDA.getUserWithUsernamePassword(u.getUsername(), u.getPassword());
		testDA.addMoneyToUser(user.getId(), 2000000);
		testDA.placeBet(user, q, 120, "1");
		testDA.close();
		// invoke System Under Test (sut) and Assert
		assertNotNull(u1);
		assertNotEquals(u1.getBets().size(), 0);
		sut.addPastBet(user, user.getBets().get(0), 5);
		testDA.open(false);
		User u2 = testDA.getUserWithUsernamePassword(u.getUsername(), u.getPassword());
		testDA.close();
		assertNotEquals(u2.getPastBets().size(), 0);
		assertEquals(u2.getBets().size(), 0);
		
		// Remove the created objects in the database (cascade removing)
		testDA.open(false);
		boolean b = testDA.removeEvent(ev);
		boolean b2 = testDA.removeUser(user);
		System.out.println("Removed event " + b + b2);
		testDA.close();
		
	}
}