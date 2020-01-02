package sk.tsystems.gamestudio.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.services.RatingService;
import sk.tsystems.gamestudio.services.ScoreService;
import sk.tsystems.gamestudio.games.puzzlegame.*;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/puzzle")
public class PuzzleController {

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	
	int x;

	private Field field;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private MainController mainContoller;

	@RequestMapping
	public String index() {
		field = new Field(4, 4);
		x= 0;
		return "puzzle";
	}

	@RequestMapping("/move")
	public String move(int tile) {
		field.moveTile(tile);
		field.setScore(++x);
		if (field.isSolved() && mainContoller.isLogged()) {
			scoreService.addScore(new Score(mainContoller.getLoggedPlayer().getName(), "puzzle", field.getScore()));
		}
		return "puzzle";
	}

	@RequestMapping("/comment")
	public String comment(String content) {
		Formatter f = new Formatter();
		try {
			if (mainContoller.isLogged())
				scoreService.addComment(new Comment(mainContoller.getLoggedPlayer().getName(), content, "puzzle",
						formatter.format(date)));
		} catch (Exception ex) {
		}
		return "puzzle";
	}

	@RequestMapping("/rating")
	public String rating(int rate) {
		Formatter f = new Formatter();
		try {
			if (mainContoller.isLogged())
				// if (rate > 0 || rate < 6)
				ratingService.setRating(new Rating(mainContoller.getLoggedPlayer().getName(), rate, "puzzle"));
		} catch (Exception ex) {
		}
		return "puzzle";
	}

	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (!field.isSolved()) {
					if (tile != null)
						f.format("<a href='/puzzle/move?tile=%d'><img src='/img/img-%d.jpg' class='puzzle_tile'></a>",
								tile.getValue(), tile.getValue());
					else
						f.format("<a href='/puzzle/move?tile=%d'><img src='/img/img-0.jpg' class='puzzle_tile'></a>",
								tile.getValue(), tile.getValue());
				} else if (field.isSolved()) {
					if (tile != null)
						f.format("<img src='/img/img-%d.jpg' class='puzzle_tile'>",
								tile.getValue(), tile.getValue());
					else
						f.format("<img src='/img/img-0.jpg' class='puzzle_tile'>",
								tile.getValue(), tile.getValue());
				}
				f.format("</td>\n");
			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");
		return f.toString();
	}

//	public String getMessage() {
//		return "Hello from Java";
//	}

	public boolean isSolved() {
		return field.isSolved();
	}

	public List<Score> getScores() {
		return scoreService.getTopScore("puzzle");
	}

	public List<Comment> getComments() {
		return scoreService.getComment("puzzle");
	}

	public double getRatings() {
		return ratingService.getAverageRating("puzzle");
	}
}
