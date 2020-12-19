package main;

import fileio.input.InputLoader;
import fileio.output.OutputWriter;
import game.Game;
import game.database.Consumers;
import game.database.Distributors;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = args[0];
        String outputPath = args[1];

        // Loading input
        InputLoader inputLoader = new InputLoader(inputPath);
        inputLoader.loadData();

        // TODO: fa-l singleton sau ceva, arata ft urat asa.
        Game.play(inputLoader.getInputData().getMonthlyUpdates());

        // Uploading output
        OutputWriter outputWriter = new OutputWriter(outputPath);
        outputWriter.uploadData();

        // Clearing all data
        Game.reset();
    }
}
