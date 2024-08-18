package com.example.springbatchchallenges.domain.restaurant;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface RestaurantRepository extends JpaAttributeConverter<Restaurant, Long> {
}
