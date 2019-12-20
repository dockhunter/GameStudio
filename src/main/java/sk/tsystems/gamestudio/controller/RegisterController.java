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
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private MainController mainContoller;

	@Autowired
	private PlayerService playerService;

	@RequestMapping
	public String index() {
		mainContoller.message = 0;
		return "register";
	}

	@RequestMapping("/regist")
	public String register(String name, String password) {
		try {
			if (!(name.equals(getPlayerName(name)))) {
				Player newPlayer = new Player(name, password);
				playerService.addPlayer(newPlayer);
				mainContoller.setLoggedPlayer(newPlayer);
			} else if (name.equals(getPlayerName(name))) {
				mainContoller.message = 2;
				return "register";
			} else {
				mainContoller.message = 3;
			}
		} catch (Exception ex) {
			mainContoller.message = 2;
		}
		return "redirect:/";
	}

	public String getPlayerName(String name) {
		for (Player player : playerService.getPlayerName(name))
			if (name.equals(player.getName()))
				return name;
		return null;
	}

	public String getPlayerPassword(String password) {
		for (Player player : playerService.getPlayerPassword(password))
			if (password.equals(player.getPassword()))
				return password;
		return null;
	}
	
}
