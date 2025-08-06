package com.example.fantasy.batch.jobs.player;

import com.example.fantasy.core.batch.common.AbstractBatchJobConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuration class for the player CSV import job.
 * Extends the abstract batch configuration to implement a specific
 * job for importing player data from CSV files.
 */
@Slf4j
@Configuration
public class PlayerCsvImportJobConfig extends AbstractBatchJobConfig<PlayerCsvItem, Object> {

    @Value("classpath:data/fm_players.csv")
    private Resource playersResource;

    public PlayerCsvImportJobConfig(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        super(jobRepository, transactionManager);
    }

    /**
     * Creates and configures the player import job.
     *
     * @return The configured Job
     */
    @Bean
    public Job playerImportJob() {
        return createJob("playerImportJob");
    }

    /**
     * Creates a CSV reader for player data.
     *
     * @return The configured CSV reader
     */
    @Override
    protected ItemReader<PlayerCsvItem> reader() {
        log.info("Initializing player CSV reader with resource: {}", playersResource);

        // TODO: Implement actual reader with proper CSV mapping
        return new FlatFileItemReaderBuilder<PlayerCsvItem>()
                .name("playerCsvReader")
                .resource(playersResource)
                .delimited()
                .names("name", "position", "club") // Add all required field names
                .targetType(PlayerCsvItem.class)
                .build();
    }

    /**
     * Creates a processor for transforming player CSV data into domain objects.
     *
     * @return The configured processor
     */
    @Override
    protected ItemProcessor<PlayerCsvItem, Object> processor() {
        return new PlayerAggregationProcessor();
    }

    /**
     * Creates a writer for persisting player domain objects to the database.
     *
     * @return The configured writer
     */
    @Override
    protected ItemWriter<Object> writer() {
        return new PlayerEntityWriter();
    }
}
