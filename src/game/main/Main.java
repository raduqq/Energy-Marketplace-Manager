package game.main;

import fileio.input.Input;
import fileio.input.InputLoader;

public class Main {

    public static void main(String[] args) throws Exception {
        // inputPath = args[0]
        // outputPath = args[1]
        String inputPath = args[0];
        InputLoader inputLoader = new InputLoader(inputPath);
        Input input = inputLoader.readData();
        System.out.println(input.getGameData());
    }
}
