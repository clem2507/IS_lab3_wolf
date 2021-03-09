package main;

import java.util.List;
import java.util.Random;

public class SmartWolf implements Wolf {

	private List<int[]> wolves;
	private String state = "wander";
	private String actionRange;
	private int wolf2track;

	@Override
	public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
		this.actionRange = "all";
		return actionChooser(wolvesSight, preysSight);
	}

	@Override
	public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
		this.actionRange = "lim";
		return actionChooser(wolvesSight, preysSight)[0];
	}

	@Override
	public int[] actionChooser(List<int[]> wolvesSight, List<int[]> preysSight){
		//System.out.println();
		//System.out.println("New wolf "+state);
		int[] tracking_wolf = wolfFollowing(wolvesSight);
		if( state == "tracking_wolf"){
			return track(wolvesSight.get(wolf2track));
		} else if (state !="tracking_pray" && tracking_wolf!=null){
			state = "tracking_wolf";
			return tracking_wolf;
		} else if (preysSight.size()>0){
			state = "tracking_pray";
			return followPrey(preysSight);
		}else{
			state = "wander";
			return randomMove();
		}
	}

	@Override
	public int[] randomMove(){
		Random r = new Random();
		int[] mymove;
		if (actionRange.equals("all")) {
			mymove = new int[2];
			mymove[0] = 1;
			// mymove[1] = r.nextInt(3)-1;
		}
		else {
			mymove = new int[2];
			mymove[0] = r.nextInt(2) + 1;
		}
		return mymove;
	}

	public int[] track(int[] coos){
		//System.out.println("Track "+coos[0]+","+coos[1]);
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

	public int[] wolfFollowing(List<int[]> wolvesSight){
		if(wolves  == null){
			wolves = wolvesSight;
			return null;
		}else{
			for (int i = 0; i < wolvesSight.size(); i++) {
				if(wolvesSight.get(i)[1]!=wolves.get(i)[1]){
					wolves =wolvesSight;
					wolf2track = i;
					return track(wolvesSight.get(i));
				}
			}
			return null;
		}
	}

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