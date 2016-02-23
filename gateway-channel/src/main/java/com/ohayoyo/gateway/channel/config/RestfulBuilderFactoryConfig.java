package com.ohayoyo.gateway.channel.config;

import com.ohayoyo.gateway.session.GatewayContext;
import com.ohayoyo.gateway.session.support.RestfulSessionRequestBuilderFactory;
import com.ohayoyo.gateway.session.support.RestfulSessionResponseBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class RestfulBuilderFactoryConfig {

    @Bean(name = "restfulSessionRequestBuilderFactory")
    @Autowired
    public RestfulSessionRequestBuilderFactory restfulSessionRequestBuilderFactory(GatewayContext gatewayContext) {
        RestfulSessionRequestBuilderFactory restfulSessionRequestBuilderFactory = new RestfulSessionRequestBuilderFactory();
        restfulSessionRequestBuilderFactory.setGatewayContext(gatewayContext);
        return restfulSessionRequestBuilderFactory;
    }

    @Bean(name = "restfulSessionResponseBuilderFactory")
    @Autowired
    public RestfulSessionResponseBuilderFactory restfulSessionResponseBuilderFactory(GatewayContext gatewayContext) {
        RestfulSessionResponseBuilderFactory restfulSessionResponseBuilderFactory = new RestfulSessionResponseBuilderFactory();
        restfulSessionResponseBuilderFactory.setGatewayContext(gatewayContext);
        return restfulSessionResponseBuilderFactory;
    }

}
