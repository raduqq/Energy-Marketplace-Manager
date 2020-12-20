package fileio.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import game.Game;
import game.database.Consumers;
import game.database.Distributors;
import game.factory.player.ConsumerFactory;
import game.factory.player.DistributorFactory;
import game.factory.player.PlayerFactory;
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

    public InputData getInputData() {
        return inputData;
    }

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
        inputFile = new File(inputPath);
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
        Game.getInstance().setNumberOfTurns(inputData.getNumberOfTurns());

        // Consumers
        inputData.getInitialData().getConsumers().forEach(consumerInputData -> {
            String[] args = new String[Constants.NO_CONSUMER_PARAM];
            args[Constants.ZEROTH_ARG] = String
                    .valueOf(consumerInputData
                            .getId());
            args[Constants.FIRST_ARG] = String
                    .valueOf(consumerInputData
                            .getInitialBudget());
            args[Constants.SECOND_ARG] = String
                    .valueOf(consumerInputData
                            .getMonthlyIncome());
            Consumers.getInstance().addToDB((Consumer) consumerFactory.create(args));
        });

        // Distributors
        inputData.getInitialData().getDistributors().forEach(distributorInputData -> {
            String[] args = new String[Constants.NO_DISTRIB_PARAM];
            args[Constants.ZEROTH_ARG] = String
                    .valueOf(distributorInputData
                            .getId());
            args[Constants.FIRST_ARG] = String
                    .valueOf(distributorInputData
                            .getInitialBudget());
            args[Constants.SECOND_ARG] = String
                    .valueOf(distributorInputData
                            .getContractLength());
            args[Constants.THIRD_ARG] = String
                    .valueOf(distributorInputData
                            .getInitialInfrastructureCost());
            args[Constants.FOURTH_ARG] = String
                    .valueOf(distributorInputData
                            .getInitialProductionCost());
            Distributors.getInstance().addToDB((Distributor) distributorFactory.create(args));
        });
    }
}
