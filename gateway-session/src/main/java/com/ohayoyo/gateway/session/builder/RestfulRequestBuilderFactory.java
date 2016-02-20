package com.ohayoyo.gateway.session.builder;

import com.ohayoyo.gateway.session.core.GatewayContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 蓝明乐
 */
public class RestfulRequestBuilderFactory extends AbstractGatewayRestfulBuilderFactory<RestfulRequestBuilder> {

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
