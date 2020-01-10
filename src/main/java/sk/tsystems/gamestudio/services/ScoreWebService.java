package sk.tsystems.gamestudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.services.*;

@RestController
public class ScoreWebService {
	
	@Autowired
	private ScoreService scoreService;

//  e.g: localhost:8080/api/score?game=puzzle	
//	@RequestMapping("/api/score")
//	public List<Score> getTopScore(String game) {
//		return scoreService.getTopScore(game);
//	}

//  e.g: localhost:8080/api/score/puzzle
	@RequestMapping("/api/score/{game}")
	public List<Score> getTopScore(@PathVariable String game) {
		return scoreService.getTopScore(game);
	}
}
