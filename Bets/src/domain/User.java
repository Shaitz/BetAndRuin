package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User 
{
	//@Id ?
	private String username;
	private String password;
	
	public User()
	{
		super();
	}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
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
	
	public String toString()
	{
		return username + "\n" + password;
	}
}