package game.player;

import game.database.Distributors;
import game.element.Contract;

import java.util.Comparator;
import java.util.stream.Collectors;

public final class Consumer extends Player {
    private int monthlyIncome;
    private Contract currContract;
    private Distributor currDistributor;

    public Consumer(int id, int budget, int monthlyIncome) {
        super(id, budget);
        this.monthlyIncome = monthlyIncome;
        this.currContract = null;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Contract getCurrContract() {
        return currContract;
    }

    public void setCurrContract(Contract currContract) {
        this.currContract = currContract;
    }

    public Distributor getCurrDistributor() { return currDistributor; }

    public void setCurrDistributor(Distributor currDistributor) {
        this.currDistributor = currDistributor;
    }

    public void pickDistributor() {
        // sort distributors by price
        Comparator<Distributor> distributorComp = Comparator.comparing(Distributor::getPrice);

        // pick distributor with lowest price
        currDistributor = Distributors.getDistributorList().stream()
                        .sorted(distributorComp)
                        .collect(Collectors.toList()).get(0);

        // assign contract to consumer
        currDistributor.assignContract(this);
    }

    public void payOverdue() {
        int overdue = currContract.getOverdue();

        if (overdue > budget) {
            goBankrupt();
            return;
        }

        currDistributor.collectPayment(overdue);
        currContract.clearOverdue();
    }

    public void payCurrentBill() {
        int bill = currContract.getPrice();

        // Not able to pay: add to overdue
        if (bill > budget) {
            int overdue = Math.toIntExact(Math.round(Math.floor(1.2 * bill)));
            currContract.setOverdue(overdue);
            return;
        }

        // Able to pay
        currDistributor.collectPayment(bill);
        budget -= bill;
    }

    @Override
    public void goBankrupt() {
        isBankrupt = true;
        currDistributor.terminateContract(currContract);
    }

    @Override
    public void takeTurn() {

    }

    @Override
    public void update() { budget += monthlyIncome; }
}
