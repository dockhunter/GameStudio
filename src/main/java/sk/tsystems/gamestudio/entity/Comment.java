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

	public Comment() {
	}
	
	public Comment(String username, String content, String game) {
		this.username = username;
		this.content = content;
		this.game = game;
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
