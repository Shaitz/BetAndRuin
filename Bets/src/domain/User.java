package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User 
{
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	@ManyToOne
	@ElementCollection
	private ArrayList<Bet> betList;
	private boolean admin;
	
	public User()
	{
		super();
	}
	
	public User(Integer id, String username, String password)
	{
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.betList = new ArrayList<Bet>();
		if(password.equals("admin123"))
			admin = true;
		else
			admin = false;
	}
	
	public User(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
		this.betList = new ArrayList<Bet>();
		if(password.equals("admin123"))
			admin = true;
		else
			admin = false;
	}
	
	/**
	 * Gets the user's id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Gets the user's name
	 */
	public String getUsername() 
	{ 
		return this.username;
	}
	
	/**
	 * Gets the user's password
	 */
	public String getPassword() 
	{ 
		return this.password; 
	}
	
	/**
	 * Sets the user's name with the given one
	 * @param username to be set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Sets the user's password with the given one
	 * @param password to be set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * Places a bet in the indicated question with the indicated amount and registers it if the amount is no less than the minimum
	 * @param question Question in which place the bet
	 * @param bet Amount to put
	 * @return True if the bet was placed successfully, false otherwise
	 */
	public boolean placeBet(Question question, double bet) {
		if(bet <= question.getBetMinimum())
			return false;
		else {
			if (betList == null)
				betList = new ArrayList<Bet>();
			
			this.betList.add(new Bet(question, bet, 1.2));
			return true;
		}
	}
	
	/**
	 * Returns a list containing the list of bets made by this user
	 * @return List with bets
	 */
	public List<Bet> getBets(){
		return betList;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public String toString()
	{
		return id + "\t" + username + "\t" + password;
	}
}