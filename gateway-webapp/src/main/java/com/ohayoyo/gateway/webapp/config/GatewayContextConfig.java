package com.ohayoyo.gateway.webapp.config;

import com.ohayoyo.gateway.channel.config.MongoGatewayChannelConfig;
import com.ohayoyo.spring.config.mongo.SimpleMongoConfig;
import com.ohayoyo.spring.config.profile.ProfileConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ProfileConfig.class,
        SimpleMongoConfig.class,
        MongoGatewayChannelConfig.class
})
public class GatewayContextConfig {
}
