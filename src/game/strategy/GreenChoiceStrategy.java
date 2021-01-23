package game.strategy;

import game.database.Producers;
import game.entity.support.Producer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class GreenChoiceStrategy implements EnergyChoiceStrategy {
    @Override
    public List<Producer> chooseEnergyStrategy() {
        // Sort by renewability -> green first
        Comparator<Producer> greenProdCmp = Comparator.comparing((Producer prod) ->
                                                                prod.getEnergyType().isRenewable(),
                Comparator.reverseOrder())
                // By price -> asc
                .thenComparing(Producer::getPriceKW)
                // By quantity -> desc
                .thenComparing(Producer::getEnergyPerDistributor, Comparator.reverseOrder())
                // By ID -> asc
                .thenComparing(Producer::getId);

        return Producers.getInstance().getProducerList().stream()
                .filter(producer ->
                            producer.getDistributorList().size() < producer.getMaxDistributors())
                .sorted(greenProdCmp)
                .collect(Collectors.toList());
    }
}
