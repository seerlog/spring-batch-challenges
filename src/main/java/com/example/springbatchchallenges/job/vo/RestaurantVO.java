package com.example.springbatchchallenges.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RestaurantVO {
    private RestaurantCsvVO restaurantCsvVO;
    private boolean hasError;
    private String errorMessage;

    public static RestaurantVO of(RestaurantCsvVO restaurantCsvVO, boolean hasError, String errorMessage) {
        return RestaurantVO.builder()
                .restaurantCsvVO(restaurantCsvVO)
                .hasError(hasError)
                .errorMessage(errorMessage)
                .build();
    }
}
