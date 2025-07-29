package com.example.fantasy.batch.job;

import com.example.fantasy.batch.processor.PlayerAggregationProcessor;
import com.example.fantasy.batch.reader.PlayerCsvItem;
import com.example.fantasy.batch.writer.PlayerEntityWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class PlayerCsvImportJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final PlayerAggregationProcessor playerProcessor;
    private final PlayerEntityWriter playerWriter;

    public PlayerCsvImportJobConfig(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            PlayerAggregationProcessor playerProcessor,
            PlayerEntityWriter playerWriter) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.playerProcessor = playerProcessor;
        this.playerWriter = playerWriter;
    }

    @Bean
    public Job importPlayerCsvJob() {
        return new JobBuilder("importPlayerCsvJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(playerCsvStep())
                .build();
    }

    @Bean
    public Step playerCsvStep() {
        return new StepBuilder("playerCsvStep", jobRepository)
                .<PlayerCsvItem, List<PlayerCsvItem>>chunk(100, transactionManager)
                .reader(playerCsvReader())
                .processor(playerProcessor)
                .writer(playerWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<PlayerCsvItem> playerCsvReader() {
        FlatFileItemReader<PlayerCsvItem> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data/fm_players.csv"));
        reader.setLinesToSkip(1); // skip header

        DefaultLineMapper<PlayerCsvItem> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("externalPlayerId", "firstName", "lastName", "position", "rating", "clubId");
        lineMapper.setLineTokenizer(tokenizer);

        BeanWrapperFieldSetMapper<PlayerCsvItem> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(PlayerCsvItem.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }
}