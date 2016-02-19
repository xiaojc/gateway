package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 蓝明乐
 */
public class RestfulRequestBuilderFactory extends AbstractRestfulBuilderFactory<RestfulRequestBuilder> {

    @Override
    public RestfulRequestBuilder getObject() throws Exception {
        return new RestfulRequestBuilder().setGatewayContext(this.getGatewayContext());
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulRequestBuilder.class;
    }

    @Autowired
    @Override
    public void setGatewayContext(GatewayContext gatewayContext) {
        super.setGatewayContext(gatewayContext);
    }

}
