package com.ohayoyo.gateway.session.builder;

import com.ohayoyo.gateway.session.core.GatewayContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 蓝明乐
 */
public class RestfulResponseBuilderFactory extends AbstractGatewayRestfulBuilderFactory<RestfulResponseBuilder> {

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
