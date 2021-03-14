package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
public class Wallet {
	
	private int currency;
	private String bankAccount;
	
	public Wallet() {
		currency = 0;
	}
	
	public Wallet(int initialAmount) {
		currency = initialAmount;
	}
	
	public int getCurrency() {
		return currency;
	}
	
	public String getAccount() {
		return bankAccount;
	}
	
	public void setAccount(String newAccount) {
		bankAccount = newAccount;
	}
	
	public void addMoney(int amount) {
		currency += amount;
	}
	
	public boolean removeMoney(int amount) {
		if(currency - amount < 0)
			return false;
		else 
			currency -= amount;
		return true;
	}
}
