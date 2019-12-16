package sk.tsystems.gamestudio.games.puzzlegame;

import java.io.*;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.games.puzzlegame.Field;
import sk.tsystems.gamestudio.games.puzzlegame.Tile;
import sk.tsystems.gamestudio.services.ScoreService;
import sk.tsystems.gamestudio.services.ScoreServicesJDBC;

public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	private int count = 0;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	ScoreService scoreService = new ScoreServicesJDBC();
	String name;

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		System.out.println("Welcome to PUZZLE GAME\n" + "\nTOP 10 PLAYERS: ");
		printHighScores();
		System.out.println("\nEnter your name:");
		try {
			name = input.readLine();
		} catch (IOException e) {
			System.err.println("Wrong input" + e.getMessage());
		}
		do {
			update();
			processInput();
			if (field.isSolved())
				field.setState(GameState.SOLVED);
		} while (field.getState().equals(GameState.PLAYING));
			if (field.getState().equals(GameState.FAILED)) {
				System.out.println("Game Over.");
			} else {
				update();
				System.out.println("YOU WON!\n" + "Your number of moves (score):" + count);
				scoreService.addScore(new Score(name, "puzzlegame", count));
				printHighScores();
			}
		System.out.println("\n\n\n\n");

	}

	private void printHighScores() {
		int x = 1;
		System.out.println("___________________");
		for (Score score : scoreService.getTopScorePuzzleGame("puzzlegame"))
			System.out.println("| " + x++ + ". " + score.getUsername() + " " + score.getValue());
		System.out.println("___________________");

	}

	@Override
	public void update() {
		for (int x = 0; x < field.getRowCount(); x++) {
			System.out.printf("%n");
			for (int z = 0; z < field.getColumnCount(); z++) {
				Tile tile = field.getTile(x, z);
				if (tile.getValue() > 0) {
					if (tile.getValue() < 10) {
						System.out.print("   " + tile.getValue());
					} else {
						System.out.print("  " + tile.getValue());
					}
				} else {
					System.out.print("   " + tile.getValue());
				}
			}
		}

		System.out.println("\n\n---------------");
	}

	private void processInput() {
		System.out.println("INSERT NUMBER TO MOVE A TILE" + "\n<X> EXIT" + "\n---------------");
		try {
			handleInput(readLine());
			count++;
		} catch (Exception ex) {
			System.err.println("Wrong format. " + ex.getMessage());
		}

	}

	/**
	 * Processes user input with an exception handler.
	 * 
	 * throws WrongFormatException
	 */
	private void handleInput(String input) throws Exception {
		if (input.equalsIgnoreCase("X")) {
			field.setState(GameState.FAILED);
		} else if (Integer.parseInt(input) < (field.getColumnCount() * field.getRowCount())) {
			field.moveTile(Integer.parseInt(input));
		} else
			System.err.println("Wrong input");
	}

	public int getCount() {
		return count;
	}

}
