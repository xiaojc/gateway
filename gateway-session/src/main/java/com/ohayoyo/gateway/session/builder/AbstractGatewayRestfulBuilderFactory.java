package com.ohayoyo.gateway.session.builder;

import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayRestfulBuilderFactory<Bean> extends AbstractGatewayAccessor implements FactoryBean<Bean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayRestfulBuilderFactory.class);

    @Override
    public boolean isSingleton() {
        return false;
    }

}
