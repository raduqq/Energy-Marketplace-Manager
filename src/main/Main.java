package main;

import fileio.input.InputLoader;
import game.Game;
import game.database.Consumers;
import game.database.Distributors;

public class Main {
    public static void main(String[] args) throws Exception {
        // inputPath = args[0]
        // outputPath = args[1]
        String inputPath = args[0];
        InputLoader inputLoader = new InputLoader(inputPath);
        inputLoader.loadData();

        // TODO: fa-l singleton sau ceva, arata ft urat asa.
        Game.play(inputLoader.getInputData().getMonthlyUpdates());
    }
}
