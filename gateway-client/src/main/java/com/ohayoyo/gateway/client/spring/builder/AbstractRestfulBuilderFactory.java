package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.spring.SpringClientHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.ObjectUtils;

public abstract class AbstractRestfulBuilderFactory<Bean> implements ApplicationContextAware, FactoryBean<Bean> {

    @Autowired(required = false)
    private ConversionService conversionService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    protected ConversionService configConversionService() {
        if (ObjectUtils.isEmpty(this.conversionService)) {
            try {
                this.conversionService = this.applicationContext.getBean(ConversionService.class);
            } catch (Exception ex) {
                //none
            }
            if (ObjectUtils.isEmpty(conversionService)) {
                this.conversionService = SpringClientHolder.globalConversionServiceHolder();
            }
        }
        return this.conversionService;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
