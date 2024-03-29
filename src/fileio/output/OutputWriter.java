package fileio.output;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import game.database.Consumers;
import game.database.Distributors;
import game.database.Producers;
import game.element.ConsumerContract;
import game.entity.player.Consumer;
import game.entity.player.Distributor;
import game.entity.support.Producer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class OutputWriter {
    private final File outputFile;
    private final OutputData outputData;

    public OutputWriter(final String outputPath) {
        outputFile = new File(outputPath);
        outputData = new OutputData();
    }
    /**
     * Transfers consumers from database to an OutputData object
     */
    public void transferConsumers() {
        for (Consumer consumer : Consumers.getInstance().getConsumerList()) {
            outputData.getConsumers()
                    .add(new ConsumerOutputData(consumer.getId(),
                            consumer.getIsBankrupt(),
                            consumer.getBudget()));
        }
    }
    /**
     * Transfers distributors from database to an OutputData object
     */
    public void transferDistributors() {
        for (Distributor distributor : Distributors.getInstance().getDistributorList()) {
            List<DistributorOutputData
                    .ContractOutputData> contractOutputDataList = new ArrayList<>();

            for (ConsumerContract consumerContract : distributor.getContractList()) {
                contractOutputDataList
                        .add(new DistributorOutputData
                                .ContractOutputData(consumerContract.getBeneficiaryID(),
                                                    consumerContract.getPrice(),
                                                    consumerContract.getRemContractMonths()));
            }

            outputData.getDistributors()
                    .add(new DistributorOutputData(
                            distributor.getId(),
                            distributor.getEnergyNeededKW(),
                            distributor.getPrice(),
                            distributor.getBudget(),
                            distributor.getProducerStrategyType(),
                            distributor.getIsBankrupt(),
                            contractOutputDataList));
        }
    }
    /**
     * Transfers producers from database to an OutputData object
     */
    public void transferProducers() {
        for (Producer producer : Producers.getInstance().getProducerList()) {
            List<ProducerOutputData.MonthlyStatsOutputData> moStatsOutputData = new ArrayList<>();

            for (Producer.MonthlyStats moStats : producer.getMonthlyStats()) {
                moStatsOutputData
                                    .add(new ProducerOutputData
                                            .MonthlyStatsOutputData(moStats.getMonth(),
                                                                    moStats.getDistributorsIds()));
            }

            outputData.getEnergyProducers()
                    .add(new ProducerOutputData(
                            producer.getId(),
                            producer.getMaxDistributors(),
                            producer.getPriceKW(),
                            producer.getEnergyType(),
                            producer.getEnergyPerDistributor(),
                            moStatsOutputData));
        }
    }
    /**
     * Transfers data from databases to an OutputData object
     */
    public void transferData() {
        transferConsumers();
        transferDistributors();
        transferProducers();
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
