package com.ohayoyo.gateway.channel.config;

import com.ohayoyo.gateway.channel.support.AutoScanGatewayChannel;
import com.ohayoyo.gateway.mongo.support.MongoGatewayContainer;
import com.ohayoyo.gateway.session.support.AutoScanGatewayContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MongoGatewayContainer.class,
        AutoScanGatewayContext.class,
        AutoScanGatewayChannel.class,
        RestfulBuilderFactoryConfig.class
})
public class MongoGatewayChannelConfig {
}
