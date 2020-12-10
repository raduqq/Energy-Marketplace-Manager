package game.element;

public final class Contract extends AbstractContract{
    private int overdue;

    public Contract(int issuerID, int beneficiaryID, int price, int remContractMonths) {
        this.issuerID = issuerID;
        this.beneficiaryID = beneficiaryID;
        this.price = price;
        this.remContractMonths = remContractMonths;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }

//    public void update() {
//        remContractMonths--;
//    }
}
