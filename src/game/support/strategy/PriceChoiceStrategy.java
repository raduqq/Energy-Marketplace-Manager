package game.support.strategy;

import game.database.Producers;
import game.support.Producer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceChoiceStrategy implements EnergyChoiceStrategy{
    @Override
    public Producer chooseEnergyStrategy() {
        // Sort by price -> asc
        Comparator<Producer> priceProdCmp = Comparator.comparing(Producer::getPriceKW)
                // By quantity -> desc
                .thenComparing(Producer::getEnergyPerDistributor, Comparator.reverseOrder())
                // By ID -> asc
                .thenComparing(Producer::getId);

        return Producers.getInstance().getProducerList().stream()
                .sorted(priceProdCmp)
                .collect(Collectors.toList())
                .get(0);
    }
}
