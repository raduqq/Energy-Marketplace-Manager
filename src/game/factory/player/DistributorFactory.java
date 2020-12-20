package game.factory.player;

import common.Constants;
import game.player.Distributor;
import game.player.Player;

public final class DistributorFactory implements PlayerFactory {
    @Override
    public Player create(final String[] args) {
        int id = Integer.parseInt(args[Constants.ZEROTH_ARG]);
        int budget = Integer.parseInt(args[Constants.FIRST_ARG]);
        int contractLength = Integer.parseInt(args[Constants.SECOND_ARG]);
        int infrastructureCosts = Integer.parseInt(args[Constants.THIRD_ARG]);
        int productionCosts = Integer.parseInt(args[Constants.FOURTH_ARG]);

        return new Distributor(id,
                budget,
                contractLength,
                infrastructureCosts,
                productionCosts);
    }
}
