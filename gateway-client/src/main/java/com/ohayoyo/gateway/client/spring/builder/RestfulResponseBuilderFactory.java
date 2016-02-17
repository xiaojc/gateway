package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

public class RestfulResponseBuilderFactory implements FactoryBean<RestfulResponseBuilder> {

    @Autowired(required = false)
    private ConversionService conversionService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public RestfulResponseBuilder getObject() throws Exception {
        if (null == conversionService) {
            try {
                conversionService = this.applicationContext.getBean(ConversionService.class);
            } catch (Exception ex) {
                //none
            }
            if (null == conversionService) {
                if (null == RestfulRequestBuilderFactory.notConfigConversionService) {
                    RestfulRequestBuilderFactory.notConfigConversionService = new DefaultFormattingConversionService();
                }
                conversionService = RestfulRequestBuilderFactory.notConfigConversionService;
            }
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
