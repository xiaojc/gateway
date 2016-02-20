package com.ohayoyo.gateway.session.builder;

import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayRestfulBuilderFactory<Bean> extends AbstractGatewayAccessor implements FactoryBean<Bean> {

    @Override
    public boolean isSingleton() {
        return false;
    }

}
