package com.ohayoyo.gateway.channel.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class SpringJunit4PropertyConfigurer {

    @Bean
    public static PropertySourcesPlaceholderConfigurer environmentContextPropertyConfigurer() {
        PropertySourcesPlaceholderConfigurer propertiesConfig = new PropertySourcesPlaceholderConfigurer();
        propertiesConfig.setIgnoreUnresolvablePlaceholders(true);
        propertiesConfig.setFileEncoding("UTF-8");
        return propertiesConfig;
    }

}
