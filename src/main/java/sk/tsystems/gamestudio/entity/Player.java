package sk.tsystems.gamestudio.entity;

import javax.persistence.*;

@Entity
public class Player {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private String name;
	
	private String password;
	
	public Player() {
		
	}
	
	public Player(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
