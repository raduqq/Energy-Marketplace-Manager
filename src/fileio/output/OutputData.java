package fileio.output;

import java.util.ArrayList;
import java.util.List;

public final class OutputData {
    private List<ConsumerOutputData> consumers;
    private List<DistributorOutputData> distributors;
    private List<ProducerOutputData> energyProducers;

    public OutputData() {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
        this.energyProducers = new ArrayList<>();
    }

    public List<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    public void setConsumers(final List<ConsumerOutputData> consumers) {
        this.consumers = consumers;
    }

    public List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public void setDistributors(final List<DistributorOutputData> distributors) {
        this.distributors = distributors;
    }

    public List<ProducerOutputData> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(final List<ProducerOutputData> energyProducers) {
        this.energyProducers = energyProducers;
    }
}
