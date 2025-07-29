package com.example.fantasy.batch.config;

import com.example.fantasy.application.port.out.PlayerRepository;
import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import com.example.fantasy.batch.processor.PlayerItemProcessor;
import com.example.fantasy.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PlayerImportBatchConfig {

    private final PlayerRepository playerRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    @StepScope
    public FlatFileItemReader<PlayerCsvRecord> playerReader(@Value("#{jobParameters['inputFile']}") String inputFile) {
        log.info("Reading player data from file: {}", inputFile);
        return new FlatFileItemReaderBuilder<PlayerCsvRecord>()
                .name("playerReader")
                .resource(new FileSystemResource(inputFile))
                .delimited()
                .delimiter(",")
                .names("fmId", "firstName", "secondName", "fullName", "dateOfBirth", 
                       "cityOfBirth", "permanentClub", "basedClub", "loanClub", 
                       "squadNumber", "nationality1", "nationality2", "nationality3",
                       "nfe", "currentAbility", "potentialAbility", "leftFoot", 
                       "rightFoot", "availabilityStatus", "mainPosition", "positions", 
                       "price", "photoUrl")
                .linesToSkip(1) // Skip header
                .fieldSetMapper(new BeanWrapperFieldSetMapper<PlayerCsvRecord>() {{
                    setTargetType(PlayerCsvRecord.class);
                }})
                .build();
    }

    @Bean
    public PlayerItemProcessor playerProcessor() {
        return new PlayerItemProcessor();
    }

    @Bean
    public ItemWriter<Player> playerWriter() {
        return players -> {
            for (Player player : players) {
                // Check if player already exists (by fmId)
                playerRepository.findByFmId(player.getFmId())
                    .ifPresentOrElse(
                        existingPlayer -> {
                            // Update existing player
                            log.info("Updating existing player: {}", existingPlayer.getFullName());
                            player.setId(existingPlayer.getId());
                            playerRepository.update(player);
                        },
                        () -> {
                            // Create new player
                            log.info("Creating new player: {}", player.getFullName());
                            playerRepository.save(player);
                        }
                    );
            }
        };
    }

    @Bean
    public SkipPolicy playerSkipPolicy() {
        return (throwable, skipCount) -> {
            // Skip processing for certain exceptions, up to a limit
            log.warn("Considering skip for exception: {}", throwable.getMessage());
            
            if (skipCount > 10) {
                log.error("Too many errors, failing the job");
                return false;
            }
            
            // Skip common validation/parsing errors
            return true; 
        };
    }

    @Bean
    public Step importPlayerStep() {
        return null;
    }

    @Bean
    public Job importPlayerJob() {
        return null;
    }
}
