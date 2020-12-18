package game.element;

public abstract class AbstractContract {
    protected int beneficiaryID;
    protected int issuerID;
    protected int price;
    protected int remContractMonths;

    public AbstractContract(int issuerID, int beneficiaryID, int price, int remContractMonths) {
        this.issuerID = issuerID;
        this.beneficiaryID = beneficiaryID;
        this.price = price;
        this.remContractMonths = remContractMonths;
    }

    public int getBeneficiaryID() {
        return beneficiaryID;
    }

    public void setBeneficiaryID(int beneficiaryID) {
        this.beneficiaryID = beneficiaryID;
    }

    public int getIssuerID() {
        return issuerID;
    }

    public void setIssuerID(int issuerID) {
        this.issuerID = issuerID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRemContractMonths() {
        return remContractMonths;
    }

    public void setRemContractMonths(int remContractMonths) {
        this.remContractMonths = remContractMonths;
    }

    public abstract void terminate();
}
