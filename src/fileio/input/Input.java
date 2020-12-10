package fileio.input;

import game.Game;

public final class Input {
    private final Game GameData;

    public Input(Game gameData) {
        GameData = gameData;
    }

    public Game getGameData() {
        return GameData;
    }
}
