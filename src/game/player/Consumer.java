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
        // Already has a contract => nothing to do
        if (currContract != null) {
            return;
        }

        // sort distributors by price
        Comparator<Distributor> distributorComp = Comparator.comparing(Distributor::getPrice);

        // pick distributor with lowest price
        currDistributor = Distributors.getInstance().getDistributorList().stream()
                        .filter(distributor -> !distributor.isBankrupt)
                        .sorted(distributorComp)
                        .collect(Collectors.toList()).get(0);

        // assign contract to consumer
        currDistributor.assignContract(this);
    }

    // Edge case: contract has run down, therefore it doesn't generate new bills
    // => only need to pay the last one, if it has any overdue
    public void resolvePastArrangement() {
        if (currContract == null) {
            return;
        }

        if (currContract.getRemContractMonths() == 0 && currContract.getOverdue() != 0) {
            payCurrentBill();

            if (!isBankrupt) {
                currDistributor.terminateContract(currContract);
            }
        }
    }

    public void payCurrentBill() {
        // Normal bill
        int bill = currContract.getPrice();

        // Add overdue if existent
        int overdue = currContract.getOverdue();
        if (overdue > 0) {
            bill += overdue;
        }

        // Not able to pay: add to overdue
        if (bill > budget) {
            /*
             Already has an overdue and cannot pay
             for the second month in a row => bankrupt
             */
            if (overdue > 0) {
                goBankrupt();
                return;
            }

            // Has no previous overdue && cannot pay current bill => set overdue
            currContract.setOverdue(Math.toIntExact(Math.round(Math.floor(1.2 * bill))));
            return;
        }

        // Able to pay
        currDistributor.collectPayment(bill);
        budget -= bill;

        // Had overdue, but paid it
        if (overdue > 0) {
            currContract.clearOverdue();
        }
    }

    @Override
    public void goBankrupt() {
        isBankrupt = true;
        currDistributor.terminateContract(currContract);
    }

    @Override
    public void takeTurn() {
        if (!isBankrupt) {
            resolvePastArrangement();
        }

        if (!isBankrupt) {
            pickDistributor();
            payCurrentBill();
        }
    }

    @Override
    public void update() {
        if (!isBankrupt) {
            budget += monthlyIncome;
        }
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", isBankrupt=" + isBankrupt +
                ", budget=" + budget +
                ", currContract=" + currContract +
                '}';
    }
}
