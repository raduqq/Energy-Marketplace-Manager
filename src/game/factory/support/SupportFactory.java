package game.factory.support;

import game.strategy.Support;

public interface SupportFactory {
    /**
     * Creates support entity
     * @param args needed to construct support entity
     * @return instantiated support entity
     */
    Support create(String[] args);
}