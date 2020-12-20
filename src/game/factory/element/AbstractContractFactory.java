package game.factory.element;

import game.element.AbstractContract;

public interface AbstractContractFactory {
    AbstractContract create(String[] args);
}
