package game.element;

import game.database.Consumers;
import game.player.Consumer;

public final class Contract extends AbstractContract {
    private int overdue;

    public Contract(final int beneficiaryID,
                    final int price,
                    final int remContractMonths) {
        super(beneficiaryID, price, remContractMonths);
        overdue = 0;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(final int overdue) {
        this.overdue = overdue;
    }

    /**
     * Clears this contract's overdue
     */
    public void clearOverdue() {
        overdue = 0;
    }

    @Override
    public void terminate() {
        Consumer currConsumer = Consumers.getInstance().findConsumerByID(getBeneficiaryID());
        if (currConsumer != null) {
            currConsumer.setCurrContract(null);
        }
    }

    /**
     * Updates contract state each round
     */
    public void update() {
        setRemContractMonths(getRemContractMonths() - 1);
    }
}
