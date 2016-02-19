package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.spring.builder.RestfulRequestBuilderFactory;
import com.ohayoyo.gateway.client.spring.builder.RestfulResponseBuilderFactory;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 蓝明乐
 */
@Configuration
public class SpringClientConfiguration {

    @Bean(name = "gatewayContainer")
    public GatewayContainer gatewayContainer() {
        return new ScanConfigurationContainerBean();
    }

    @Bean(name = "gatewayContext")
    public GatewayContext gatewayContext(@Qualifier("gatewayContainer") GatewayContainer gatewayContainer) {
        return new ConfigurableContextBean().setGatewayContainer(gatewayContainer);
    }

    @Bean(name = "gatewayChannel")
    public GatewayChannel gatewayChannel(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        return new ClientChannelBean().setGatewayContext(gatewayContext);
    }

    @Bean
    public RestfulResponseBuilderFactory restfulResponseBuilderFactory(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        return (RestfulResponseBuilderFactory) new RestfulResponseBuilderFactory().setGatewayContext(gatewayContext);
    }

    @Bean
    public RestfulRequestBuilderFactory restfulRequestBuilderFactory(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        return (RestfulRequestBuilderFactory) new RestfulRequestBuilderFactory().setGatewayContext(gatewayContext);
    }

}
