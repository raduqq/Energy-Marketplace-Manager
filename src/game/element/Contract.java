package game.element;

import game.database.Consumers;
import game.player.Consumer;

public final class Contract extends AbstractContract{
    private int overdue;

    public Contract(int issuerID, int beneficiaryID, int price, int remContractMonths) {
        super(issuerID, beneficiaryID, price, remContractMonths);
        overdue = 0;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }

    public void clearOverdue() { overdue = 0; }

    @Override
    public void terminate() {
        Consumer currConsumer = Consumers.getInstance().findConsumerByID(beneficiaryID);
        if (currConsumer != null) {
            currConsumer.setCurrContract(null);
        }
    }

    public void update() {
        remContractMonths--;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "overdue=" + overdue +
                ", issuerID= " + issuerID +
                ", price=" + price +
                ", remContractMonths=" + remContractMonths +
                '}';
    }
}
