package game.factory.support;

import common.Constants;
import game.element.EnergyType;
import game.entity.support.Producer;
import game.entity.support.Support;

public final class ProducerFactory implements SupportFactory {
    @Override
    public Support create(String[] args) {
        int id = Integer.parseInt(args[Constants.ZEROTH_ARG]);
        EnergyType energyType = EnergyType.valueOf(args[Constants.FIRST_ARG]);
        int maxDistributors = Integer.parseInt(args[Constants.SECOND_ARG]);
        double priceKW = Double.parseDouble(args[Constants.THIRD_ARG]);
        int energyPerDistributor = Integer.parseInt(args[Constants.FOURTH_ARG]);

        return new Producer(id, energyType, maxDistributors, priceKW, energyPerDistributor);
    }
}
