package game.strategy;

import game.entity.support.Producer;

import java.util.List;

public interface EnergyChoiceStrategy {
    /**
     * Returns a ranking of producers according to given energy choice strategy
     * @return list of best-suited producers
     */
    List<Producer> chooseEnergyStrategy();
}
