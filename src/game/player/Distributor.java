package game.player;

import game.element.Contract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class Distributor extends Player{
    private List<Contract> contractList;
    private int noClients;
    private int price;
    private int contractLength;
    private int infrastructureCosts;
    private int productionCosts;
    private int profit;

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
        profit = Math.toIntExact(Math.round(Math.floor(0.2 * productionCosts)));

        // Compute new price
        if (contractList.isEmpty()) {
            price = infrastructureCosts + productionCosts + profit;
        } else {
            price = Math.toIntExact(Math.round(Math.floor(
                    infrastructureCosts / noClients)
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
        //TODO: replace with contract factory, later on
        return new Contract(id, consumer.getId(), price, contractLength);
    }

    public void assignContract(Consumer consumer) {
        // generate contract for him
        Contract currContract = generateContract(consumer);
        // give it to him
        consumer.setCurrContract(currContract);
        // write to the distributors books
        contractList.add(currContract);
        noClients++;
    }

    public void terminateContract(Contract contract) {
        // terminate contract
        contract.terminate();
        // write off this guy's books
        contractList.remove(contract);

        // NOT modifying noClients here.
        // noClients at start of round needed for payCosts.
    }

    public void collectPayment(int payment) {
        budget += payment;
    }

    public void payCosts() {
        int costs = infrastructureCosts + productionCosts * noClients;

        if (costs > budget) {
            goBankrupt();
            return;
        }

        budget -= costs;
    }

    @Override
    public void goBankrupt() {
        isBankrupt = true;

        for(Contract contract : contractList) {
            terminateContract(contract);
        }
    }

    @Override
    public void takeTurn() {
        if (!isBankrupt) {
            payCosts();
        }
    }

    @Override
    public void update() {
        if (!isBankrupt) {
            noClients = contractList.size();
            computePrice();
        }
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "id=" + id +
                ", budget=" + budget +
                ", isBankrupt=" + isBankrupt +
                ", infrastructureCosts=" + infrastructureCosts +
                ", productionCosts=" + productionCosts +
                '}';
    }
}