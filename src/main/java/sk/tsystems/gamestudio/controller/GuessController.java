package sk.tsystems.gamestudio.controller;

import java.util.ArrayList;
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

import java.text.SimpleDateFormat;  
import java.util.Date; 

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/guessnumber")
public class GuessController {
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();  

	List<String> commentary = new ArrayList<>();
	
	int solved;
	int score;

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
		solved = 0;
		score = 0;
		commentary.clear();
		return "guessnumber";
	}

	@RequestMapping("/input")
	public String processInput(String guess) {
		Formatter f = new Formatter();
		if (!guess.isBlank() && !guess.equals(null)) {
			try {
				answer = Integer.parseInt(guess);
			} catch (Exception ex) {
				if (mainContoller.isLogged())
					commentary.add(mainContoller.getLoggedPlayer().getName() + ": " + guess + "<br>"
							+ "<p class='ai red'>AI: Please use only numbers</p>");
				else
					commentary.add("You: " + guess + "<br>" + "<p class='ai red'>AI: Please use only numbers</p>");
					answer = null;
			}
			if (answer != null && answer != 0) {
				if (mainContoller.isLogged()) {
					commentary.add(mainContoller.getLoggedPlayer().getName() + ": " + guess);
				} else {
					commentary.add("You: " + guess);
				}
			}
		}
		return "guessnumber";
	}

	@RequestMapping("/comment")
	public String comment(String content) {
		Formatter f = new Formatter();
		try {
			if (mainContoller.isLogged())
				scoreService.addComment(new Comment(mainContoller.getLoggedPlayer().getName(), content, "guessnumber", formatter.format(date)));
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
			if (answer == number.getNumber()) {
				f.format("<br><br><span class='t' id='data'>AI: Yes! You are correct! My number was </span>" + number.getNumber() + "<br><br>");
				solved = 1;
				scoreService.addScore(new Score(mainContoller.getLoggedPlayer().getName(), "guessnumber", score));
			} else if (answer == number.getNumber() + 1 || answer == number.getNumber() - 1) {
				f.format("<p class='ai t'>AI: You are really close! Try again.</p>");
				commentary.add("<p class='ai'>AI: You are really close! Try again.</p>");
			} else if (answer < lowerLimit || answer > upperLimit) {
				f.format("<p class='ai t red'>AI: Warning! You are not guessing in the range 1 to 10.</p>");
				commentary.add("<p class='ai red'>AI: Warning! You are not guessing in the range 1 to 10.</p>");
			} else {
				int randomNumbers = rnd.nextInt(6) + 1;
				switch (randomNumbers) {
				case 1:
					f.format("<p class='ai t'>AI: No. try again...</p>");
					commentary.add("<p class='ai'>AI: No. try again...</p>");
					break;
				case 2:
					f.format("<p class='ai t'>AI: No...</p>");
					commentary.add("<p class='ai'>AI: No...</p>");
					break;
				case 3:
					f.format("<p class='ai t'>AI: You are not even close...</p>");
					commentary.add("<p class='ai'>AI: You are not even close...</p>");
					break;
				case 4:
					f.format("<p class='ai t'>AI: Really??... No! Guess again...</p>");
					commentary.add("<p class='ai'>AI: Really??... No! Guess again...</p>");
					break;
				case 5:
					f.format("<p class='ai t'>AI: Boooooring... Try again...</p>");
					commentary.add("<p class='ai'>AI: Boooooring... Try again...</p>");
					break;
				case 6:
					f.format("<p class='ai t'>AI: You almoust had it! ..No just kidding... Try again!</p>");
					commentary.add("<p class='ai'>AI: You almoust had it! ..No just kidding... Try again!</p>");
					break;
				}
			}
		} catch (Exception ex) {
		}
		score++;
		answer = null;
		return f.toString();
	}

	public String getConversation() {
		Formatter f = new Formatter();
		for (String string : commentary) {
			f.format(string);
		}
		return f.toString();
	}

	public boolean isSolved() {
		if (solved != 1)
			return false;
		return true;
	}

	public List<String> getCommentary() {
		return commentary;
	}
	
	public List<Score> getScores() {
		return scoreService.getTopScore("guessnumber");
	}
	
	public List<Comment> getComments() {
		return scoreService.getComment("guessnumber");
	}

	public double getRatings() {
		return ratingService.getAverageRating("guessnumber");
	}

	public void getClear() {
		commentary.clear();
	}

}
