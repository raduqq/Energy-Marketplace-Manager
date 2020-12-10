package fileio.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;

import java.io.File;
import java.io.IOException;

public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    private final File inputFile;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
        inputFile = new File(inputPath);
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * The method reads the game.database
     * @return an Input object
     */
    public Input readData() {
        ObjectMapper objectMapper = new ObjectMapper();
        Game gameData = new Game();

        try {
            gameData = objectMapper.readValue(inputFile, Game.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return new Input(gameData);
    }
}
