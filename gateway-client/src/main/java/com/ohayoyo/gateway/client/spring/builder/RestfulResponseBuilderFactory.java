package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 蓝明乐
 */
public class RestfulResponseBuilderFactory extends AbstractRestfulBuilderFactory<RestfulResponseBuilder> {

    @Override
    public RestfulResponseBuilder getObject() throws Exception {
        return new RestfulResponseBuilder().setGatewayContext(this.getGatewayContext());
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulResponseBuilder.class;
    }

    @Autowired
    @Override
    public void setGatewayContext(GatewayContext gatewayContext) {
        super.setGatewayContext(gatewayContext);
    }

}
