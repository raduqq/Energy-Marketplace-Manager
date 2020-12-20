package game.factory.player;

import game.player.Player;

public interface PlayerFactory {
    Player create(String[] args);
}
