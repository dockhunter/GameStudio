package sk.tsystems.gamestudio.games.minesweeper;

import sk.tsystems.gamestudio.games.minesweeper.core.Field;

public interface UserInterface {

	/**
	 * Starts the game.
	 * @param field field of mines and clues
	 */
	void newGameStarted(Field field);

	/**
	 * Updates user interface - prints the field.
	 */
	void update();

}