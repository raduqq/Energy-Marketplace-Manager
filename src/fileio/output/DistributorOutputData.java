package fileio.output;

import game.element.EnergyType;
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

    public DistributorOutputData(int id,
                                 int energyNeededKW,
                                 int contractCost,
                                 int budget,
                                 EnergyChoiceStrategyType producerStrategy,
                                 boolean isBankrupt,
                                 List<ContractOutputData> contracts) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
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

    public void setContracts(List<ContractOutputData> contracts) {
        this.contracts = contracts;
    }
}
