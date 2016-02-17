package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

public class RestfulResponseBuilderFactory implements FactoryBean<RestfulResponseBuilder> {

    @Autowired(required = false)
    private ConversionService conversionService;

    @Override
    public RestfulResponseBuilder getObject() throws Exception {
        if (null == conversionService) {
            if (null == RestfulRequestBuilderFactory.notConfigConversionService) {
                RestfulRequestBuilderFactory.notConfigConversionService = new DefaultFormattingConversionService();
            }
            conversionService = RestfulRequestBuilderFactory.notConfigConversionService;
        }
        return RestfulResponseBuilder.newInstance().conversionService(conversionService);
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulResponseBuilder.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
