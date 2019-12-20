package sk.tsystems.gamestudio.entity;

import javax.persistence.*;

@Entity
public class Answer {
	
	@Id
	public int ident;
	
	public String guess;
	
	
	public Answer() {	
	}

	public Answer(String guess) {
		this.guess = guess;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

}
