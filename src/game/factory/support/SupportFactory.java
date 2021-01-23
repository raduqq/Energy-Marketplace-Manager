package game.factory.support;

import game.entity.support.Support;

public interface SupportFactory {
    /**
     * Creates support entity
     * @param args needed to construct support entity
     * @return instantiated support entity
     */
    Support create(String[] args);
}
