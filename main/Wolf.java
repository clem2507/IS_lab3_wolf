package main;

import java.util.List;

public interface Wolf {
	public abstract int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight);
	// returns an array with 2 elements in {-1,0,1} where the 
	// first integer indicates the ROW movement, and the second the COL movement
	// Hence, diagonal movement is possible

	public abstract int moveLim(List<int[]> wolvesSight, List<int[]> preysSight);
	// returns 0 for No Movement, 1 for North, 2 for East, 3 for South, 4 for West
	// Hence, no diagonal movement is possible

	/**
	 * Method that applies the priority of operations for the agent based on its current state
	 * @param wolvesSight list of coordinates that corresponds the the positions of other wolves
	 * @param preysSight list that contains possible coordinates of a prey if situated in the agent range of view
	 * @return 2D integer array of the best action to take
	 */
	public abstract int[] actionChooser(List<int[]> wolvesSight, List<int[]> preysSight);

	/**
	 * Function that returns a random move based on the possible action range given to the agent (limited or not)
	 * @return 2D integer array with a random move
	 */
	public abstract int[] randomMove();
}
