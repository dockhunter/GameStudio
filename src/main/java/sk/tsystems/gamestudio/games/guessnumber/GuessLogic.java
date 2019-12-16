package sk.tsystems.gamestudio.games.guessnumber;

import java.util.Random;

public class GuessLogic {

	public int lowerLimit, upperLimit, number;
	static Random rnd = new Random();

	GuessLogic() {
		getNumber();
	}
	
	public void generateNumber(int lowerLimit, int upperLimit) {
		int randomNumber = rnd.nextInt((upperLimit+1)-lowerLimit) + lowerLimit;
			number = randomNumber;
	}

	public int getNumber() {
		return number;
	}

}
