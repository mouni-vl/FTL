package com.example.fantasy.application.service;

import com.example.fantasy.application.port.in.BatchJobUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

/**
 * Service implementation for triggering batch jobs
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BatchJobService implements BatchJobUseCase {

    private final JobLauncher jobLauncher;
    private final Job importPlayerCsvJob;

    @Override
    public Long importPlayerCsv() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            
            JobExecution execution = jobLauncher.run(importPlayerCsvJob, jobParameters);
            log.info("Started player CSV import job with ID: {}", execution.getId());
            
            return execution.getId();
        } catch (Exception e) {
            log.error("Error starting player import job", e);
            throw new RuntimeException("Failed to start player import job", e);
        }
    }
}
