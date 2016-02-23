package com.ohayoyo.gateway.webapp.config;

import com.ohayoyo.spring.config.thymeleaf.SimpleThymeleafConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@Import({
        SimpleThymeleafConfig.class
})
public class GatewayWebMvcConfig extends WebMvcConfigurerAdapter {

}
