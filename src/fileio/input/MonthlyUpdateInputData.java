package fileio.input;

import java.util.List;

public final class MonthlyUpdateInputData {
    public static final class DistributorChangesInputData {
        private int id;
        private int infrastructureCost;

        public int getId() {
            return id;
        }

        public void setId(final int id) {
            this.id = id;
        }

        public int getInfrastructureCost() {
            return infrastructureCost;
        }

        public void setInfrastructureCost(final int infrastructureCost) {
            this.infrastructureCost = infrastructureCost;
        }
    }

    public static final class ProducerChangesInputData {
        private int id;
        private int energyPerDistributor;

        public int getId() {
            return id;
        }

        public void setId(final int id) {
            this.id = id;
        }

        public int getEnergyPerDistributor() {
            return energyPerDistributor;
        }

        public void setEnergyPerDistributor(final int energyPerDistributor) {
            this.energyPerDistributor = energyPerDistributor;
        }
    }

    private List<ConsumerInputData> newConsumers;
    private List<DistributorChangesInputData> distributorChanges;
    private List<ProducerChangesInputData> producerChanges;

    public List<ConsumerInputData> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final List<ConsumerInputData> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<DistributorChangesInputData> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(final List<DistributorChangesInputData> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public List<ProducerChangesInputData> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(final List<ProducerChangesInputData> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
