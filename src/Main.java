import fileio.input.InputLoader;
import fileio.output.OutputWriter;
import game.Game;

public class Main {
    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];

        // Loading input
        InputLoader inputLoader = new InputLoader(inputPath);
        inputLoader.loadData();

        // Playing
        Game.getInstance().play(inputLoader.getInputData().getMonthlyUpdates());

        // Uploading output
        OutputWriter outputWriter = new OutputWriter(outputPath);
        outputWriter.uploadData();

        // Clearing all data
        Game.getInstance().reset();
    }
}
