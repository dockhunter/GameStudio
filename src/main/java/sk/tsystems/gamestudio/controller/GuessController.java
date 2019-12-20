package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.*;
import sk.tsystems.gamestudio.games.guessnumber.*;
import sk.tsystems.gamestudio.services.*;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/guessnumber")
public class GuessController {

	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private RatingService ratingService;

	@Autowired
	private MainController mainContoller;

	Random rnd = new Random();

	public Integer answer;
	int upperLimit = 10;
	int lowerLimit = 1;

	GuessLogic number = new GuessLogic();

	@RequestMapping
	public String index() {
		number.generateNumber(lowerLimit, upperLimit);
		answer = null;
		return "guessnumber";
	}

	@RequestMapping("/input")
	public String processInput(String guess) {
		Formatter f = new Formatter();
		try {
			answer = 0;
			answer = Integer.parseInt(guess);
		} catch (Exception ex) {
		}
		return "guessnumber";
	}

	@RequestMapping("/comment")
	public String comment(String content) {
		Formatter f = new Formatter();
		try {
			if (mainContoller.isLogged())
				scoreService.addComment(new Comment(mainContoller.getLoggedPlayer().getName(), content, "guessnumber"));
		} catch (Exception ex) {
		}
		return "guessnumber";
	}

	@RequestMapping("/rating")
	public String rating(int rate) {
		Formatter f = new Formatter();
		try {
			if (mainContoller.isLogged())
				// if (rate > 0 || rate < 6)
				ratingService.setRating(new Rating(mainContoller.getLoggedPlayer().getName(), rate, "guessnumber"));
		} catch (Exception ex) {
		}
		return "guessnumber";
	}

	public String getMessage() {
		Formatter f = new Formatter();
		try {
//			if (!String.valueOf(answer).isBlank())			
			if (answer == number.getNumber())
				f.format("Your answer is correct!");
			else if (answer == number.getNumber() + 1 || answer == number.getNumber() - 1)
				f.format("You are really close! Try again.");
			else if (answer < lowerLimit || answer > upperLimit)
				f.format("Warning! You are not guessing in range.");
			else {
				int randomNumbers = rnd.nextInt(6) + 1;
				switch (randomNumbers) {
				case 1:
					f.format("No. try again...");
					break;
				case 2:
					f.format("No...");
					break;
				case 3:
					f.format("You are not even close...");
					break;
				case 4:
					f.format("Really??... No! Guess again...");
					break;
				case 5:
					f.format("Boooooring... Try again...");
					break;
				case 6:
					f.format("You almoust had it! ..No just kidding... Try again!");
					break;
				}
			}
		} catch (Exception ex) {
		}
		return f.toString();
	}

	public boolean isSolved() {
		if (answer == null || answer != number.getNumber())
			return false;
		return true;
	}

	public List<Comment> getComments() {
		return scoreService.getComment("guessnumber");
	}

	public double getRatings() {
		return ratingService.getAverageRating("guessnumber");
	}

}
