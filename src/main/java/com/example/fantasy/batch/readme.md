Spring Batch Architecture in This Project
### example: Player Import Batch Job

Core Components
1. Configuration Layer (PlayerImportBatchConfig)
   This class defines the batch job pipeline and its components:


Job: The complete batch process (importing players)
Step: A discrete phase in the job (reading, processing, writing)
ItemReader: Reads input data from the CSV file
ItemProcessor: Transforms PlayerCsvRecord into Player domain objects
ItemWriter: Persists processed data to your repository
SkipPolicy: Handles exceptions during processing
2. Domain Layer
   PlayerCsvRecord: Data Transfer Object representing the raw CSV structure
   Player: Your domain entity representing the business model
3. Processing Layer
   PlayerItemProcessor: Contains business logic to transform input data to domain objects
4. Infrastructure
   JobRepository: Tracks job execution metadata
   TransactionManager: Manages database transactions
   Why Each Component is Necessary
   FlatFileItemReader: Reads CSV records efficiently, handling large files without memory issues. The .names() method maps CSV columns to PlayerCsvRecord fields.


ItemProcessor: Separates transformation logic from reading/writing concerns, following Single Responsibility Principle. It converts raw data to your domain model.


ItemWriter: Manages persistence logic, including existence checks and update/insert decisions.


SkipPolicy: Provides fault tolerance, allowing the job to continue despite some errors.


Step: Encapsulates a complete unit of work with its own transaction boundaries.


Job: Orchestrates the entire process, managing execution order and failure handling.


This architecture follows Spring Batch's chunk-oriented processing model, where items are read individually, processed, and written in chunks (transactions), providing excellent performance and resilience for batch operations.