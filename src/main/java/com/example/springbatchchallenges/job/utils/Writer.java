package com.example.springbatchchallenges.job.utils;

import com.example.springbatchchallenges.domain.restaurant.Restaurant;
import com.example.springbatchchallenges.domain.restaurant.RestaurantRepository;
import com.example.springbatchchallenges.job.RestaurantJobConfiguration;
import com.example.springbatchchallenges.job.vo.RestaurantCsvVO;
import com.example.springbatchchallenges.job.vo.RestaurantVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Writer implements ItemWriter<RestaurantVO> {
    private final Logger logger = LoggerFactory.getLogger(Writer.class);
    private final RestaurantRepository restaurantRepository;

    @Override
    public void write(Chunk<? extends RestaurantVO> chunk) {
        Chunk<Restaurant> restaurants = new Chunk<>();
        List<Long> errors = new ArrayList<>();
        chunk.forEach(restaurantVO -> {
            if(restaurantVO.isHasError()) {
                Restaurant restaurant = Restaurant.of(restaurantVO.getRestaurantCsvVO());
                errors.add(restaurant.getNo());
            } else {
                Restaurant restaurant = Restaurant.of(restaurantVO.getRestaurantCsvVO());
                restaurants.add(restaurant);
            }
        });
        restaurantRepository.saveAll(restaurants);
    }
}
