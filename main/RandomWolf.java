package main;

import java.util.List;
import java.util.Random;

/**
 * This class implements a totally random strategy that can be used by an agent
 */
public class RandomWolf implements Wolf {

    private String actionRange;

    @Override
    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        this.actionRange = "all";
        return actionChooser(wolvesSight, preysSight);
    }

    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        this.actionRange = "lim";
        return actionChooser(wolvesSight, preysSight)[0];
    }

    // Description of the method in the Wolf interface
    @Override
    public int[] actionChooser(List<int[]> wolvesSight, List<int[]> preysSight) {
        return randomMove();
    }

    // Description of the method in the Wolf interface
    @Override
    public int[] randomMove() {
        int[] mymove;
        Random r = new Random();
        if (actionRange.equals("all")) {
            mymove = new int[2];
            mymove[0] = r.nextInt(3)-1;
            mymove[1] = r.nextInt(3)-1;
        }
        else {
            mymove = new int[2];
            mymove[0] = r.nextInt(4) + 1;
        }
        return mymove;
    }
}
