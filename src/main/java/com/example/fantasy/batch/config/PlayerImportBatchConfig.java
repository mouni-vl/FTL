package com.example.fantasy.batch.config;

import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import com.example.fantasy.batch.processor.PlayerItemProcessor;
import com.example.fantasy.batch.reader.PlayerCsvReader;
import com.example.fantasy.batch.writer.PlayerEntityWriter;
import com.example.fantasy.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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

    @Bean
    public Job importPlayerCsvJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(importPlayerCsvStep())
                .end()
                .build();
    }

    @Bean
    public Step importPlayerCsvStep() {
        return new StepBuilder(STEP_NAME, jobRepository)
                .<PlayerCsvRecord, Player> chunk(100, transactionManager)
                .reader(playerCsvReader())
                .processor(playerProcessor)
                .writer(playerWriter)
                .transactionManager(transactionManager)
                .faultTolerant()
                .skipLimit(10)
                .skip(Exception.class)
                .listener(stepListener())
                .build();
    }

    @Bean
    public FlatFileItemReader<PlayerCsvRecord> playerCsvReader() {
        return new PlayerCsvReader(new ClassPathResource("data/fm_players.csv"));
    }

    @Bean
    public StepExecutionListener stepListener() {
        //return new LoggingStepListener(); // implement to log start/end and item count
        return null;
    }
}