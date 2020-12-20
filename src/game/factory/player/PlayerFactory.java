package game.factory.player;

import game.player.Player;

public interface PlayerFactory {
    /**
     * Creates player
     * @param args needed to construct player
     * @return instantiated player
     */
    Player create(String[] args);
}
