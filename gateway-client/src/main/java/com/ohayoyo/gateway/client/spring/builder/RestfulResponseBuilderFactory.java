package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;

public class RestfulResponseBuilderFactory extends AbstractRestfulBuilderFactory<RestfulResponseBuilder> {

    @Override
    public RestfulResponseBuilder getObject() throws Exception {
        return RestfulResponseBuilder.newInstance().conversionService(this.configConversionService());
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulResponseBuilder.class;
    }

}
