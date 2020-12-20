package game.factory.element;

import game.element.AbstractContract;

public interface AbstractContractFactory {
    /**
     * Creates contract
     * @param args needed to construct contract
     * @return instantiated contract
     */
    AbstractContract create(String[] args);
}
