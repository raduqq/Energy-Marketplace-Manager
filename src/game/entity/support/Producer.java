package game.entity.support;

import game.element.EnergyType;
import game.entity.player.Distributor;
import game.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public final class Producer extends Support implements Observable {
    public static final class MonthlyStats {
        private int month;
        private List<Integer> distributorsIds;

        public MonthlyStats() {
            distributorsIds = new ArrayList<>();
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(final int month) {
            this.month = month;
        }

        public List<Integer> getDistributorsIds() {
            return distributorsIds;
        }

        public void setDistributorsIds(final List<Integer> distributorsIds) {
            this.distributorsIds = distributorsIds;
        }
    }

    private final List<Distributor> distributorList;
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
        super(id);
        this.distributorList = new ArrayList<>();
        this.monthlyStats = new ArrayList<>();
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
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
    /**
     * Creates & assigns producer-distributor "contract"
     * @param distributor the producer signs with
     */
    public void assignContract(final Distributor distributor) {
        // Write to the producer's distributor record
        distributor.getProducerList().add(this);
        // Write to the distributor's producer record
        distributorList.add(distributor);
    }
    /**
     * Terminates producer-distributor contract:
     * @param distributor to terminate contract with
     */
    public void terminateContract(final Distributor distributor) {
        // Removes producer from distributor's producerList
        distributor.getProducerList().remove(this);
        // Removes distributor from producer's distributorList
        distributorList.remove(distributor);
    }
    /**
     * Generates monthly stats for this producer
     * @param currMonth to generate monthly stats for
     */
    public void generateMonthlyStats(final int currMonth) {
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
    public void notify(final Object arg) {
        List<Distributor> copyDistributorList = new ArrayList<>(distributorList);
        copyDistributorList.forEach(distributor -> distributor.update(null));
    }
}
