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
	@ManyToOne
	@ElementCollection
	private List<Bet> pastBets;
	private boolean admin;
	@Embedded
	private Wallet personalWallet;
	private String eMail;
	
	public User()
	{
		super();
	}
	
	public User(Integer id, String username, String password, String mail)
	{
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.betList = new ArrayList<>();
		this.pastBets = new ArrayList<>();
		if(password.equals("admin123"))
			admin = true;
		else
			admin = false;
		personalWallet = new Wallet();
		eMail = mail;
	}
	
	public User(Integer id, String username, String password, String mail, int initialCurrency)
	{
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.betList = new ArrayList<>();
		this.pastBets = new ArrayList<>();
		if(password.equals("admin123"))
			admin = true;
		else
			admin = false;
		personalWallet = new Wallet(initialCurrency);
		eMail = mail;
	}
	
	public User(String username, String password, String mail)
	{
		super();
		this.username = username;
		this.password = password;
		this.betList = new ArrayList<>();
		this.pastBets = new ArrayList<>();
		if(password.equals("admin123"))
			admin = true;
		else
			admin = false;
		personalWallet = new Wallet();
		eMail = mail;
	}
	
	public User(String username, String password, String mail, int initialCurrency)
	{
		super();
		this.username = username;
		this.password = password;
		this.betList = new ArrayList<>();
		this.pastBets = new ArrayList<>();
		if(password.equals("admin123"))
			admin = true;
		else
			admin = false;
		personalWallet = new Wallet(initialCurrency);
		eMail = mail;
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
		if(password.equals("admin123"))
			admin=true;
		else
			admin=false;
	}
	
	/**
	 * Places a bet in the indicated question with the indicated amount and registers it if the amount is no less than the minimum
	 * @param question Question in which place the bet
	 * @param bet Amount to put
	 * @return True if the bet was placed successfully, false otherwise
	 */
	public boolean placeBet(Question question, double bet, String answer) {
		
		//System.out.println(increaseCurrency(22)); // for testing purposes, only admins can add money
		
		if(bet <= question.getBetMinimum() || personalWallet.getCurrency() < bet)
			return false;
		else
		{
			if (betList == null)
				betList = new ArrayList<>();
			List<Bet> userBets = this.getBets();
			
			// check if user has already bet this question
			for (Bet b : userBets)
			{
				if (b.getQuestion().getQuestionNumber().equals(question.getQuestionNumber()))
					return false;
			}
			
			this.betList.add(new Bet(question, bet, 1.2, answer));
			decreaseCurrency(bet);
			return true;
		}
	}
	
	public boolean removeBet(Bet bet) 
	{
		boolean toCheck = false;
		if(betList.contains(bet)) 
		{
			betList.remove(bet);
			toCheck = true;
		}
		return toCheck;
	}
	
	public void addToPastBets(Bet bet)
	{
		this.pastBets.add(bet);
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
	
	public Wallet getWallet()
	{
		return this.personalWallet;
	}
	
	public double increaseCurrency(double amount) {
		personalWallet.addMoney(amount);
		return personalWallet.getCurrency();
	}
	
	public double decreaseCurrency(double amount) {
		personalWallet.removeMoney(amount);
		return personalWallet.getCurrency();
	}
	
	public String getMail() {
		return eMail;
	}
	
	public void setMail(String mail) {
		eMail = mail;
	}
	
	public List<Bet> getPastBets()
	{
		return this.pastBets;
	}
	
	public String toString()
	{
		return id + "\t" + username + "\t" + password;
	}
}