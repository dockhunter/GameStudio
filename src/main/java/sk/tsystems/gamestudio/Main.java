package sk.tsystems.gamestudio;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.services.*;

public class Main {


	Main() throws Exception {
		new ConsoleUI().menu();
	}
	public static void main(String[] args) throws Exception {
//		ScoreService scoreService = new ScoreServicesJDBC();
//		scoreService.addScore(new Score("danka", "npuzzle", 456));
//		scoreService.addScore(new Score("stefka", "npuzzle", 798));
		
//		for(Score score: scoreService.getTopScore("npuzzle"))
//			System.out.println(score.getUsername() + " " + score.getValue());
		new Main();
	
		
		
		
		
		
	}

}
