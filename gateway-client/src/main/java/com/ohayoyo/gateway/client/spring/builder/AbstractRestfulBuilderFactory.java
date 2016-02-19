package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.core.AbstractContextAccessor;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 蓝明乐
 */
public abstract class AbstractRestfulBuilderFactory<Bean> extends AbstractContextAccessor implements FactoryBean<Bean> {

    @Override
    public boolean isSingleton() {
        return false;
    }

}
