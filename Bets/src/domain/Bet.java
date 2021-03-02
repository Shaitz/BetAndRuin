package domain;

public class Bet {
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
