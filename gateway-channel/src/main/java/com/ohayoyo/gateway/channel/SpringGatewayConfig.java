package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.container.GatewayContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringGatewayConfig {

    @Bean
    public GatewayChannel springGatewayChannel(@Qualifier("springGatewayContainer") GatewayContainer gatewayContainer) {
        return new SpringGatewayChannel(gatewayContainer);
    }

    @Bean
    public GatewayContainer springGatewayContainer() {
        return new SpringGatewayContainer();
    }

}
