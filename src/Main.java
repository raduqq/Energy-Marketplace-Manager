import common.Constants;
import fileio.input.InputLoader;
import fileio.output.OutputWriter;
import game.Game;


public final class Main {
    private Main() {
    }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     */
    public static void main(final String[] args) {
        String inputPath = args[Constants.ZEROTH_ARG];
        String outputPath = args[Constants.FIRST_ARG];

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
