package com.example.fantasy.application.port.in;

/**
 * Port for triggering batch jobs from the application layer
 */
public interface BatchJobUseCase {
    
    /**
     * Triggers the player CSV import job
     * @return the job execution positionId
     */
    Long importPlayerCsv();
}
