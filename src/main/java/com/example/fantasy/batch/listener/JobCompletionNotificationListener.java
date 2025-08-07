package com.example.fantasy.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job {} starting with parameters: {}",
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getJobParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job {} completed with status: {}",
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getStatus());

        // Clean up temporary file if it exists
        String filePath = jobExecution.getJobParameters().getString("filePath");
        if (filePath != null && !filePath.isEmpty()) {
            try {
                Path tempFile = Paths.get(filePath);
                // Only delete if it exists and is in our temp directory
                if (Files.exists(tempFile) &&
                    tempFile.getParent().toString().contains("fantasy-batch-uploads")) {

                    Files.delete(tempFile);
                    log.info("Successfully deleted temporary file: {}", filePath);
                }
            } catch (IOException e) {
                log.error("Error cleaning up temporary file: {}", filePath, e);
            }
        }
    }
}
