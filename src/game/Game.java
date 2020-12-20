package game;

import fileio.input.MonthlyUpdateInputData;
import game.database.Consumers;
import game.database.Distributors;
import game.element.Contract;
import game.player.Consumer;
import game.player.Distributor;

import java.util.List;

public final class Game {
    private static Game instance;
    private int numberOfTurns;

    static { instance = new Game(); }

    public void reset() {
        instance = new Game();

        Consumers.getInstance().reset();
        Distributors.getInstance().reset();
    }

    public static Game getInstance() { return instance; }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public void updatePlayers() {
        Consumers.getInstance().getConsumerList().forEach(Consumer::update);
        Distributors.getInstance().getDistributorList().forEach(Distributor::update);
    }

    public void updateElements() {
        for (Distributor distributor : Distributors.getInstance().getDistributorList()) {
            for (Contract contract : distributor.getContractList()) {
                contract.update();
            }
        }
    }

    public void applyMonthlyUpdates(List<MonthlyUpdateInputData> monthlyUpdates) {
        // Fetch current update
        MonthlyUpdateInputData currUpdate = monthlyUpdates.get(0);

        // Updating databases
        Consumers.getInstance().update(currUpdate.getNewConsumers());
        Distributors.getInstance().update(currUpdate.getCostsChanges());

        // Done with current update
        monthlyUpdates.remove(0);
    }

    public void takeTurns() {
        Consumers.getInstance().getConsumerList().forEach(Consumer::takeTurn);
        Distributors.getInstance().getDistributorList().forEach(Distributor::takeTurn);
    }

    // Checks if game is still playable (i.e. there are still functioning distributors)
    public boolean endGameCheck() {
        int stillAlive = (int) Distributors.getInstance()
                            .getDistributorList().stream()
                            .filter(distributor -> !distributor.getIsBankrupt())
                            .count();

        return stillAlive == 0;
    }

    public void play(List<MonthlyUpdateInputData> monthlyUpdates) {
        for (int i = 0; i <= numberOfTurns; i++) {
            // Playability check
            if (endGameCheck()) {
                return;
            }

            // Setup round (round 0) doesn't get updates
            if (i > 0) {
                applyMonthlyUpdates(monthlyUpdates);
            }

            // Game logic of each round
            updatePlayers();
            takeTurns();
            updateElements();
        }
    }
}
