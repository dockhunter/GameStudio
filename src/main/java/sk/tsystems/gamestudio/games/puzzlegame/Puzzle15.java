package sk.tsystems.gamestudio.games.puzzlegame;

import sk.tsystems.gamestudio.games.puzzlegame.ConsoleUI; 

public class Puzzle15 {
	
    public UserInterface userInterface;
    
    public Puzzle15() {
		
		userInterface = new ConsoleUI();
        
        Field field = new Field(2, 2);
        userInterface.newGameStarted(field);   	
    }
    
    
    
	public static void main(String[] args) {

        new Puzzle15();
    }


}
