package com.example.springbatchchallenges.job;

import com.example.springbatchchallenges.job.vo.RestaurantCsvVO;
import com.example.springbatchchallenges.job.vo.RestaurantVO;
import com.example.springbatchchallenges.utils.Util;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class RestaurantReader {
    private final Logger logger = LoggerFactory.getLogger(RestaurantReader.class);

    @Bean
    public FlatFileItemReader<RestaurantVO> csvReader() throws Exception {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(RestaurantCsvVO.getFieldNames().toArray(String[]::new));
        delimitedLineTokenizer.setStrict(false);

        DefaultLineMapper<RestaurantVO> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper((fieldSet -> {
            try {
                hasDoubleQuotationOddNumber(fieldSet);
                hasInvalidDate(fieldSet);
                return RestaurantVO.of(RestaurantCsvVO.of(fieldSet), false, "");
            } catch (Exception e) {
                String message = ObjectUtils.isEmpty(e.getMessage()) ? "Unknown" : e.getMessage();
                return RestaurantVO.of(RestaurantCsvVO.of(fieldSet.readString("no")), true, message);
            }
        }));

        FlatFileItemReader<RestaurantVO> flatFileItemReader = new FlatFileItemReaderBuilder<RestaurantVO>()
                .name("csvFileItemReader")
                .encoding("EUC-KR")
                .resource(new ClassPathResource("data.csv"))
                .linesToSkip(1)
                .lineMapper(defaultLineMapper)
                .build();

        flatFileItemReader.afterPropertiesSet();
        return flatFileItemReader;
    }

    private void hasDoubleQuotationOddNumber(FieldSet fieldSet) {
        for(String value : fieldSet.getValues()) {
            if(checkContainDoubleQuotation(value)) {
                if(checkContainDoubleQuotationOddNumber(value)) {
                    throw new RuntimeException("Double quotation mark is odd number");
                }
            }
        }
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

    private void hasInvalidDate(FieldSet fieldSet) {
        if(!isCorrectDateFormat(fieldSet)) {
            throw new RuntimeException("Invalid date format");
        }
    }

    private boolean isCorrectDateFormat(FieldSet fieldSet) {
        try {
            Util.parseDate(fieldSet.readString("licensingDate"));
            Util.parseDate(fieldSet.readString("licensingCancelDate"));
            Util.parseDate(fieldSet.readString("closingDate"));
            Util.parseDate(fieldSet.readString("suspensionStartDate"));
            Util.parseDate(fieldSet.readString("suspensionEndDate"));
            Util.parseDate(fieldSet.readString("reopeningDate"));

            Util.parseDateTime(fieldSet.readString("lastModifiedDatetime"));
            Util.parseDateTime(fieldSet.readString("dataRenewalDate"));

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
