package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.spring.builder.RestfulRequestBuilderFactory;
import com.ohayoyo.gateway.client.spring.builder.RestfulResponseBuilderFactory;
import com.ohayoyo.gateway.client.validator.ClientDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 蓝明乐
 */
@Configuration
public class SpringClientConfig {

    @Bean
    public RestfulResponseBuilderFactory restfulResponseBuilderFactory() {
        return new RestfulResponseBuilderFactory();
    }

    @Bean
    public RestfulRequestBuilderFactory restfulRequestBuilderFactory() {
        return new RestfulRequestBuilderFactory();
    }

    @Bean
    public GatewayDefineValidator gatewayDefineValidator() {
        return new ClientDefineValidator();
    }

    @Bean(name = "springGatewayChannel")
    public GatewayChannel springGatewayChannel(@Qualifier("springGatewayContainer") GatewayContainer gatewayContainer) {
        return new SpringClientChannel(gatewayContainer);
    }

    @Bean(name = "springGatewayContainer")
    public GatewayContainer springGatewayContainer() {
        return new SpringClientContainer();
    }

}
