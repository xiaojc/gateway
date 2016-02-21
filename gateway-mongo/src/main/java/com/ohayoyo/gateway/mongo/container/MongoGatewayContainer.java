package com.ohayoyo.gateway.mongo.container;

import com.ohayoyo.gateway.define.container.AbstractGatewayContainer;
import com.ohayoyo.gateway.define.support.ConfigurableGatewayContainer;
import com.ohayoyo.gateway.mongo.http.MongoGatewayInterface;
import com.ohayoyo.gateway.mongo.repository.MongoGatewayInterfaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

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
