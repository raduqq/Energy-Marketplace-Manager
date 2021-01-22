package fileio.output;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import game.database.Consumers;
import game.database.Distributors;
import game.element.Contract;
import game.player.Consumer;
import game.player.Distributor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class OutputWriter {
    // TODO: may need to be updated?
    private final File outputFile;

    private final OutputData outputData;

    public OutputWriter(final String outputPath) {
        outputFile = new File(outputPath);
        outputData = new OutputData();
    }

    /**
     * Transfers data from databases to an OutputData object
     */
    public void transferData() {
        for (Consumer consumer : Consumers.getInstance().getConsumerList()) {
            outputData.getConsumers()
                        .add(new OutputData
                                .ConsumerOutputData(consumer.getId(),
                                                    consumer.getIsBankrupt(),
                                                    consumer.getBudget()));
        }

        for (Distributor distributor : Distributors.getInstance().getDistributorList()) {
            List<OutputData
            .DistributorOutputData
            .ContractOutputData> contractOutputDataList = new ArrayList<>();

            for (Contract contract : distributor.getContractList()) {
                contractOutputDataList
                .add(new OutputData
                        .DistributorOutputData
                        .ContractOutputData(contract.getBeneficiaryID(),
                                            contract.getPrice(),
                                            contract.getRemContractMonths()));
            }

            outputData.getDistributors()
                        .add(new OutputData.DistributorOutputData(
                            distributor.getId(),
                            distributor.getBudget(),
                            distributor.getIsBankrupt(),
                            contractOutputDataList));
        }
    }

    /**
     * Writes data from an OutputData object to file
     */
    public void writeData() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            objectWriter.writeValue(outputFile, outputData);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Uploads data to output file
     */
    public void uploadData() {
        transferData();
        writeData();
    }
}
