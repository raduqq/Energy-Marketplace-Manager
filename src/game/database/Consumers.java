package game.database;

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

    static { instance = new Consumers(); }

    public static Consumers getInstance() {
        return instance;
    }

    public List<Consumer> getConsumerList() {
        return consumerList;
    }

    public void addToDB(Consumer consumer) {
        consumerList.add(consumer);
    }

    public Consumer findConsumerByID (int id) {
        for (Consumer consumer : consumerList) {
            if (consumer.getId() == id) {
                return consumer;
            }
        }

        return  null;
    }

    public void update(List<ConsumerInputData> newConsumers) {
        PlayerFactory consumerFactory = new ConsumerFactory();

        for (ConsumerInputData consumerInputData : newConsumers) {
            String[] args = new String[3];
            args[0] = String.valueOf(consumerInputData.getId());
            args[1] = String.valueOf(consumerInputData.getInitialBudget());
            args[2] = String.valueOf(consumerInputData.getMonthlyIncome());

            addToDB((Consumer) consumerFactory.create(args));
        }
    }

    public void reset() {
        instance = new Consumers();
    }
}
