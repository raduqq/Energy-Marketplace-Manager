package game.entity.player;

import common.Constants;
import game.database.Distributors;
import game.element.Contract;

import java.util.Comparator;
import java.util.stream.Collectors;

public final class Consumer extends Player {
    private int monthlyIncome;
    private Contract currContract;
    private Distributor currDistributor;

    public Consumer(final int id,
                    final int budget,
                    final int monthlyIncome) {
        super(id, budget);
        this.monthlyIncome = monthlyIncome;
        this.currContract = null;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setCurrContract(final Contract currContract) {
        this.currContract = currContract;
    }

    /**
     * Picks distributor at the beginning of the road
     * Signs contract with picked distributor
     */
    public void pickDistributor() {
        // Already has a contract => nothing to do
        if (currContract != null) {
            return;
        }

        // Sort distributors by price
        Comparator<Distributor> distributorComp = Comparator.comparing(Distributor::getPrice);

        // Pick distributor with lowest price
        currDistributor = Distributors.getInstance().getDistributorList().stream()
                        // Filters out bankrupt distributors
                        .filter(distributor -> !distributor.getIsBankrupt())
                        .sorted(distributorComp)
                        .collect(Collectors.toList()).get(0);

        // Assign contract to consumer
        currDistributor.assignContract(this);
    }

    /**
     * Edge case: contract has run down, therefore it doesn't generate new bills
     * => only need to pay overdue bill (if existent)
     */
    public void resolvePastArrangement() {
        // TODO: vezi enuntul, poate trebuie updatat
        if (currContract == null) {
            return;
        }

        if (currContract.getRemContractMonths() == 0 && currContract.getOverdue() != 0) {
            payCurrentBill();

            if (!getIsBankrupt()) {
                currDistributor.terminateContract(currContract);
            }
        }
    }

    /**
     * Pays current bill (along with overdue)
     */
    public void payCurrentBill() {
        // Normal bill
        int bill = currContract.getPrice();

        // Add overdue if existent
        int overdue = currContract.getOverdue();
        if (overdue > 0) {
            bill += overdue;
        }

        // Not able to pay: add to overdue
        if (bill > getBudget()) {
            /*
             Already has an overdue and cannot pay
             for the second month in a row => bankrupt
             */
            if (overdue > 0) {
                goBankrupt();
                return;
            }

            // Has no previous overdue && cannot pay current bill => set overdue
            currContract.setOverdue(Math
                                    .toIntExact(
                                            Math.round(
                                                    Math.floor(Constants.OVERDUE_FACTOR * bill))));
            return;
        }

        // Able to pay
        currDistributor.collectPayment(bill);
        setBudget(getBudget() - bill);

        // Had overdue, but paid it
        if (overdue > 0) {
            currContract.clearOverdue();
        }
    }

    @Override
    public void goBankrupt() {
        setIsBankrupt(true);
        currDistributor.terminateContract(currContract);
    }

    @Override
    public void takeTurn() {
        if (!getIsBankrupt()) {
            resolvePastArrangement();
        }

        if (!getIsBankrupt()) {
            pickDistributor();
            payCurrentBill();
        }
    }

    @Override
    public void roundUpdate() {
        if (!getIsBankrupt()) {
            setBudget(getBudget() + monthlyIncome);
        }
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + getId() +
                ", isBankrupt=" + getIsBankrupt() +
                ", budget=" + getBudget() +
                "}\n";
    }
}
