package fileio.input;

import java.util.List;

public final class InitialData {
    private List<ConsumerInputData> consumers;
    private List<DistributorInputData> distributors;

    public List<ConsumerInputData> getConsumers() {
        return consumers;
    }

    public void setConsumers(final List<ConsumerInputData> consumers) {
        this.consumers = consumers;
    }

    public List<DistributorInputData> getDistributors() {
        return distributors;
    }

    public void setDistributors(final List<DistributorInputData> distributors) {
        this.distributors = distributors;
    }
}
