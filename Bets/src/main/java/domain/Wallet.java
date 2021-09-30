package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
public class Wallet {
	
	private double currency;
	private String bankAccount;
	
	public Wallet() {
		currency = 0;
	}
	
	public Wallet(double initialAmount) {
		currency = initialAmount;
	}
	
	public double getCurrency() {
		return currency;
	}
	
	public String getAccount() {
		return bankAccount;
	}
	
	public void setAccount(String newAccount) {
		bankAccount = newAccount;
	}
	
	public void addMoney(double amount) {
		currency += amount;
	}
	
	public boolean removeMoney(double amount) {
		if(currency - amount < 0)
			return false;
		else 
			currency -= amount;
		return true;
	}
}
