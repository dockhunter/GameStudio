package sk.tsystems.gamestudio.games.minesweeper.core;

/**
 * Game state.
 */
public enum GameState {
    /** Playing game. */
    PLAYING,
    
    /** Game failed. */
    FAILED,
    
    /** Game solved. */
    SOLVED,
    
    /** Marking tiles. */
    MARKING
}
