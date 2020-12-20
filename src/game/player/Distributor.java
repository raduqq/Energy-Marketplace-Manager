package game.player;

import game.element.Contract;
import game.factory.element.AbstractContractFactory;
import game.factory.element.ContractFactory;

import java.util.ArrayList;
import java.util.List;

public final class Distributor extends Player{
    private List<Contract> contractList;
    private int noClients;
    private int price;
    private int contractLength;
    private int infrastructureCosts;
    private int productionCosts;

    public Distributor(int id,
                       int budget,
                       int contractLength,
                       int infrastructureCosts,
                       int productionCosts) {
        super(id, budget);
        this.contractList = new ArrayList<>();
        this.noClients = 0;
        this.contractLength = contractLength;
        this.infrastructureCosts = infrastructureCosts;
        this.productionCosts = productionCosts;
    }

    public void computePrice() {
        // Compute profit
        int profit = Math.toIntExact(Math.round(Math.floor(0.2 * productionCosts)));

        // Compute new price
        if (contractList.isEmpty()) {
            price = infrastructureCosts + productionCosts + profit;
        } else {
            price = Math.toIntExact(Math.round(Math.floor(
                    (double) infrastructureCosts / contractList.size())
                    + productionCosts
                    + profit));
        }
    }

    public int getInfrastructureCosts() {
        return infrastructureCosts;
    }

    public void setInfrastructureCosts(int infrastructureCosts) {
        this.infrastructureCosts = infrastructureCosts;
    }

    public int getProductionCosts() {
        return productionCosts;
    }

    public void setProductionCosts(int productionCosts) {
        this.productionCosts = productionCosts;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }

    public Contract generateContract(Consumer consumer) {
        AbstractContractFactory contractFactory = new ContractFactory();

        String[] args = new String[4];
        args[0] = String.valueOf(getId());
        args[1] = String.valueOf(consumer.getId());
        args[2] = String.valueOf(price);
        args[3] = String.valueOf(contractLength);

        return (Contract) contractFactory.create(args);
    }

    public void assignContract(Consumer consumer) {
        // Generate contract
        Contract currContract = generateContract(consumer);
        // Give contract to customer
        consumer.setCurrContract(currContract);
        // Write to the distributor's contract record
        contractList.add(currContract);
        noClients++;
    }

    public void terminateContract(Contract contract) {
        contract.terminate();
        // Writing off the distributor's record
        contractList.remove(contract);

        /*
         NOT modifying noClients here.
         noClients at start of round needed for payCosts.
         */
    }

    public void collectPayment(int payment) { setBudget(getBudget() + payment); }

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

        List<Contract> dissolvedContracts = contractList;
        dissolvedContracts.forEach(Contract::terminate);
    }

    @Override
    public void takeTurn() {
        if (!getIsBankrupt()) {
            payCosts();
        }
    }

    public void updateContractList() {
        List<Contract> expiredContracts = new ArrayList<>();

        // Fetching expired contracts
        for (Contract contract : getContractList()) {
            if (contract.getRemContractMonths() == 0 && contract.getOverdue() == 0) {
                expiredContracts.add(contract);
            }
        }

        // Removing expired contracts
        for (Contract expiredContract : expiredContracts) {
            terminateContract(expiredContract);
        }
    }

    @Override
    public void update() {
        if (!getIsBankrupt()) {
            computePrice();
            updateContractList();
            // no clients: excluding those whose contracts expired this month
            noClients = contractList.size();
        }
    }
}