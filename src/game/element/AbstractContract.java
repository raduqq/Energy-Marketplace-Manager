package game.element;

public abstract class AbstractContract {
    private final int beneficiaryID;
    private int price;
    private int remContractMonths;

    public AbstractContract(final int beneficiaryID,
                            final int price,
                            final int remContractMonths) {
        this.beneficiaryID = beneficiaryID;
        this.price = price;
        this.remContractMonths = remContractMonths;
    }

    /**
     * Extension should not cause any problems
     * @return beneficiary id
     */
    public int getBeneficiaryID() {
        return beneficiaryID;
    }
    /**
     * Extension should not cause any problems
     * @return contract price
     */
    public int getPrice() {
        return price;
    }
    /**
     * Extension should not cause any problems
     * @param price to set
     */
    public void setPrice(final int price) {
        this.price = price;
    }
    /**
     * Extension should not cause any problems
     * @return remaining contract months
     */
    public int getRemContractMonths() {
        return remContractMonths;
    }
    /**
     * Extension should not cause any problems
     * @param remContractMonths to set
     */
    public void setRemContractMonths(final int remContractMonths) {
        this.remContractMonths = remContractMonths;
    }

    /**
     * Terminates contract (customer won't have a contract anymore)
     */
    public abstract void terminate();
}
