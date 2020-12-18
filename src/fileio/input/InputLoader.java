package fileio.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import game.database.Consumers;
import game.database.Distributors;
import game.factory.ConsumerFactory;
import game.factory.DistributorFactory;
import game.factory.PlayerFactory;
import game.player.Consumer;
import game.player.Distributor;

import java.io.File;
import java.io.IOException;

public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    private final File inputFile;

    private InputData inputData;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
        inputFile = new File(inputPath);
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * Loads input into Singleton Consumers & Distributors classes
     */
    public void loadData() {
        readData();
        transferData();
    }

    /**
     * Transfers JSON data into InputData class
     */
    public void readData() {
        ObjectMapper objectMapper = new ObjectMapper();
        inputData = new InputData();

        try {
            inputData = objectMapper.readValue(inputFile, InputData.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Transfers InputData into Consumers & Distributors classes
     */
    public void transferData() {
        PlayerFactory consumerFactory = new ConsumerFactory();
        PlayerFactory distributorFactory = new DistributorFactory();

        // Game
        Game.setNumberOfTurns(inputData.getNumberOfTurns());

        // Consumers
        for (ConsumerInputData consumerInputData : inputData.getInitialData().getConsumers()) {
            String[] args = new String[3];
            args[0] = String.valueOf(consumerInputData.getId());
            args[1] = String.valueOf(consumerInputData.getInitialBudget());
            args[2] = String.valueOf(consumerInputData.getMonthlyIncome());

            Consumers.addToDB((Consumer) consumerFactory.create(args));
        }

        // Distributors
        for (DistributorInputData distributorInputData : inputData.getInitialData().getDistributors()) {
            String[] args = new String[5];
            args[0] = String.valueOf(distributorInputData.getId());
            args[1] = String.valueOf(distributorInputData.getInitialBudget());
            args[2] = String.valueOf(distributorInputData.getContractLength());
            args[3] = String.valueOf(distributorInputData.getInitialInfrastructureCost());
            args[4] = String.valueOf(distributorInputData.getInitialProductionCost());

            Distributors.addToDB((Distributor) distributorFactory.create(args));
        }
    }
}
