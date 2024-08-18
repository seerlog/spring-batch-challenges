package com.example.springbatchchallenges.job.utils;

import com.example.springbatchchallenges.job.vo.RestaurantCsvVO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class Reader {
    @Bean
    public FlatFileItemReader<RestaurantCsvVO> csvReader() throws Exception {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(RestaurantCsvVO.getFieldNames().toArray(String[]::new));
        delimitedLineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<RestaurantCsvVO> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(RestaurantCsvVO.class);

        DefaultLineMapper<RestaurantCsvVO> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

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
}
