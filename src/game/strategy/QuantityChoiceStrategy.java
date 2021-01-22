package game.strategy;

import game.database.Producers;
import game.entity.support.Producer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityChoiceStrategy implements EnergyChoiceStrategy {
    @Override
    public List<Producer> chooseEnergyStrategy() {
        // Sort by quantity -> desc
        Comparator<Producer> qtyProdCmp = Comparator.comparing(Producer::getEnergyPerDistributor,
                Comparator.reverseOrder())
                // By price -> asc
                //TODO: does this count?
//                .thenComparing(Producer::getPriceKW)
                // By ID -> asc
                .thenComparing(Producer::getId);

        return Producers.getInstance().getProducerList().stream()
                .filter(producer -> producer.getDistributorList().size() < producer.getMaxDistributors())
                .sorted(qtyProdCmp)
                .collect(Collectors.toList());
    }
}
