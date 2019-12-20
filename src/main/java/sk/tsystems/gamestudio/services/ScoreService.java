package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.entity.Rating;

public interface ScoreService {
	
	void addScore(Score score);
	
	void addComment(Comment comment);
	
	List<Score> getTopScoreMinesweeper(String game);
	
	List<Score> getTopScorePuzzleGame(String game);
	
	List<Comment> getComment(String game);
	
	List<Score> getTopScore(String game);
	
	List<Score> getAnswers(String game);
	
}
