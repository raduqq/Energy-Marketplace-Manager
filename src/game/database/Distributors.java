package game.database;

import fileio.input.MonthlyUpdateInputData;
import game.player.Distributor;

import java.util.ArrayList;
import java.util.List;

public final class Distributors {
    private static Distributors instance;

    private final List<Distributor> distributorList;

    private Distributors() { distributorList = new ArrayList<>(); }

    static { instance = new Distributors(); }

    public static Distributors getInstance() {
        return instance;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    public void addToDB(Distributor distributor) {
        distributorList.add(distributor);
    }

    public Distributor findDistributorByID (int id) {
        for (Distributor distributor : distributorList) {
            if (distributor.getId() == id) {
                return distributor;
            }
        }

        return  null;
    }

    public void update(List<MonthlyUpdateInputData.CostChangesInputData> costChanges) {
        for (MonthlyUpdateInputData.CostChangesInputData costChange : costChanges) {
            Distributor currDistributor = Distributors.getInstance().findDistributorByID(costChange.getId());
            assert currDistributor != null;

            currDistributor.setInfrastructureCosts(costChange.getInfrastructureCost());
            currDistributor.setProductionCosts(costChange.getProductionCost());
        }
    }

    public void reset() {
        instance = new Distributors();
    }
}
