package sk.tsystems.gamestudio.games.guessnumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ConsoleUI {

	GuessLogic number = new GuessLogic();
	int upperLimit;
	int lowerLimit;

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private int answer;
	Random rnd = new Random();

	public void guessingStarts() {
		System.out.println("Set the range, From:To");
		processRange();
		do {
			processInput();
		} while (!checkAnswer());
		System.out.println("\n\n\n\n");

	}

	public void processRange() {
		try {
			do {
				lowerLimit = 0;
				upperLimit = 0;
				Pattern pattern = Pattern.compile("(\\d+)(.)(\\d+)");
				Matcher matcher = pattern.matcher(input.readLine());
				if (matcher.matches()) {
					String x = matcher.group(1);
					String y = matcher.group(3);
					lowerLimit = Integer.parseInt(x);
					upperLimit = Integer.parseInt(y);
				} else if (!matcher.matches()  || lowerLimit < 0 || lowerLimit > upperLimit || upperLimit < 0)
					System.err.println("This is not a range! Insert range again.");
			} while ((lowerLimit == 0 && upperLimit == 0));
			number.generateNumber(lowerLimit, upperLimit);
			System.out.println("Guess what number do I think of.");
		} catch (Exception ex) {
			System.err.println("I do not understand.\nPlease use only numbers");
			processRange();
		}
	}

	public void processInput() {
		try {
			handleInput(input.readLine());
		} catch (Exception ex) {
			System.err.println("I do not understand.\nPlease use only numbers");
		}
	}

	public void handleInput(String s) {
		answer = Integer.parseInt(s);
		if (answer == number.getNumber())
			System.out.println("Your answer is correct!");
		else if (answer == number.getNumber() + 1 || answer == number.getNumber() - 1)
			System.out.println("You are really close! Try again.");
		else if (answer < lowerLimit || answer > upperLimit)
			System.err.println("Warning! You are not guessing in range.");
		else {
			int randomNumbers = rnd.nextInt(6) + 1;
			switch (randomNumbers) {
			case 1:
				System.out.println("No. try again...");
				break;
			case 2:
				System.out.println("No...");
				break;
			case 3:
				System.out.println("You are not even close...");
				break;
			case 4:
				System.out.println("Really??... No! Guess again...");
				break;
			case 5:
				System.out.println("Boooooring... Try again...");
				break;
			case 6:
				System.out.println("You almoust had it! ..No just kidding... Try again!");
				break;
			}
		}
	}

	private boolean checkAnswer() {
		if (answer != number.getNumber())
			return false;
		return true;
	}
}
