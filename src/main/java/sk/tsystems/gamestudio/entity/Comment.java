package sk.tsystems.gamestudio.entity;

import javax.persistence.*;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private String content; 
	
	private String game;

	private String username;
	
	private String date;

	public Comment() {
	}
	
	public Comment(String username, String content, String game, String date) {
		this.username = username;
		this.content = content;
		this.game = game;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
