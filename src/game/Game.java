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

    static {
        instance = new Game();
    }

    /**
     * Resets all game entities
     */
    public void reset() {
        instance = new Game();

        Consumers.getInstance().reset();
        Distributors.getInstance().reset();
    }

    public static Game getInstance() {
        return instance;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * Updates the game's players
     * Updates are done each round
     */
    public void updatePlayers() {
        Consumers.getInstance().getConsumerList().forEach(Consumer::update);
        Distributors.getInstance().getDistributorList().forEach(Distributor::update);
    }

    /**
     * Updates the game's elements
     * Updates are done each round
     */
    public void updateElements() {
        for (Distributor distributor : Distributors.getInstance().getDistributorList()) {
            for (Contract contract : distributor.getContractList()) {
                contract.update();
            }
        }
    }

    /**
     * Applying this month's updates
     * @param monthlyUpdates to be applied
     */
    public void applyMonthlyUpdates(final List<MonthlyUpdateInputData> monthlyUpdates) {

        // Fetch current update
        MonthlyUpdateInputData currUpdate = monthlyUpdates.get(0);

        // Updating databases
        // TODO: adapt
        Consumers.getInstance().update(currUpdate.getNewConsumers());
        // TODO: adapt
        Distributors.getInstance().update(currUpdate.getDistributorChanges());
        // TODO: add producers

        // Done with current update
        monthlyUpdates.remove(0);
    }

    /**
     * All players take turns here
     */
    public void takeTurns() {
        Consumers.getInstance().getConsumerList().forEach(Consumer::takeTurn);
        Distributors.getInstance().getDistributorList().forEach(Distributor::takeTurn);
    }

    /**
     * Checks if game is still playable (i.e. there are still functioning distributors)
     * @return true if game is no longer playable
     */
    public boolean endGameCheck() {
        int stillAlive = (int) Distributors.getInstance()
                            .getDistributorList().stream()
                            .filter(distributor -> !distributor.getIsBankrupt())
                            .count();

        return stillAlive == 0;
    }

    /**
     * Simulates numberOfTurns rounds
     * @param monthlyUpdates to be inserted every month
     */
    public void play(final List<MonthlyUpdateInputData> monthlyUpdates) {
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
