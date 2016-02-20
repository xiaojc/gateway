package com.ohayoyo.gateway.channel.support.configuration;

import com.ohayoyo.gateway.channel.core.GatewayChannel;
import com.ohayoyo.gateway.channel.support.ScanMemoryGatewayChannel;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.memory.support.AutoScanMemoryGatewayContainer;
import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.support.AutoScanGatewayContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryGatewayChannelConfiguration {

    @Bean
    public GatewayContainer container() {
        AutoScanMemoryGatewayContainer autoScanMemoryGatewayContainer = new AutoScanMemoryGatewayContainer();
        return autoScanMemoryGatewayContainer;
    }

    @Bean(name = "gatewayContext")
    public GatewayContext gatewayContext() {
        AutoScanGatewayContext autoScanGatewayContext = new AutoScanGatewayContext();
        return autoScanGatewayContext;
    }

    @Bean
    public GatewayChannel gatewayChannel(@Qualifier("gatewayContext") GatewayContext gatewayContext) {
        ScanMemoryGatewayChannel scanMemoryChannel = new ScanMemoryGatewayChannel();
        scanMemoryChannel.setGatewayContext(gatewayContext);
        return scanMemoryChannel;
    }

}
