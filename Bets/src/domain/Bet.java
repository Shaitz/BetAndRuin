package domain;

import javax.persistence.*;

@Entity
public class Bet 
{
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int questionId;
	private Question question;
	private double placedBet;
	private double benefits;
	
	public Bet(Question question, double placedBet, double benefits) {
		this.question = question;
		this.placedBet = placedBet;
		this.benefits = benefits;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public double getPlacedBet() {
		return placedBet;
	}

	public void setPlacedBet(double placedBet) {
		this.placedBet = placedBet;
	}

	public double getBenefits() {
		return benefits;
	}

	public void setBenefits(double benefits) {
		this.benefits = benefits;
	}
	
	
}
