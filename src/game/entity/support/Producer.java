package game.entity.support;

import game.element.EnergyType;
import game.entity.player.Distributor;
import game.strategy.Support;
import game.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Support implements Observable {
    public static final class MonthlyStats {
        private int month;
        private List<Integer> distributorsIds;

        public MonthlyStats() {
            distributorsIds = new ArrayList<>();
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public List<Integer> getDistributorsIds() {
            return distributorsIds;
        }

        public void setDistributorsIds(List<Integer> distributorsIds) {
            this.distributorsIds = distributorsIds;
        }

        @Override
        public String toString() {
            return "{" +
                    "mo=" + month +
                    ", distribIds=" + distributorsIds +
                    '}';
        }
    }

    private List<Distributor> distributorList;
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private List<MonthlyStats> monthlyStats;

    public Producer(final int id,
                    final EnergyType energyType,
                    final int maxDistributors,
                    final double priceKW,
                    final int energyPerDistributor) {
        this.distributorList = new ArrayList<>();
        this.monthlyStats = new ArrayList<>();
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
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


    public void generateMonthlyStats(int currMonth) {
        MonthlyStats currMonthlyStats = new MonthlyStats();

        // Set month
        currMonthlyStats.setMonth(currMonth);
        // Set distributorIds
        for (Distributor distributor : distributorList) {
            currMonthlyStats.getDistributorsIds().add(distributor.getId());
        }

        // Add current month stats to overall monthly stats
        monthlyStats.add(currMonthlyStats);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", enPerDistrib=" + energyPerDistributor +
                ", MoStats=" + monthlyStats +
                '}';
    }

    @Override
    public void notify(Object arg) {
        distributorList.forEach(distributor -> distributor.update(null));
    }
}
