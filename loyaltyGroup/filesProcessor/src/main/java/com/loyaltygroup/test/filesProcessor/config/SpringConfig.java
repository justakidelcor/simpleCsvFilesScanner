package com.loyaltygroup.test.filesProcessor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class SpringConfig {

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }

}
