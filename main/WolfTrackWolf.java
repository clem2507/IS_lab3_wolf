package main;

import java.util.List;
import java.util.Random;

/**
 * This class implements a strategy that can be used by an agent to only track possible preys
 */
public class WolfTrackWolf implements Wolf {

    private List<int[]> wolves;
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

    // Description of the method in the Wolf interface
    @Override
    public int[] actionChooser(List<int[]> wolvesSight, List<int[]> preysSight){
        int[] tracking_wolf = wolfFollowing(wolvesSight);
        if (tracking_wolf != null) {
            return track(wolvesSight.get(wolf2track));
        }
        else {
            return randomMove();
        }
    }

    // Description of the method in the Wolf interface
    @Override
    public int[] randomMove(){
        int[] mymove;
        Random r = new Random();
        if (actionRange.equals("all")) {
            mymove = new int[2];
            mymove[0] = r.nextInt(2);
            mymove[1] = r.nextInt(2);
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
}
