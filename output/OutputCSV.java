package output;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Deals with the simulations' results and outputs them to a CSV file
 */
public class OutputCSV {

    private String fileName;
    private String stringHeaderResume;

    private String filePath;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;


    public OutputCSV(String fileName, String headerResume) {

        this.fileName = fileName;
        this.stringHeaderResume = headerResume;

        this.filePath = fileName;
        try {
            this.fileWriter = new FileWriter(filePath,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bufferedWriter = new BufferedWriter(fileWriter);
        this.printWriter = new PrintWriter(bufferedWriter);
    }


    public void writeResume(String[] data) {

        try {
            printWriter.println(stringHeaderResume);

            for (int i = 0; i < data.length; i++) {
                printWriter.println(data[i]);
            }
            printWriter.flush();
            printWriter.close();
            System.out.println();
            System.out.println("Data is save in: " + fileName);
        }
        catch (Exception e) {
            System.out.println("Recorded not save");
        }
    }
}

