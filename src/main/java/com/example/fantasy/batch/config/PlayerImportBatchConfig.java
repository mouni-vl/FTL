package com.example.fantasy.batch.config;

import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import com.example.fantasy.batch.listener.JobCompletionNotificationListener;
import com.example.fantasy.batch.processor.PlayerItemProcessor;
import com.example.fantasy.batch.reader.PlayerCsvReader;
import com.example.fantasy.batch.writer.PlayerEntityWriter;
import com.example.fantasy.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class PlayerImportBatchConfig {

    private static final String JOB_NAME = "importPlayerCsvJob";
    private static final String STEP_NAME = "importPlayerCsvStep";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final PlayerItemProcessor playerProcessor;
    private final PlayerEntityWriter playerWriter;
    private final JobCompletionNotificationListener jobCompletionListener;

    @Bean
    public Job importPlayerCsvJob(Step importPlayerCsvStep) {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionListener)
                .flow(importPlayerCsvStep)
                .end()
                .build();
    }

    @Bean
    public Step importPlayerCsvStep(FlatFileItemReader<PlayerCsvRecord> playerCsvReader) {
        return new StepBuilder(STEP_NAME, jobRepository)
                .<PlayerCsvRecord, Player>chunk(100, transactionManager)
                .reader(playerCsvReader)
                .processor(playerProcessor)
                .writer(playerWriter)
                .transactionManager(transactionManager)
                .faultTolerant()
                .skipLimit(10)
                .skip(Exception.class)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<PlayerCsvRecord> playerCsvReader(
            @Value("#{jobParameters['filePath']}") String filePath) {
        log.info("Initializing player CSV reader with file path: {}", filePath);

        Resource resource;
        if (filePath != null && !filePath.isEmpty()) {
            resource = new FileSystemResource(filePath);
        } else {
            log.info("No file path provided, using default resource");
            resource = new ClassPathResource("data/fm_players.csv");
        }

        return new PlayerCsvReader(resource);
    }

    @Bean
    public StepExecutionListener stepListener() {
        //return new LoggingStepListener(); // implement to log start/end and item count
        return null;
    }
}