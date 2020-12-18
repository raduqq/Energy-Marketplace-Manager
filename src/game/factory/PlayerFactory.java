package game.factory;

import game.player.Player;

public interface PlayerFactory {
    Player create(String[] args);
}
