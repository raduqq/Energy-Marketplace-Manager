package fileio.output;

import java.util.ArrayList;
import java.util.List;

public final class OutputData {
    // TODO: may need to be updated?
    public static final class ConsumerOutputData {
        private int id;
        private boolean isBankrupt;
        private int budget;

        public ConsumerOutputData(final int id,
                                  final boolean isBankrupt,
                                  final int budget) {
            this.id = id;
            this.isBankrupt = isBankrupt;
            this.budget = budget;
        }

        public int getId() {
            return id;
        }

        public void setId(final int id) {
            this.id = id;
        }

        public boolean getIsBankrupt() {
            return isBankrupt;
        }

        public void setIsBankrupt(final boolean bankrupt) {
            isBankrupt = bankrupt;
        }

        public int getBudget() {
            return budget;
        }

        public void setBudget(final int budget) {
            this.budget = budget;
        }
    }

    public static final class DistributorOutputData {
        public static final class ContractOutputData {
            private int consumerId;
            private int price;
            private int remainedContractMonths;

            public ContractOutputData(final int consumerId,
                                      final int price,
                                      final int remainedContractMonths) {
                this.consumerId = consumerId;
                this.price = price;
                this.remainedContractMonths = remainedContractMonths;
            }

            public int getConsumerId() {
                return consumerId;
            }

            public void setConsumerId(final int consumerId) {
                this.consumerId = consumerId;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(final int price) {
                this.price = price;
            }

            public int getRemainedContractMonths() {
                return remainedContractMonths;
            }

            public void setRemainedContractMonths(final int remainedContractMonths) {
                this.remainedContractMonths = remainedContractMonths;
            }
        }

        private int id;
        private int budget;
        private boolean isBankrupt;
        private List<ContractOutputData> contracts;

        public DistributorOutputData(final int id,
                                     final int budget,
                                     final boolean isBankrupt,
                                     final List<ContractOutputData> contracts) {
            this.id = id;
            this.budget = budget;
            this.isBankrupt = isBankrupt;
            this.contracts = contracts;
        }

        public int getBudget() {
            return budget;
        }

        public void setBudget(final int budget) {
            this.budget = budget;
        }

        public boolean getIsBankrupt() {
            return isBankrupt;
        }

        public void setIsBankrupt(final boolean bankrupt) {
            isBankrupt = bankrupt;
        }

        public List<ContractOutputData> getContracts() {
            return contracts;
        }

        public void setContracts(final List<ContractOutputData> contracts) {
            this.contracts = contracts;
        }

        public int getId() {
            return id;
        }

        public void setId(final int id) {
            this.id = id;
        }
    }

    private List<ConsumerOutputData> consumers;
    private List<DistributorOutputData> distributors;

    public OutputData() {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
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
}
