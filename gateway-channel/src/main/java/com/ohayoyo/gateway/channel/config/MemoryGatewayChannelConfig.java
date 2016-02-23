package com.ohayoyo.gateway.channel.config;

import com.ohayoyo.gateway.channel.support.AutoScanGatewayChannel;
import com.ohayoyo.gateway.memory.support.MemoryGatewayContainer;
import com.ohayoyo.gateway.session.support.AutoScanGatewayContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MemoryGatewayContainer.class,
        AutoScanGatewayContext.class,
        AutoScanGatewayChannel.class,
        RestfulBuilderFactoryConfig.class
})
public class MemoryGatewayChannelConfig {
}
