package game.database;

import fileio.input.MonthlyUpdateInputData;
import game.entity.support.Producer;

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

    public void update(List<MonthlyUpdateInputData.ProducerChangesInputData> producerChanges) {
        for (MonthlyUpdateInputData.ProducerChangesInputData producerChange : producerChanges) {
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

    public Producer findProducerByID(final int id) {
        for (Producer producer : producerList) {
            if (producer.getId() == id) {
                return producer;
            }
        }

        return null;
    }

}
