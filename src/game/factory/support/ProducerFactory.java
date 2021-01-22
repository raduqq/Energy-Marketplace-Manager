package game.factory.support;

import game.element.EnergyType;
import game.support.Producer;
import game.support.strategy.Support;

public class ProducerFactory implements SupportFactory {
    @Override
    public Support create(String[] args) {
        int id = Integer.parseInt(args[0]);
        EnergyType energyType = EnergyType.valueOf(args[1]);
        int maxDistributors = Integer.parseInt(args[2]);
        double priceKW = Double.parseDouble(args[3]);
        int energyPerDistributor = Integer.parseInt(args[4]);

        return new Producer(id, energyType, maxDistributors, priceKW, energyPerDistributor);
    }
}
