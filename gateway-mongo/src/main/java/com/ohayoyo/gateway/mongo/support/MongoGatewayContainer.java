package com.ohayoyo.gateway.mongo.support;

import com.ohayoyo.gateway.define.AbstractGatewayContainer;
import com.ohayoyo.gateway.define.ConfigurableGatewayContainer;
import com.ohayoyo.gateway.mongo.model.MongoGatewayInterface;
import com.ohayoyo.gateway.mongo.repository.MongoGatewayInterfaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ObjectUtils;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = {
        MongoGatewayInterfaceRepository.class
})
public class MongoGatewayContainer extends AbstractGatewayContainer<MongoGatewayInterface> implements ConfigurableGatewayContainer<MongoGatewayInterface> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoGatewayContainer.class);

    private ApplicationContext applicationContext;

    @Autowired
    private MongoGatewayInterfaceRepository mongoGatewayInterfaceRepository;

    @Override
    public MongoGatewayInterface query(String key) {
        return mongoGatewayInterfaceRepository.findByKey(key);
    }

    @Override
    public void save(MongoGatewayInterface anInterface) {
        mongoGatewayInterfaceRepository.save(anInterface);
    }

    @Override
    public void remove(MongoGatewayInterface anInterface) {
        mongoGatewayInterfaceRepository.delete(anInterface);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (ObjectUtils.isEmpty(mongoGatewayInterfaceRepository)) {
            throw new Exception("没有配置MongoGatewayInterfaceRepository.");
        }
    }
}
