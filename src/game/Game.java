package game;

import fileio.input.ConsumerInputData;
import fileio.input.MonthlyUpdateInputData;
import fileio.input.MonthlyUpdateInputData.CostChangesInputData;
import game.database.Consumers;
import game.database.Distributors;
import game.element.Contract;
import game.player.Consumer;
import game.player.Distributor;

import java.util.ArrayList;
import java.util.List;

// TODO: scoate metodele statice!
public final class Game {
    private static final Game instance;

    // puteai sa agregi aici o lista de players si terminai situatia

    private static int numberOfTurns;

    // de inclus si Game.reset();
    static { instance = new Game(); }

    public static Game getInstance() { return instance; }

    public static int getNumberOfTurns() {
        return numberOfTurns;
    }

    public static void setNumberOfTurns(int numberOfTurns) {
        Game.numberOfTurns = numberOfTurns;
    }

    public static void updatePlayers() { // la toti playerii
        // consumeri -> intra leafa
        Consumers.getInstance().getConsumerList().forEach(Consumer::update);
        // distributori -> new prices + contracts running down
        Distributors.getInstance().getDistributorList().forEach(Distributor::update);
    }

    public static void updateElements() { // contractele
        List<Contract> expiredContracts = new ArrayList<>();

        for (Distributor distributor : Distributors.getInstance().getDistributorList()) {
            for (Contract contract : distributor.getContractList()) {
                contract.update();

                if (contract.getRemContractMonths() < 0) {
                    expiredContracts.add(contract);
                }
            }

            for (Contract expiredContract : expiredContracts) {
                distributor.terminateContract(expiredContract);
            }
        }
    }

    public static void applyMonthlyUpdates(List<MonthlyUpdateInputData> monthlyUpdates) {
        // Fetch current update
        MonthlyUpdateInputData currUpdate = monthlyUpdates.get(0);

        // TODO: player factory
        // add new consumers => TODO: Consumers.update(newConsumers) in loc de asta
        for (ConsumerInputData consumerInputData : currUpdate.getNewConsumers()) {
            Consumers.getInstance().addToDB(new Consumer(consumerInputData.getId(),
                                            consumerInputData.getInitialBudget(),
                                            consumerInputData.getMonthlyIncome()));
        }

        // update distributorii cu pricina => TODO: Distributors.update(costsChanges) in loc de asta
        for (CostChangesInputData costChangesInputData : currUpdate.getCostsChanges()) {
            Distributor currDistributor = Distributors.getInstance().findDistributorByID(costChangesInputData.getId());
            assert currDistributor != null;

            currDistributor.setInfrastructureCosts(costChangesInputData.getInfrastructureCost());
            currDistributor.setProductionCosts(costChangesInputData.getProductionCost());
        }

        // Done with current update
        monthlyUpdates.remove(0);
    }

    public static void takeTurns() {
        Consumers.getInstance().getConsumerList().forEach(Consumer::takeTurn);
        Distributors.getInstance().getDistributorList().forEach(Distributor::takeTurn);
    }

    public static void play(List<MonthlyUpdateInputData> monthlyUpdates) {
        for (int i = 0; i <= numberOfTurns; i++) {
            if (i > 0) {
                applyMonthlyUpdates(monthlyUpdates);
            }

            updatePlayers();
            takeTurns();
            updateElements();

            // DEBUG
            System.out.println("---------------------- " + (i - 1) + " ----------------------");
            for (Consumer consumer : Consumers.getInstance().getConsumerList()) System.out.println(consumer);
            for (Distributor distributor : Distributors.getInstance().getDistributorList()) System.out.println(distributor);
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "numberOfTurns=" + numberOfTurns +
                '}';
    }
}
