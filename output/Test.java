package output;

import main.Wolves;
import main.WolvesApp;

public class Test {

    public static int counter = 0;
    public static int numOfIterations = 1000;
    public static String[] data = new String[numOfIterations];
    public static OutputCSV output;

    public static void main(String[]args) {

        startGame();
    }

    public static void startGame() {

        output = new OutputCSV("Wolf_Wolf_Prey.csv", "#turns");

        WolvesApp wol = new WolvesApp("Hungry Hungry main.Wolves", 50, 50, 15);
        wol.runGoL(1);
    }
}
