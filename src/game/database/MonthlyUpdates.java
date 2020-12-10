package game.database;

import game.player.Consumer;

import java.util.List;

public final class MonthlyUpdates {
    public static class MonthlyCostChanges {
        private int distributorID;
        private int infrastructureCost;
        private int productionCost;
    }

    List<Consumer> newConsumers;
    List<MonthlyCostChanges> monthlyCostChangesList;

    public List<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(List<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<MonthlyCostChanges> getMonthlyCostChangesList() {
        return monthlyCostChangesList;
    }

    public void setMonthlyCostChangesList(List<MonthlyCostChanges> monthlyCostChangesList) {
        this.monthlyCostChangesList = monthlyCostChangesList;
    }
}
