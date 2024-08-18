package com.example.springbatchchallenges.job;

import com.example.springbatchchallenges.job.utils.CsvReader;
import com.example.springbatchchallenges.job.utils.DbWriter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class RestaurantJobConfiguration {
    private final Logger logger = LoggerFactory.getLogger(RestaurantJobConfiguration.class);
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CsvReader csvReader;
    private final DbWriter dbWriter;
    private static final int chunkSize = 1000;

    @Bean
    public Job RestaurantJob() {
        return new JobBuilder("RestaurantJob", jobRepository)
                .start(readCsv())
                .build();
    }

    @Bean
    public Step readCsv() {
        return new StepBuilder("readCsv", jobRepository)
                .tasklet(((contribution, chunkContext) -> {
                    logger.info("Hello, Spring Batch!");
                    return RepeatStatus.FINISHED;
                }), transactionManager)
                .build();
    }
}
