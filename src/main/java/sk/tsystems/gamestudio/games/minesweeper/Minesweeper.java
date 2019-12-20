package sk.tsystems.gamestudio.games.minesweeper;

import sk.tsystems.gamestudio.games.minesweeper.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.games.minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
    /** User interface. */
    private UserInterface userInterface;
    
//    /** Saves the starting time of the game */
//    private long startMillis;
    
    
    /**
     * Constructor.
     */
    public Minesweeper() {
        userInterface = new ConsoleUI();
     
        Field field = new Field(9, 9, 10);
        userInterface.newGameStarted(field);
        field.setStartMillis(System.currentTimeMillis());

    }

    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Minesweeper();
    }
//    
//    /** Returns the play time
//     * 
//     */
//    public int getPlayingSeconds() {
//    	int startSeconds = (int) (startMillis / 1000) % 60 ;
//    	int endSeconds = (int) (System.currentTimeMillis() / 1000) % 60 ;
//       	return endSeconds - startSeconds;
//        
//    }
}
