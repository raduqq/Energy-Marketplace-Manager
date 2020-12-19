package game.database;

import game.player.Consumer;

import java.util.ArrayList;
import java.util.List;

public final class Consumers {
    private static final Consumers instance;

    private static List<Consumer> consumerList;

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

    public void addToDB(Consumer consumer) {
        Consumers.consumerList.add(consumer);
    }

    public Consumer findConsumerByID (int id) {
        for (Consumer consumer : consumerList) {
            if (consumer.getId() == id) {
                return consumer;
            }
        }

        return  null;
    }
}
