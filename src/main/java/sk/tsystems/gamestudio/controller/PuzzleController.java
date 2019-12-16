package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.services.ScoreService;
import sk.tsystems.gamestudio.games.puzzlegame.*;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/puzzle")
public class PuzzleController {

	private Field field;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private MainController mainContoller;

	@RequestMapping
	public String index() {
		field = new Field(4, 4);
		return "puzzle";
	}

	@RequestMapping("/move")
	public String move(int tile) {
		field.moveTile(tile);
		if(field.isSolved() && mainContoller.isLogged()) {
			scoreService.addScore(new Score(mainContoller.getLoggedPlayer().getName(), "puzzle", 10));
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
				if (tile != null)
					f.format("<a href='/puzzle/move?tile=%d'><img src='/img/img%d.jpg'></a>", tile.getValue(), tile.getValue());
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
}
