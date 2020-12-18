package game.factory;

import game.player.Distributor;
import game.player.Player;

public class DistributorFactory implements PlayerFactory{
    @Override
    public Player create(String[] args) {
        int id = Integer.parseInt(args[0]);
        int budget = Integer.parseInt(args[1]);
        int contractLength = Integer.parseInt(args[2]);
        int infrastructureCosts = Integer.parseInt(args[3]);
        int productionCosts = Integer.parseInt(args[4]);

        return new Distributor(id,
                budget,
                contractLength,
                infrastructureCosts,
                productionCosts);
    }
}
