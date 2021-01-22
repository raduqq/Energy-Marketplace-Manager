package game.strategy;

import game.entity.support.Producer;

import java.util.List;

public interface EnergyChoiceStrategy {
    List<Producer> chooseEnergyStrategy();
}
