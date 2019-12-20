package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Player;

public interface PlayerService {
	
	void addPlayer(Player player);
	
	List<Player> getPlayerName(String name);
	
	List<Player> getPlayerPassword(String password);
}
