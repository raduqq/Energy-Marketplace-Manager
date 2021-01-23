package fileio.output;

import game.element.EnergyType;

import java.util.List;

public final class ProducerOutputData {
    public static final class MonthlyStatsOutputData {
        private int month;
        private List<Integer> distributorsIds;

        public MonthlyStatsOutputData(final int month,
                                      final List<Integer> distributorsIds) {
            this.month = month;
            this.distributorsIds = distributorsIds;
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

    private int id;
    private int maxDistributors;
    private double priceKW;
    private EnergyType energyType;
    private int energyPerDistributor;
    private List<MonthlyStatsOutputData> monthlyStats;

    public ProducerOutputData(final int id,
                              final int maxDistributors,
                              final double priceKW,
                              final EnergyType energyType,
                              final int energyPerDistributor,
                              final List<MonthlyStatsOutputData> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthlyStatsOutputData> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final List<MonthlyStatsOutputData> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
