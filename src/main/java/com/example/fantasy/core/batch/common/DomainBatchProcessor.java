package com.example.fantasy.core.batch.common;

import org.springframework.batch.item.ItemProcessor;

/**
 * Interface for domain-specific batch processors.
 * Extends Spring Batch's ItemProcessor with domain-specific processing logic.
 *
 * @param <I> The input type to be processed
 * @param <O> The output type after processing
 */
public interface DomainBatchProcessor<I, O> extends ItemProcessor<I, O> {

    /**
     * Process the provided item into a domain entity
     *
     * @param item The item to process
     * @return The processed item or null if the item should be filtered
     * @throws Exception if processing fails
     */
    @Override
    O process(I item) throws Exception;
}
