package com.example.fantasy.core.batch.common;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

/**
 * Abstract base configuration for batch jobs to standardize step creation.
 * Follows the template method pattern where concrete implementations
 * provide specific reader, processor, and writer components.
 *
 * @param <I> Input type for the batch job
 * @param <O> Output type after processing
 */
@RequiredArgsConstructor
public abstract class AbstractBatchJobConfig<I, O> {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    /**
     * Default chunk size for batch processing
     */
    protected static final int CHUNK_SIZE = 100;

    /**
     * Creates a job with the given name using the configured step
     *
     * @param jobName Name for the job
     * @return Configured Job
     */
    protected Job createJob(String jobName) {
        return new JobBuilder(jobName, jobRepository)
                .flow(createStep(jobName + "Step"))
                .end()
                .build();
    }

    /**
     * Creates a step with the given name using the reader, processor, and writer
     *
     * @param stepName Name for the step
     * @return Configured Step
     */
    protected Step createStep(String stepName) {
        return new StepBuilder(stepName, jobRepository)
                .<I, O>chunk(CHUNK_SIZE, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    /**
     * Provides the item reader for this job
     *
     * @return Configured ItemReader
     */
    protected abstract ItemReader<I> reader();

    /**
     * Provides the item processor for this job
     *
     * @return Configured ItemProcessor
     */
    protected abstract ItemProcessor<I, O> processor();

    /**
     * Provides the item writer for this job
     *
     * @return Configured ItemWriter
     */
    protected abstract ItemWriter<O> writer();
}
