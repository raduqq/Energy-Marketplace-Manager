package game.entity.support;

import game.element.EnergyType;
import game.entity.player.Distributor;
import game.strategy.Support;
import game.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Support implements Observable {
    private List<Distributor> distributorList;
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    public Producer(final int id,
                    final EnergyType energyType,
                    final int maxDistributors,
                    final double priceKW,
                    final int energyPerDistributor) {
        this.distributorList = new ArrayList<>();
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    public void setDistributorList(List<Distributor> distributorList) {
        this.distributorList = distributorList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public void assignContract(final Distributor distributor) {
        // Write to the producer's contract record
        distributor.getProducerList().add(this);
        distributorList.add(distributor);
    }

    // TODO: replace with ProdDistribContract.terminate()
    public void terminateContract(Distributor distributor) {
        distributor.getProducerList().remove(this);
        distributorList.remove(distributor);
    }



    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", energyPerDistributor=" + energyPerDistributor +
                "}";
    }

    @Override
    public void notify(Object arg) {
        distributorList.forEach(distributor -> distributor.update(null));
    }
}
