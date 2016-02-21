package com.ohayoyo.gateway.channel.support.config;

import com.ohayoyo.gateway.channel.core.GatewayChannel;
import com.ohayoyo.gateway.channel.support.ScanMemoryGatewayChannel;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.memory.support.AutoScanMemoryGatewayContainer;
import com.ohayoyo.gateway.session.builder.RestfulSessionRequestBuilderFactory;
import com.ohayoyo.gateway.session.builder.RestfulSessionResponseBuilderFactory;
import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.support.AutoScanGatewayContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMemoryGatewayChannel {

    @Bean(name = "gatewayContainer")
    public GatewayContainer gatewayContainer() {
        return new AutoScanMemoryGatewayContainer();
    }

    @Bean(name = "gatewayContext")
    public GatewayContext gatewayContext(@Qualifier("gatewayContainer") GatewayContainer gatewayContainer) {
        AutoScanGatewayContext autoScanGatewayContext = new AutoScanGatewayContext();
        autoScanGatewayContext.setGatewayContainer(gatewayContainer);
        return autoScanGatewayContext;
    }

    @Bean(name = "gatewayChannel")
    public GatewayChannel gatewayChannel(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        ScanMemoryGatewayChannel scanMemoryChannel = new ScanMemoryGatewayChannel();
        scanMemoryChannel.setGatewayContext(gatewayContext);
        return scanMemoryChannel;
    }

    @Bean(name = "restfulSessionRequestBuilderFactory")
    public RestfulSessionRequestBuilderFactory restfulSessionRequestBuilderFactory(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        RestfulSessionRequestBuilderFactory restfulSessionRequestBuilderFactory = new RestfulSessionRequestBuilderFactory();
        restfulSessionRequestBuilderFactory.setGatewayContext(gatewayContext);
        return restfulSessionRequestBuilderFactory;
    }

    @Bean(name = "restfulSessionResponseBuilderFactory")
    public RestfulSessionResponseBuilderFactory restfulSessionResponseBuilderFactory(@Qualifier("gatewayContext") GatewayContext gatewayContex) {
        RestfulSessionResponseBuilderFactory restfulSessionResponseBuilderFactory = new RestfulSessionResponseBuilderFactory();
        restfulSessionResponseBuilderFactory.setGatewayContext(gatewayContex);
        return restfulSessionResponseBuilderFactory;
    }

}
