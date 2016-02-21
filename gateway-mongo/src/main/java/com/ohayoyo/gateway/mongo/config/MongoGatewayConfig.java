package com.ohayoyo.gateway.mongo.config;

import com.ohayoyo.gateway.mongo.repository.MongoGatewayInterfaceRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = {
        MongoGatewayInterfaceRepository.class
})
public class MongoGatewayConfig {
}
