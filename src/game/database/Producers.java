package game.database;

import fileio.input.MonthlyUpdateInputData;
import game.entity.support.Producer;

import java.util.ArrayList;
import java.util.List;

public final class Producers {
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

    public List<Producer> getProducerList() {
        return producerList;
    }
    /**
     * Finds producer in database by his id
     * @param id to find producer by
     * @return producer with given id
     */
    public Producer findProducerByID(final int id) {
        for (Producer producer : producerList) {
            if (producer.getId() == id) {
                return producer;
            }
        }

        return null;
    }
    /**
     * Adds producer to DB
     * @param producer to add to DB
     */
    public void addToDB(final Producer producer) {
        producerList.add(producer);
    }
    /**
     * Updates producers with current monthlyChanges
     * @param prodChanges - producer changes of current month
     */
    public void update(final List<MonthlyUpdateInputData.ProducerChangesInputData> prodChanges) {
        for (MonthlyUpdateInputData.ProducerChangesInputData producerChange : prodChanges) {
            // Finding producer to apply update to
            Producer currProducer = Producers
                                    .getInstance().findProducerByID(producerChange.getId());

            assert currProducer != null;

            // Applying update
            currProducer.setEnergyPerDistributor(producerChange.getEnergyPerDistributor());

            // Notifying observers
            currProducer.notify(null);
        }
    }
    /**
     * Resets the producer database
     */
    public void reset() {
        instance = new Producers();
    }
}
