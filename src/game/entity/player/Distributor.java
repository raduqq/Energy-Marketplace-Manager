package game.entity.player;

import common.Constants;
import game.element.ConsumerContract;
import game.factory.element.AbstractContractFactory;
import game.factory.element.ContractFactory;
import game.entity.support.Producer;
import game.observer.Observer;
import game.strategy.EnergyChoiceStrategy;
import game.strategy.EnergyChoiceStrategyType;
import game.strategy.GreenChoiceStrategy;
import game.strategy.PriceChoiceStrategy;
import game.strategy.QuantityChoiceStrategy;

import java.util.ArrayList;
import java.util.List;

public final class Distributor extends Player implements Observer {
    private final List<ConsumerContract> consumerContractList;
    private final List<Producer> producerList;

    private int noClients;
    private int price;
    private int contractLength;
    private int infrastructureCosts;

    private int energyNeededKW;
    private final EnergyChoiceStrategyType producerStrategyType;
    private EnergyChoiceStrategy producerStrategy;
    private int productionCosts;
    private boolean needsProducers;

    public Distributor(final int id,
                       final int budget,
                       final int contractLength,
                       final int infrastructureCosts,
                       final int energyNeededKW,
                       final EnergyChoiceStrategyType producerStrategy) {
        super(id, budget);
        this.consumerContractList = new ArrayList<>();
        this.producerList = new ArrayList<>();
        this.noClients = 0;
        this.contractLength = contractLength;
        this.infrastructureCosts = infrastructureCosts;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategyType = producerStrategy;
        determineProducerStrategy(producerStrategy);
        needsProducers = true;
    }
    /**
     * Converts EnergyChoiceStraegyType ("PRICE") to EnergyChoiceStrategy(PriceStrategy)
     * @param energyChoiceStrategyType to convert to energyChoiceStrategy
     */
    public void determineProducerStrategy(final EnergyChoiceStrategyType energyChoiceStrategyType) {
        switch (energyChoiceStrategyType) {
            case GREEN:
                producerStrategy = new GreenChoiceStrategy();
                break;
            case PRICE:
                producerStrategy = new PriceChoiceStrategy();
                break;
            case QUANTITY:
                producerStrategy = new QuantityChoiceStrategy();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + energyChoiceStrategyType);
        }
    }
    /**
     * Chooses & signs with this month's producers
     */
    public void chooseProducers() {
        int totalEnergy = 0;

        // Get best-suited producers
        List<Producer> potentialProducers = producerStrategy.chooseEnergyStrategy();

        while (totalEnergy < energyNeededKW) {
            // Get current potential producer
            Producer currProducer = potentialProducers.get(0);

            // Cannot find producer => bankruptcy
            if (currProducer == null) {
                goBankrupt();
                return;
            }

            // Mark choice as made
            currProducer.assignContract(this);
            // Mark energy as secured
            totalEnergy += currProducer.getEnergyPerDistributor();
            // Mark producer as already contracted with
            potentialProducers.remove(0);
        }
    }
    /**
     * Computes this round's production costs
     */
    public void computeProductionCosts() {
        int totalProductionCosts = 0;

        for (Producer producer : producerList) {
            totalProductionCosts += producer.getEnergyPerDistributor() * producer.getPriceKW();
        }

        productionCosts = Math
                        .toIntExact(
                                Math.round(
                                        Math.floor((double) totalProductionCosts / Constants.PRODUCTION_COST_FACTOR)));
    }
    /**
     * Computes this round's contract prices
     */
    public void computePrice() {
        computeProductionCosts();

        // Compute profit
        int profit = Math
                    .toIntExact(
                            Math.round(
                                    Math.floor(Constants.PROFIT_FACTOR * productionCosts)));

        // Compute new price
        if (consumerContractList.isEmpty()) {
            price = infrastructureCosts + productionCosts + profit;
        } else {
            price = Math.toIntExact(Math.round(Math.floor(
                    (double) infrastructureCosts / consumerContractList.size())
                    + productionCosts
                    + profit));
        }
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public EnergyChoiceStrategyType getProducerStrategyType() {
        return producerStrategyType;
    }

    public EnergyChoiceStrategy getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(final EnergyChoiceStrategy producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public void setInfrastructureCosts(final int infrastructureCosts) {
        this.infrastructureCosts = infrastructureCosts;
    }

    public List<ConsumerContract> getContractList() {
        return consumerContractList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    /**
     * Generating contract to be assigned to customer
     * @param consumer to generate contract for
     * @return contract ready to be assigned
     */
    public ConsumerContract generateContract(final Consumer consumer) {
        AbstractContractFactory contractFactory = new ContractFactory();

        String[] args = new String[Constants.NO_CONTRACT_PARAM];
        args[Constants.ZEROTH_ARG] = String.valueOf(consumer.getId());
        args[Constants.FIRST_ARG] = String.valueOf(price);
        args[Constants.SECOND_ARG] = String.valueOf(contractLength);

        return (ConsumerContract) contractFactory.create(args);
    }

    /**
     * Assigning contract to customer
     * - Generating contract
     * - Giving contract to customer
     * - Updating distributor's contract record (contractList + noClients)
     * @param consumer to receive new contract
     */
    public void assignContract(final Consumer consumer) {
        // Generate contract
        ConsumerContract currConsumerContract = generateContract(consumer);
        // Give contract to customer
        consumer.setCurrContract(currConsumerContract);
        // Write to the distributor's contract record
        consumerContractList.add(currConsumerContract);
        noClients++;
    }

    /**
     * Terminates contract:
     * - Sets customer's currContract to null
     * - Removes contract from distributor's contract list
     * @param consumerContract to be terminated
     */
    public void terminateContract(final ConsumerContract consumerContract) {
        consumerContract.terminate();
        // Writing off the distributor's record
        consumerContractList.remove(consumerContract);

        /*
         NOT modifying noClients here.
         noClients at start of round needed for payCosts.
         */
    }

    /**
     * Collects payment from customer (when he is able to pay)
     * @param payment to be collected
     */
    public void collectPayment(final int payment) {
        setBudget(getBudget() + payment);
    }

    /**
     * Pays costs at the end of round
     */
    public void payCosts() {
        int costs = infrastructureCosts + productionCosts * noClients;

        setBudget(getBudget() - costs);

        if (getBudget() < 0) {
            goBankrupt();
        }
    }

    @Override
    public void goBankrupt() {
        setIsBankrupt(true);
        consumerContractList.forEach(ConsumerContract::terminate);

        producerList.forEach(producer -> producer.terminateContract(this));
    }

    @Override
    public void takeTurn() {
        if (!getIsBankrupt()) {
            payCosts();
        }
    }

    /**
     * Updates contract list (removing expired contracts)
     * At the beginning of the round, after computing this round's prices
     */
    public void updateContractList() {
        List<ConsumerContract> expiredConsumerContracts = new ArrayList<>();

        // Fetching expired contracts
        for (ConsumerContract consContract : getContractList()) {
            if (consContract.getRemContractMonths() == 0 && consContract.getOverdue() == 0) {
                expiredConsumerContracts.add(consContract);
            }
        }

        // Removing expired contracts
        for (ConsumerContract expiredConsumerContract : expiredConsumerContracts) {
            terminateContract(expiredConsumerContract);
        }
    }

    @Override
    public void roundUpdate() {
        if (!getIsBankrupt()) {
            if (needsProducers) {
                chooseProducers();
                needsProducers = false;
            }

            computePrice();
            updateContractList();

            // no clients: excluding those whose contracts expired this month
            noClients = consumerContractList.size();
        }
    }

    @Override
    public void update(final Object arg) {
        List<Producer> copyProducerList = new ArrayList<>(producerList);

        copyProducerList.forEach(producer -> producer.terminateContract(this));
        needsProducers = true;
    }
}
