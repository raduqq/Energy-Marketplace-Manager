package game.strategy;

import game.database.Producers;
import game.entity.support.Producer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class PriceChoiceStrategy implements EnergyChoiceStrategy {
    @Override
    public List<Producer> chooseEnergyStrategy() {
        // Sort by price -> asc
        Comparator<Producer> priceProdCmp = Comparator.comparing(Producer::getPriceKW)
                // By quantity -> desc
                .thenComparing(Producer::getEnergyPerDistributor, Comparator.reverseOrder())
                // By ID -> asc
                .thenComparing(Producer::getId);

        return Producers.getInstance().getProducerList().stream()
                .filter(producer ->
                        producer.getDistributorList().size() < producer.getMaxDistributors())
                .sorted(priceProdCmp)
                .collect(Collectors.toList());
    }
}
