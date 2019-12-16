package sk.tsystems.gamestudio.games.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
    /** List of best player times. */
    private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

    /**
     * Returns an iterator over a set of  best times.
     * @return an iterator
     */
    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }

    /**
     * Adds player time to best times.
     * @param name name ot the player
     * @param time player time in seconds
     */
    public void addPlayerTime(String name, int time) {
    	
        throw new UnsupportedOperationException("Method addPlayerTime not yet implemented");
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object
     */
    public String toString() {
        throw new UnsupportedOperationException("Method toString not yet implemented");
    }
    
    
    /**
     * Player time.
     */
    public static class PlayerTime implements Comparable<PlayerTime>{
        /** Player name. */
        private final String name;

        /** Playing time in seconds. */
        private final int time;

        /**
         * Constructor.
         * @param name player name
         * @param time playing game time in seconds
         */
        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}
		
        /**
         * Compares players .
         * @param time playing game time in seconds
         */		
		public int compareTo(PlayerTime o) {	
			if (time == o.time)
			return 0;
			else if (time > o.time)
			return 1;
			else
			return -1;
		}
    }
}
