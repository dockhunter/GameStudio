package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Score;

public interface ScoreService {
	
	void addScore(Score score);
	
	List<Score> getTopScoreMinesweeper(String game);
	
	List<Score> getTopScorePuzzleGame(String game);
	
	List<Score> getTopScore(String game);
}
