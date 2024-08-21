package com.example.springbatchchallenges.job;

import com.example.springbatchchallenges.job.vo.RestaurantVO;
import lombok.RequiredArgsConstructor;
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
public class RestaurantJob {
    private final RestaurantReader restaurantReader;
    private final RestaurantWriter restaurantWriter;
    private static final int chunkSize = 1000;

    @Bean
    public Job restaurantJob(JobRepository jobRepository, Step saveFromCsvToDB) {
        return new JobBuilder("RestaurantJob", jobRepository)
                .start(saveFromCsvToDB)
                .build();
    }

    @Bean
    public Step saveFromCsvToDB(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) throws Exception {
        return new StepBuilder("saveFromCsvToDB", jobRepository)
                .<RestaurantVO, RestaurantVO>chunk(chunkSize, platformTransactionManager)
                .reader(restaurantReader.csvReader())
                .writer(restaurantWriter)
                .build();
    }
}
