package game.database;

import common.Constants;
import fileio.input.ConsumerInputData;
import game.factory.player.ConsumerFactory;
import game.factory.player.PlayerFactory;
import game.player.Consumer;

import java.util.ArrayList;
import java.util.List;

public final class Consumers {
    private static Consumers instance;

    private final List<Consumer> consumerList;

    private Consumers() {
        consumerList = new ArrayList<>();
    }

    static {
        instance = new Consumers();
    }

    public static Consumers getInstance() {
        return instance;
    }

    public List<Consumer> getConsumerList() {
        return consumerList;
    }

    /**
     * Adds consumer to the database
     * @param consumer to be added
     */
    public void addToDB(final Consumer consumer) {
        consumerList.add(consumer);
    }

    /**
     * Finds consumer by its id
     * @param id to find consumer by
     * @return consumer with given id
     */
    public Consumer findConsumerByID(final int id) {
        for (Consumer consumer : consumerList) {
            if (consumer.getId() == id) {
                return consumer;
            }
        }

        return  null;
    }

    /**
     * Applying monthly updates to the consumer database
     * = adding new consumers to the database
     * @param newConsumers to be added in the database
     */
    public void update(final List<ConsumerInputData> newConsumers) {
        PlayerFactory consumerFactory = new ConsumerFactory();

        for (ConsumerInputData consumerInputData : newConsumers) {
            String[] args = new String[Constants.NO_CONSUMER_PARAM];
            args[Constants.ZEROTH_ARG] = String.valueOf(consumerInputData.getId());
            args[Constants.FIRST_ARG] = String.valueOf(consumerInputData.getInitialBudget());
            args[Constants.SECOND_ARG] = String.valueOf(consumerInputData.getMonthlyIncome());

            addToDB((Consumer) consumerFactory.create(args));
        }
    }

    /**
     * Resets the consumer database
     */
    public void reset() {
        instance = new Consumers();
    }
}
