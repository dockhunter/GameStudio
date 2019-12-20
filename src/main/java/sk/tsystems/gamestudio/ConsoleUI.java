package sk.tsystems.gamestudio;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import sk.tsystems.gamestudio.games.minesweeper.GuessNumber;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.puzzlegame.Puzzle15;

public class ConsoleUI {

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	int inputNumber;

	public void menu() {
		do {
			processInput();
		} while (true);
	}
	public void processInput() {
		try {
			System.out.println("Choose a game by number:\n\n" + "1 - Minesweeper\n" + "2 - Puzzlegame\n" + "3 - GuessNumber\n"
					+ "0 - Exit GameStudio");
			handleInput(Integer.parseInt(input.readLine()));
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	public void handleInput(int inputNumber) {
		switch (inputNumber) {
		case 1:
			new Minesweeper();
			break;
		case 2:
			new Puzzle15();
			break;
		case 3:
			new GuessNumber();
			break;
		case 0:
			System.out.println("See you later!");
			System.exit(0);
			break;
		}
	}
}
