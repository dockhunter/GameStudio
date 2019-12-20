package sk.tsystems.gamestudio.entity;

import javax.persistence.*;

@Entity
public class Rating {

	@Id
	@GeneratedValue
	private int ident;
	
	private int rate; 
	
	private String game;

	private String username;
	
	public Rating() {
	}
	
	public Rating(String username, int rate, String game) {
		this.rate = rate;
		this.game = game;
		this.username = username;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
