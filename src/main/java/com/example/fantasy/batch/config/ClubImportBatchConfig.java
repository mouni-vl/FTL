package com.example.fantasy.batch.config;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import com.example.fantasy.batch.csvRecord.ClubCsvRecord;
import com.example.fantasy.batch.listener.JobCompletionNotificationListener;
import com.example.fantasy.batch.processor.club.ClubItemProcessor;
import com.example.fantasy.batch.reader.club.ClubCsvReader;
import com.example.fantasy.batch.writer.club.ClubEntityWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
public class ClubImportBatchConfig {

    private static final String JOB_NAME = "importClubCsvJob";
    private static final String STEP_NAME = "importClubCsvStep";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ClubItemProcessor clubProcessor;
    private final ClubEntityWriter clubWriter;
    private final JobCompletionNotificationListener jobCompletionListener;

    @Bean
    public Job importClubCsvJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionListener)
                .flow(importClubCsvStep())
                .end()
                .build();
    }

    @Bean
    public Step importClubCsvStep() {
        return new StepBuilder(STEP_NAME, jobRepository)
                .<ClubCsvRecord, ClubEntity>chunk(100, transactionManager)
                .reader(clubCsvReader(null))
                .processor(clubProcessor)
                .writer(clubWriter)
                .faultTolerant()
                .skipLimit(10)
                .skip(Exception.class)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<ClubCsvRecord> clubCsvReader(
            @Value("#{jobParameters['filePath']}") String filePath) {
        log.info("Initializing club CSV reader with file path: {}", filePath);

        Resource resource;
        if (filePath != null && !filePath.isEmpty()) {
            resource = new FileSystemResource(filePath);
            log.info("Using uploaded file: {}", filePath);
        } else {
            log.info("No file path provided, using default resource");
            resource = new ClassPathResource("data/fm_clubs.csv");
        }

        return new ClubCsvReader(resource);
    }
}
