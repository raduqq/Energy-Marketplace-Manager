package fileio.output;

import game.strategy.EnergyChoiceStrategyType;

import java.util.List;

public final class DistributorOutputData {
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
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private List<ContractOutputData> contracts;

    public DistributorOutputData(final int id,
                                 final int energyNeededKW,
                                 final int contractCost,
                                 final int budget,
                                 final EnergyChoiceStrategyType producerStrategy,
                                 final boolean isBankrupt,
                                 final List<ContractOutputData> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(final int contractCost) {
        this.contractCost = contractCost;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(final EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
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
}
