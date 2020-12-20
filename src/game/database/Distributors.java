package game.database;

import fileio.input.MonthlyUpdateInputData;
import game.player.Distributor;

import java.util.ArrayList;
import java.util.List;

public final class Distributors {
    private static Distributors instance;

    private final List<Distributor> distributorList;

    private Distributors() {
        distributorList = new ArrayList<>();
    }

    static {
        instance = new Distributors();
    }

    public static Distributors getInstance() {
        return instance;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    /**
     * Adds distributor in the database
     * @param distributor to be added
     */
    public void addToDB(final Distributor distributor) {
        distributorList.add(distributor);
    }

    /**
     * Finds distributor in database by its id
     * @param id of distributor to be found
     * @return distributor with given id
     */
    public Distributor findDistributorByID(final int id) {
        for (Distributor distributor : distributorList) {
            if (distributor.getId() == id) {
                return distributor;
            }
        }

        return  null;
    }

    /**
     * Applies monthly updates to distributors
     * @param costChanges to be applied
     */
    public void update(final List<MonthlyUpdateInputData.CostChangesInputData> costChanges) {
        for (MonthlyUpdateInputData.CostChangesInputData costChange : costChanges) {
            Distributor currDistributor = Distributors
                                        .getInstance().findDistributorByID(costChange.getId());
            assert currDistributor != null;

            currDistributor.setInfrastructureCosts(costChange.getInfrastructureCost());
            currDistributor.setProductionCosts(costChange.getProductionCost());
        }
    }

    /**
     * Resets the distributor database
     */
    public void reset() {
        instance = new Distributors();
    }
}
