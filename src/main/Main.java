package main;

import fileio.input.InputLoader;
import game.Game;

public class Main {
    public static void main(String[] args) throws Exception {
        // inputPath = args[0]
        // outputPath = args[1]
        String inputPath = args[0];
        InputLoader inputLoader = new InputLoader(inputPath);
        inputLoader.loadData();
        Game.play();
    }
}
