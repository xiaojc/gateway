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
public class BeanClientConfiguration {

    @Bean(name = "gatewayContainer")
    public GatewayContainer gatewayContainer() {
        return new ScanConfigurationContainer();
    }

    @Bean(name = "gatewayContext")
    public GatewayContext gatewayContext() {
        return new ScanConfigurableContext();
    }

    @Bean(name = "gatewayChannel")
    public GatewayChannel gatewayChannel(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        ScanConfigurationChannel scanConfigurationChannel = new ScanConfigurationChannel();
        scanConfigurationChannel.setGatewayContext(gatewayContext);
        return scanConfigurationChannel;
    }

    @Bean(name = "restfulResponseBuilderFactory")
    public RestfulResponseBuilderFactory restfulResponseBuilderFactory(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        return new RestfulResponseBuilderFactory();
    }

    @Bean(name = "restfulRequestBuilderFactory")
    public RestfulRequestBuilderFactory restfulRequestBuilderFactory(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        return  new RestfulRequestBuilderFactory() ;
    }

}
