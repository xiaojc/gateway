package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.container.GatewayContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 蓝明乐
 */
@Configuration
public class SpringGatewayConfig {

    @Bean(name = "springGatewayChannel")
    public GatewayChannel springGatewayChannel(@Qualifier("springGatewayContainer") GatewayContainer gatewayContainer) {
        return new SpringGatewayChannel(gatewayContainer);
    }

    @Bean(name = "springGatewayContainer")
    public GatewayContainer springGatewayContainer() {
        return new SpringGatewayContainer();
    }

}
