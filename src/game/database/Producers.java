package game.database;

import game.support.Producer;

import java.util.ArrayList;
import java.util.List;

public class Producers {
    private static Producers instance;

    private final List<Producer> producerList;

    private Producers() {
        producerList = new ArrayList<>();
    }

    static {
        instance = new Producers();
    }

    public static Producers getInstance() {
        return instance;
    }

    public static void setInstance(Producers instance) {
        Producers.instance = instance;
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    public void addToDB(final Producer producer) {
        producerList.add(producer);
    }

    //TODO: update

    /**
     * Resets the producer database
     */
    public void reset() { instance = new Producers(); }

    @Override
    public String toString() {
        return "Producers{" +
                "producerList=" + producerList +
                '}';
    }
}
