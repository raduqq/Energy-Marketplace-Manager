import common.Constants;
import fileio.input.InputLoader;
import game.database.Consumers;
import game.database.Distributors;
import game.database.Producers;
import game.support.strategy.EnergyChoiceStrategy;
import game.support.strategy.GreenChoiceStrategy;
import game.support.strategy.PriceChoiceStrategy;
import game.support.strategy.QuantityChoiceStrategy;

public final class Main {
    private Main() {
    }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     */
    public static void main(final String[] args){
        String inputPath = args[Constants.ZEROTH_ARG];
        String outputPath = args[Constants.FIRST_ARG];

        // Loading input
        InputLoader inputLoader = new InputLoader(inputPath);
        inputLoader.loadData();

        /*
        // Playing
        Game.getInstance().play(inputLoader.getInputData().getMonthlyUpdates());

        // Uploading output
        OutputWriter outputWriter = new OutputWriter(outputPath);
        outputWriter.uploadData();

        // Clearing all data
        Game.getInstance().reset();
         */
    }
}
