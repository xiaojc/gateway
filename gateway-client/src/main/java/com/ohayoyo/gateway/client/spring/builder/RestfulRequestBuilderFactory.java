package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;

public class RestfulRequestBuilderFactory extends AbstractRestfulBuilderFactory<RestfulRequestBuilder> {

    @Override
    public RestfulRequestBuilder getObject() throws Exception {
        return RestfulRequestBuilder.newInstance().conversionService(this.configConversionService());
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulRequestBuilder.class;
    }

}
