package domain;

import java.io.*;
import java.util.ArrayList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import enums.QuestionTypes;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;

	private String question; 
	private float betMinimum;
	private String result;  
	private QuestionTypes type;
	@XmlIDREF
	private Event event;
	private ArrayList<String> answerList;
	private double pool;

	public Question(){
		super();
		this.pool = 0;
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.pool = 0;
	}

	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.pool = 0;
	}

	public Question(Integer queryNumber, String query, float betMinimum, Event event, QuestionTypes type) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.type = type;
		this.pool = 0;
	}
	
	/**
	 * Gets the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Assigns the bet number to a question
	 * 
	 * @param questionNumber to be set
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * Gets the question description of the bet
	 * 
	 * @return the bet question
	 */
	public String getQuestion() {
		return question;
	}


	/**
	 * Sets the question description of the bet
	 * 
	 * @param question to be set
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}



	/**
	 * Gets the minimum amount allowed for a bet
	 * 
	 * @return the minimum bet
	 */
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Gets the minimum amount allowed for the bet
	 * 
	 * @param  minimum amount to be set
	 */
	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}


	/**
	 * Gets the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the correct result of the  query
	 * 
	 * @param correct result of the query
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Gets the event associated with the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Sets the event associated with the bet
	 * 
	 * @param event to associate with the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public void setType(QuestionTypes type)
	{
		this.type = type;
	}
	
	public QuestionTypes getType()
	{
		return this.type;
	}
	
	public double getPool()
	{
		return this.pool;
	}
	
	public void addPool(double pool)
	{
		this.pool += pool;
	}
	
	public boolean addAnswer(String answer) {
		if(answerList != null) {
			if(answerList.contains(answer))
				return false;
		}
		else
			answerList = new ArrayList<String>();
		answerList.add(answer);
		return true;
	}
	
	public boolean removeAnswer(String answer) {
		String ret = answerList.remove(answerList.indexOf(answer));
		if(ret == null)
			return false;
		return true;
	}
	
	public ArrayList<String> getAnswers(){
		return answerList;
	}
	
	@Override
	public String toString(){
		return questionNumber + ";" + question + ";" + Float.toString(betMinimum);
	}	
}