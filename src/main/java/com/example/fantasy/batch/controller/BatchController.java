package com.example.fantasy.batch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
@Slf4j
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job importPlayerCsvJob;

    @PostMapping(value = "/import-players", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> importPlayers(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("success", false);
            response.put("message", "Please upload a CSV file");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // Create temp directory if it doesn't exist
            Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "fantasy-batch-uploads");
            if (!Files.exists(tempDir)) {
                Files.createDirectories(tempDir);
            }

            // Create a unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : ".csv";
            String uniqueFilename = "players_" + System.currentTimeMillis() + fileExtension;
            Path tempFile = tempDir.resolve(uniqueFilename);

            // Save the file
            log.info("Saving uploaded file to: {}", tempFile);
            Files.copy(file.getInputStream(), tempFile);

            // Create job parameters
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("filePath", tempFile.toString())
                    .addDate("time", new Date())
                    .toJobParameters();

            // Launch the job
            log.info("Launching import job with parameters: {}", jobParameters);
            JobExecution jobExecution = jobLauncher.run(importPlayerCsvJob, jobParameters);

            // Prepare response
            response.put("success", true);
            response.put("jobId", jobExecution.getId());
            response.put("jobStatus", jobExecution.getStatus().toString());
            response.put("message", "File upload successful. Import job started.");

            log.info("Job launched successfully with ID: {}", jobExecution.getId());
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            log.error("Error saving uploaded file", e);
            response.put("success", false);
            response.put("message", "Failed to save uploaded file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            log.error("Error launching job", e);
            response.put("success", false);
            response.put("message", "Failed to launch import job: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
