package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.services.PlayerService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {

	private Player loggedPlayer;
	int message;

//	private Player registeredPlayer;

	@Autowired
	private PlayerService playerService;

	@RequestMapping("/login")
	public String login(String name, String password) {
		try {
			if (name.equals(getPlayerName(name)))
				if (password.equals(getPlayerPassword(password))) {
					loggedPlayer = new Player(name, password);
				}
			else
				message = 1;
		} catch (Exception ex) {
			message = 3;
		}
		return "redirect:/";
	}

	public String getMessage() {
		Formatter f = new Formatter();
		try {
			switch (message) {
			case 1:
				f.format("<p class='red center'>Wrong password or username</p>");
				break;
			case 2:
				f.format("<p class='red center-l'>Username already exists!<br> If you already have an account: <a href='/' class='link'>Log in</a></p>");
				message = 0;
				break;
			case 3:
				f.format("<p class'red center-l'>Somethink went wrong.<br> Please refresh the page and try again.</p>");
				break;
			default:
				f.format("");
				break;
			}
		} catch (Exception e) {
		}
		return f.toString();
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/logout")
	public String logout() {
		loggedPlayer = null;
		return "redirect:/";
	}

	public boolean isLogged() {
		return loggedPlayer != null;
	}

	public Player getLoggedPlayer() {
		return loggedPlayer;
	}

	public void setLoggedPlayer(Player player) {
		loggedPlayer = player;
	}

	public void getClearMessage() {
		message = 0;		
	}
	
	public String getPlayerName(String name) {
		for (Player player : playerService.getPlayerName(name))
			if (player.getName().equals(name))
				return name;
		return null;
	}

	public String getPlayerPassword(String password) {
		for (Player player : playerService.getPlayerPassword(password))
			if (player.getPassword().equals(password))
				return password;
		return null;
	}
}
