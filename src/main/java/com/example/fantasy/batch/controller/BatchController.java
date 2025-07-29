package com.example.fantasy.batch.controller;

import com.example.fantasy.batch.job.PlayerCsvImportJobConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
@Slf4j
public class BatchController {

//    private final JobLauncher jobLauncher;
//    private final Job importPlayerJob;

//    @PostMapping("/players")
//    public ResponseEntity<String> importPlayers(@RequestParam("file") MultipartFile file) {
//        try {
//            // Validate file format
//            if (!file.getOriginalFilename().endsWith(".csv")) {
//                return ResponseEntity.badRequest().body("Only CSV files are supported");
//            }
//
//            // Store the uploaded file to disk for batch to pick up
//            Path tempFile = Files.createTempFile("players-", ".csv");
//            file.transferTo(tempFile.toFile());
//            log.info("Saved uploaded CSV file to: {}", tempFile.toAbsolutePath());
//
//            // Configure job parameters
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString("inputFile", tempFile.toAbsolutePath().toString())
//                    .addLong("timestamp", System.currentTimeMillis()) // to ensure uniqueness
//                    .toJobParameters();
//
//            // Launch the batch job asynchronously
//            JobExecution jobExecution = jobLauncher.run(importPlayerJob, jobParameters);
//            log.info("Started player import batch job: {}", jobExecution.getJobId());
//
//            return ResponseEntity.ok("Player import batch triggered successfully. Job ID: " + jobExecution.getJobId());
//        } catch (Exception e) {
//            log.error("Failed to start player import batch job", e);
//            return ResponseEntity.internalServerError()
//                .body("Failed to start import: " + e.getMessage());
//        }
//    }
}
