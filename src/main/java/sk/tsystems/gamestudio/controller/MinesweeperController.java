package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.games.minesweeper.core.*;
import sk.tsystems.gamestudio.services.*;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesweeperController {

	private Field field;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private MainController mainContoller;
	
	@RequestMapping("/minesweeper")
	public String index() {
		field = new Field(9, 9, 4);
		return "minesweeper";

	}

	@RequestMapping("/minesweeper/open")
	public String open(int row, int column) {
		field.openTile(row, column);
		if(field.isSolved() && mainContoller.isLogged()) {
			scoreService.addScore(new Score(mainContoller.getLoggedPlayer().getName(), "minesweeper", 10));
		}
		return "minesweeper";

	}

	@RequestMapping("/minesweeper/markTile")
	public String markTile(int row, int column) {
		field.markTile(row, column);
		return "minesweeper";

	}

	@RequestMapping("/minesweeper/mark")
	public String mark() {
		if (field.getState().equals(GameState.PLAYING))
			field.setState(GameState.MARKING);
		else if (field.getState().equals(GameState.MARKING))
			field.setState(GameState.PLAYING);
		return "minesweeper";

	}

	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (field.getState().equals(GameState.PLAYING)) {
					if (tile.getState() == Tile.State.CLOSED)
						f.format(
								"<a href='/minesweeper/open?row=%d&column=%d'><img src='/images/tile.jpg' class='img'></a>",
								row, column);
					else if (tile instanceof Mine && tile.getState() == Tile.State.OPEN) {
						f.format("<img src='/images/bomb.jpg' class='img'>");
						field.setState(GameState.FAILED);
					} else if (tile instanceof Clue && tile.getState() == Tile.State.OPEN) {
						Clue clue = (Clue) tile;
						int value = clue.getValue();
						f.format("<img src='/images/%d.jpg' class='img'>", value);
					} else if (tile.getState() == Tile.State.MARKED)
						f.format("<img src='/images/marked.jpg' class='img'>");
				} else if (field.getState().equals(GameState.MARKING)) {
					if (tile.getState() == Tile.State.CLOSED)
						f.format(
								"<a href='/minesweeper/markTile?row=%d&column=%d'><img src='/images/tile.jpg' class='img'></a>",
								row, column);
					else if (tile.getState() == Tile.State.MARKED)
						f.format(
								"<a href='/minesweeper/markTile?row=%d&column=%d'><img src='/images/marked.jpg' class='img'></a>",
								row, column);
					else if (tile instanceof Mine && tile.getState() == Tile.State.OPEN) {
						f.format("<img src='/images/bomb.jpg' class='img'>");
						field.setState(GameState.FAILED);
					} else if (tile instanceof Clue && tile.getState() == Tile.State.OPEN) {
						Clue clue = (Clue) tile;
						int value = clue.getValue();
						f.format("<img src='/images/%d.jpg' class='img'>", value);
					}
				} else {
					if (tile.getState() == Tile.State.CLOSED && !(tile instanceof Mine))
						f.format("<img src='/images/tile.jpg' class='img'>", row, column);
					else if (tile instanceof Mine && tile.getState() == Tile.State.OPEN) {
						f.format("<img src='/images/bomb.jpg' class='img'>");
						field.setState(GameState.FAILED);
					} else if (tile instanceof Clue && tile.getState() == Tile.State.OPEN) {
						Clue clue = (Clue) tile;
						int value = clue.getValue();
						f.format("<img src='/images/%d.jpg' class='img'>", value);
					} else if (tile instanceof Mine || tile.getState() == Tile.State.MARKED)
						f.format("<img src='/images/marked.jpg' class='img'>");
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

	public boolean isFailed() {
		if (!(field.getState().equals(GameState.FAILED)))
			return false;

		return true;
	}

	public boolean isSolved() {
		if (!(field.getState().equals(GameState.SOLVED)))
			return false;

		return true;
	}

	public boolean isPlaying() {
		if (!(field.getState().equals(GameState.PLAYING)))
			return false;

		return true;
	}
	
	public boolean isMarking() {
		if (!(field.getState().equals(GameState.MARKING)))
			return false;

		return true;
	}
	
	public List<Score> getScores() {
		return scoreService.getTopScore("minesweeper");
	}
	
//	public String getHighScores() {
//		Formatter f = new Formatter();
//		ScoreService scoreService = new ScoreServicesJDBC();
//		int x = 1;
//		f.format("<div class='scores'>");
//		f.format("<h4>TOP 10 PLAYERS</h4><span>_______________________</span><br><br>");
//		for (Score score : scoreService.getTopScore("puzzlegame"))
//			f.format("<span>| %d . %s - %d  </span><br>", x, score.getUsername(), score.getValue());
//		f.format("<span>_______________________</span></div>");
//		
//		return f.toString();
//	}
}
