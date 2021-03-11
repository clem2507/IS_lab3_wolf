package main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class implements a smart strategy that can be used by an agent
 */
public class SmartWolf implements Wolf {

	private List<int[]> wolves;
	private String state = "wander";
	private String actionRange;
	private int wolf2track;

	@Override
	public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
		this.actionRange = "all";
		for (int i = 0; i < Wolves.previousStates.length; i++) {
			if (wolvesSight.get(i)[0] == 0 && wolvesSight.get(i)[1] == 0) {
				Wolves.previousStates[i] = state;
			}
		}
		return actionChooser(wolvesSight, preysSight);
	}

	@Override
	public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
		this.actionRange = "lim";
		for (int i = 0; i < Wolves.previousStates.length; i++) {
			if (wolvesSight.get(i)[0] == 0 && wolvesSight.get(i)[1] == 0) {
				Wolves.previousStates[i] = state;
			}
		}
		return actionChooser(wolvesSight, preysSight)[0];
	}

	// Description of the method in the Wolf interface
	@Override
	public int[] actionChooser(List<int[]> wolvesSight, List<int[]> preysSight){
		int[] tracking_wolf = wolfFollowing(wolvesSight);
		if (Wolves.flag) {
			if (state.equals("tracking_wolf")) {
				return track(wolvesSight.get(wolf2track));
			} else if (!state.equals("tracking_pray") && tracking_wolf != null) {
				state = "tracking_wolf";
				return tracking_wolf;
			} else if (preysSight.size() > 0) {
				state = "tracking_pray";
				return followPrey(preysSight);
			} else {
				state = "wander";
				return randomMove();
			}
		}
		else {
			state = "wander";
			return randomMove();
		}
	}

	// Description of the method in the Wolf interface
	@Override
	public int[] randomMove(){
		Random r = new Random();
		int[] mymove;
		if (actionRange.equals("all")) {
			mymove = new int[2];
			mymove[0] = 1;
		}
		else {
			mymove = new int[2];
			mymove[0] = r.nextInt(2) + 1;
		}
		return mymove;
	}

	/**
	 * Method that given certain coordinates on the board, return the best move to track it
	 * @param coos 2D integer array that contains the coordinates to follow
	 * @return 2D integer array of the best action to track the coordinates
	 */
	public int[] track(int[] coos){
		if (actionRange.equals("all")) {
			int x_dir = 0;
			if (coos[0] != 0) {
				x_dir = coos[0] / Math.abs(coos[0]);
			}

			int y_dir = 0;
			if (coos[1] != 0) {
				y_dir = coos[1] / Math.abs(coos[1]);
			}
			return new int[]{-x_dir, -y_dir};
		}
		else {
			int dir;
			if (Math.abs(coos[0]) > Math.abs(coos[1])) {
				if (coos[0] > 0) {
					dir = 1;
				}
				else {
					dir = 3;
				}
			}
			else {
				if (coos[1] > 0) {
					dir = 4;
				}
				else {
					dir = 2;
				}
			}
			return new int[]{dir, 0};
		}
	}

	/**
	 * Method used to know the coordinates of the best wolf to follow
	 * @param wolvesSight list of coordinates that corresponds the the positions of other wolves
	 * @return 2D integer array of the wolf coordinates to track
	 */
	public int[] wolfFollowing(List<int[]> wolvesSight){
		if(wolves  == null){
			wolves = wolvesSight;
			return null;
		}else{
			for (int i = 0; i < wolvesSight.size(); i++) {
				if(wolvesSight.get(i)[1]!=wolves.get(i)[1]){
					wolves = wolvesSight;
					wolf2track = i;
					return track(wolvesSight.get(i));
				}
			}
			return null;
		}
	}

	/**
	 * Method similar to the wolfFollowing() one, but for the preys
	 * @param preysSight list that contains possible coordinates of a prey if situated in the agent range of view
	 * @return 2D integer array of the prey coordinates to track
	 */
	public int[] followPrey(List<int[]>preysSight){

		int[] closest_pray = preysSight.get(0);
		for(int[] pray : preysSight){
			if(closest_pray[0]+closest_pray[1]>pray[0]+pray[1]){
				closest_pray = pray;
			}
		}
		int[] move = track(closest_pray);
		return move;
	}
}