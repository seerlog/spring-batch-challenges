package com.example.springbatchchallenges.job.utils;

import com.example.springbatchchallenges.domain.restaurant.Restaurant;
import com.example.springbatchchallenges.domain.restaurant.RestaurantRepository;
import com.example.springbatchchallenges.job.RestaurantJobConfiguration;
import com.example.springbatchchallenges.job.vo.RestaurantCsvVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Writer implements ItemWriter<RestaurantCsvVO> {
    private final Logger logger = LoggerFactory.getLogger(Writer.class);
    private final RestaurantRepository restaurantRepository;

    @Override
    public void write(Chunk<? extends RestaurantCsvVO> chunk) {
        logger.info("Chunk size: {}", chunk.size());
        logger.info("Chunk: {}", chunk);
//        Chunk<Restaurant> restaurants = new Chunk<>();
//        chunk.forEach(restaurant -> restaurants.add(restaurant));
//        restaurantRepository.saveAll(restaurants);
    }
}
