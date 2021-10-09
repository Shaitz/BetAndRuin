package addPastBetTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import enums.QuestionTypes;
import businessLogic.BlFacadeImplementation;
import businessLogic.BlFacade;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;


class GiveRewardsBLMockTest {
	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	Event mockedEvent = Mockito.mock(Event.class);

	BlFacade sut = new BlFacadeImplementation(dataAccess);

	@SuppressWarnings("unchecked")
	@DisplayName("sut.giveRewards: Question es null.")
	@Test
	void test1() 
	{
		Question q = null;
		assertThrows(RuntimeException.class, ()-> sut.giveRewards(q));
	}
	

	@Test
	@DisplayName("sut.giveRewards: Question no tiene resultado.")
	void test2() throws ParseException {
		// define paramaters
		String queryText = "Query Text";
		// configure Mock
		Float betMinimum = 2f;
		// invoke System Under Test (sut)
		QuestionTypes tipo = QuestionTypes.FREE;
		
		Question q = new Question(1, queryText, betMinimum, mockedEvent, tipo);
		assertThrows(RuntimeException.class, ()-> sut.giveRewards(q));

	}
	
	@Test
	@DisplayName("sut.giveRewards: BD vacía")
	void test3() throws ParseException {
		// define paramaters
		String queryText = "Query Text";
		Float betMinimum = 2f;
		// configure Mock
		List<User> ulist = new ArrayList<>();
		Mockito.doReturn(ulist).when(dataAccess).getAllUsers();
		
		// invoke System Under Test (sut)
		QuestionTypes tipo = QuestionTypes.FREE;
		Question q = new Question(1, queryText, betMinimum, mockedEvent, tipo);
		q.setResult("1");
		assertThrows(RuntimeException.class, ()-> sut.giveRewards(q));
		Mockito.verify(dataAccess, Mockito.times(1)).getAllUsers();

	}
	
	@Test
	@DisplayName("sut.giveRewards: BD con 3 usuarios diferentes.")
	void test4() throws ParseException {
		// define paramaters
		String queryText = "Query Text";
		Float betMinimum = 2f;
		QuestionTypes tipo = QuestionTypes.FREE;
		Question q = new Question(1, queryText, betMinimum, mockedEvent, tipo);
		q.setResult("1");
		// configure Mock
		User u1 = new User(1, "Test1", "test1", "email@test1.com");
		User u2 = new User(2, "Test2", "test2", "email@test2.com");
		User u3 = new User(3, "Test3", "test3", "email@test3.com");
		u1.increaseCurrency(5555);
		u2.increaseCurrency(5555);
		u1.placeBet(q, 5, "1");
		u2.placeBet(q, 5, "2");
		List<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		
		Mockito.doReturn(users).when(dataAccess).getAllUsers();
		sut.giveRewards(q);
		
		Mockito.verify(dataAccess, Mockito.times(1)).getAllUsers();
		Mockito.verify(dataAccess, Mockito.times(2)).addPastBet(Mockito.any(User.class), Mockito.any(Bet.class), Mockito.any(Double.class));
		Mockito.verify(dataAccess, Mockito.times(1)).addMoneyToUser(Mockito.anyInt(), Mockito.anyInt());
	}
}
