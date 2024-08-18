package com.example.springbatchchallenges.job;

import com.example.springbatchchallenges.domain.restaurant.Restaurant;
import com.example.springbatchchallenges.job.utils.Reader;
import com.example.springbatchchallenges.job.utils.Writer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class RestaurantJobConfiguration {
    private final Reader reader;
    private final Writer writer;
    private static final int chunkSize = 1000;

    @Bean
    public Job restaurantJob(JobRepository jobRepository, Step saveFromCsvToDB) {
        return new JobBuilder("RestaurantJob", jobRepository)
                .start(saveFromCsvToDB)
                .build();
    }

    @Bean
    public Step saveFromCsvToDB(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("saveFromCsvToDB", jobRepository)
                .<Restaurant, Restaurant>chunk(chunkSize, platformTransactionManager)
                .reader(reader.csvReader())
                .writer(writer)
                .build();
    }
}
