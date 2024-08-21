package com.example.springbatchchallenges.job;

import com.example.springbatchchallenges.domain.errorLog.ErrorLog;
import com.example.springbatchchallenges.domain.errorLog.ErrorLogRepository;
import com.example.springbatchchallenges.domain.restaurant.Restaurant;
import com.example.springbatchchallenges.domain.restaurant.RestaurantRepository;
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
public class RestaurantWriter implements ItemWriter<RestaurantVO> {
    private final Logger logger = LoggerFactory.getLogger(RestaurantWriter.class);
    private final RestaurantRepository restaurantRepository;
    private final ErrorLogRepository errorLogRepository;

    @Override
    public void write(Chunk<? extends RestaurantVO> chunk) {
        Chunk<Restaurant> restaurants = new Chunk<>();
        List<ErrorLog> errors = new ArrayList<>();
        chunk.forEach(restaurantVO -> {
            if(restaurantVO.isHasError()) {
                Restaurant restaurant = Restaurant.of(restaurantVO.getRestaurantCsvVO());
                errors.add(ErrorLog.of(restaurant.getNo(), restaurantVO.getErrorMessage()));
            } else {
                Restaurant restaurant = Restaurant.of(restaurantVO.getRestaurantCsvVO());
                restaurants.add(restaurant);
            }
        });
        restaurantRepository.saveAll(restaurants);
        errorLogRepository.saveAll(errors);
    }
}
