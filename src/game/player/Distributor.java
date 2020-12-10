package game.player;

public final class Distributor extends Player{
    private int noClients;
    private int price;
    private int contractLength;
    private int infrastructureCosts;
    private int productionCosts;

    public Distributor(int id,
                       int budget,
                       int noClients,
                       int price,
                       int contractLength,
                       int infrastructureCosts,
                       int productionCosts) {
        super(id, budget);
        this.noClients = 0;
        this.price = price;
        this.contractLength = contractLength;
        this.infrastructureCosts = infrastructureCosts;
        this.productionCosts = productionCosts;
    }

    public int getNoClients() {
        return noClients;
    }

    public void setNoClients(int noClients) {
        this.noClients = noClients;
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

    @Override
    public void takeTurn() {

    }

    @Override
    public void update() {

    }
}
