package fileio.output;

import java.util.ArrayList;
import java.util.List;

public class OutputData {
    public static class ConsumerOutputData {
        int id;
        boolean isBankrupt;
        int budget;

        public ConsumerOutputData(int id, boolean isBankrupt, int budget) {
            this.id = id;
            this.isBankrupt = isBankrupt;
            this.budget = budget;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean getIsBankrupt() {
            return isBankrupt;
        }

        public void setIsBankrupt(boolean bankrupt) {
            isBankrupt = bankrupt;
        }

        public int getBudget() {
            return budget;
        }

        public void setBudget(int budget) {
            this.budget = budget;
        }
    }

    public static class DistributorOutputData {
        public static class ContractOutputData {
            int consumerId;
            int price;
            int remainedContractMonths;

            public ContractOutputData(int consumerId, int price, int remainedContractMonths) {
                this.consumerId = consumerId;
                this.price = price;
                this.remainedContractMonths = remainedContractMonths;
            }

            public int getConsumerId() {
                return consumerId;
            }

            public void setConsumerId(int consumerId) {
                this.consumerId = consumerId;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getRemainedContractMonths() {
                return remainedContractMonths;
            }

            public void setRemainedContractMonths(int remainedContractMonths) {
                this.remainedContractMonths = remainedContractMonths;
            }
        }

        int id;
        int budget;
        boolean isBankrupt;
        List<ContractOutputData> contracts;

        public DistributorOutputData(int id, int budget, boolean isBankrupt, List<ContractOutputData> contracts) {
            this.id = id;
            this.budget = budget;
            this.isBankrupt = isBankrupt;
            this.contracts = contracts;
        }

        public int getBudget() {
            return budget;
        }

        public void setBudget(int budget) {
            this.budget = budget;
        }

        public boolean getIsBankrupt() {
            return isBankrupt;
        }

        public void setIsBankrupt(boolean bankrupt) {
            isBankrupt = bankrupt;
        }

        public List<ContractOutputData> getContracts() {
            return contracts;
        }

        public void setContracts(List<ContractOutputData> contracts) {
            this.contracts = contracts;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

    public void setConsumers(List<ConsumerOutputData> consumers) {
        this.consumers = consumers;
    }

    public List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public void setDistributors(List<DistributorOutputData> distributors) {
        this.distributors = distributors;
    }
}
