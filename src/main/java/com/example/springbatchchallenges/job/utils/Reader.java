package com.example.springbatchchallenges.job.utils;

import com.example.springbatchchallenges.job.vo.RestaurantCsvVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class Reader {
    private final Logger logger = LoggerFactory.getLogger(Reader.class);

    @Bean
    public FlatFileItemReader<RestaurantCsvVO> csvReader() throws Exception {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(RestaurantCsvVO.getFieldNames().toArray(String[]::new));
        delimitedLineTokenizer.setStrict(false);

        DefaultLineMapper<RestaurantCsvVO> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper((fieldSet -> {
            try {
                for(String value : fieldSet.getValues()) {
                    if(checkContainDoubleQuotation(value)) {
                        if(checkContainDoubleQuotationOddNumber(value)) {
                            throw new RuntimeException("Double quotation mark is odd number");
                        }
                    }
                }

                return RestaurantCsvVO.builder()
                        .no(fieldSet.readString("no"))
                        .openServiceName(fieldSet.readString("openServiceName"))
                        .openServiceId(fieldSet.readString("openServiceId"))
                        .openMunicipalityCode(fieldSet.readString("openMunicipalityCode"))
                        .controlNumber(fieldSet.readString("controlNumber"))
                        .licensingDate(fieldSet.readString("licensingDate"))
                        .licensingCancelDate(fieldSet.readString("licensingCancelDate"))
                        .businessStatusCode(fieldSet.readString("businessStatusCode"))
                        .businessStatusName(fieldSet.readString("businessStatusName"))
                        .businessDetailStatusCode(fieldSet.readString("businessDetailStatusCode"))
                        .businessDetailStatusName(fieldSet.readString("businessDetailStatusName"))
                        .closingDate(fieldSet.readString("closingDate"))
                        .suspensionStartDate(fieldSet.readString("suspensionStartDate"))
                        .suspensionEndDate(fieldSet.readString("suspensionEndDate"))
                        .reopeningDate(fieldSet.readString("reopeningDate"))
                        .locationPhone(fieldSet.readString("locationPhone"))
                        .locationArea(fieldSet.readString("locationArea"))
                        .locationZipCode(fieldSet.readString("locationZipCode"))
                        .locationFullAddress(fieldSet.readString("locationFullAddress"))
                        .streetFullAddress(fieldSet.readString("streetFullAddress"))
                        .streetZipCode(fieldSet.readString("streetZipCode"))
                        .businessLocationName(fieldSet.readString("businessLocationName"))
                        .lastModifiedDatetime(fieldSet.readString("lastModifiedDatetime"))
                        .dataRenewalType(fieldSet.readString("dataRenewalType"))
                        .dataRenewalDate(fieldSet.readString("dataRenewalDate"))
                        .businessTypeName(fieldSet.readString("businessTypeName"))
                        .coordinateX(fieldSet.readString("coordinateX"))
                        .coordinateY(fieldSet.readString("coordinateY"))
                        .sanitationBusinessTypeName(fieldSet.readString("sanitationBusinessTypeName"))
                        .maleWorkersCount(fieldSet.readString("maleWorkersCount"))
                        .femaleWorkersCount(fieldSet.readString("femaleWorkersCount"))
                        .businessNeighborhoodAreaName(fieldSet.readString("businessNeighborhoodAreaName"))
                        .gradeTypeName(fieldSet.readString("gradeTypeName"))
                        .waterSupplyType(fieldSet.readString("waterSupplyType"))
                        .employeeTotalCount(fieldSet.readString("employeeTotalCount"))
                        .headOfficeEmployeeCount(fieldSet.readString("headOfficeEmployeeCount"))
                        .factoryOfficeEmployeeCount(fieldSet.readString("factoryOfficeEmployeeCount"))
                        .factorySalesEmployeeCount(fieldSet.readString("factorySalesEmployeeCount"))
                        .factoryProduceEmployeeCount(fieldSet.readString("factoryProduceEmployeeCount"))
                        .buildingOwnershipType(fieldSet.readString("buildingOwnershipType"))
                        .guaranteeAmount(fieldSet.readString("guaranteeAmount"))
                        .monthlyRentAmount(fieldSet.readString("monthlyRentAmount"))
                        .multiUseEstablishmentYn(fieldSet.readString("multiUseEstablishmentYn"))
                        .facilityTotalSize(fieldSet.readString("facilityTotalSize"))
                        .traditionalEstablishmentDesignationNumber(fieldSet.readString("traditionalEstablishmentDesignationNumber"))
                        .traditionalEstablishmentMainFood(fieldSet.readString("traditionalEstablishmentMainFood"))
                        .homepage(fieldSet.readString("homepage"))
                        .empty(fieldSet.readString("empty"))
                        .build();
            } catch (Exception e) {
                logger.info("Error: {}", e.getMessage());
                logger.info("fieldSet: {}", fieldSet);
                return RestaurantCsvVO.builder().build();
            }
        }));

        FlatFileItemReader<RestaurantCsvVO> flatFileItemReader = new FlatFileItemReaderBuilder<RestaurantCsvVO>()
                .name("csvFileItemReader")
                .encoding("EUC-KR")
                .resource(new ClassPathResource("data.csv"))
                .linesToSkip(1)
//                .recordSeparatorPolicy(new DefaultRecordSeparatorPolicy())
                .lineMapper(defaultLineMapper)
                .build();

        flatFileItemReader.afterPropertiesSet();

        return flatFileItemReader;
    }

    private boolean checkContainDoubleQuotation(String text) {
        return Pattern.compile("\"").matcher(text).find();
    }

    private boolean checkContainDoubleQuotationOddNumber(String text) {
        int quoteCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\"') {
                quoteCount++;
            }
        }
        if (quoteCount % 2 != 0) {
            return true;
        }
        return false;
    }
}
