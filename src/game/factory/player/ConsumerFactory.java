package game.factory.player;

import game.entity.player.Consumer;
import game.entity.player.Player;

public final class ConsumerFactory implements PlayerFactory {
    @Override
    public Player create(final String[] args) {
        int id = Integer.parseInt(args[0]);
        int budget = Integer.parseInt(args[1]);
        int monthlyIncome = Integer.parseInt(args[2]);

        return new Consumer(id, budget, monthlyIncome);
    }
}
