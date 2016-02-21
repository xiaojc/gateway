package com.ohayoyo.gateway.session.builder;

import com.ohayoyo.gateway.session.core.GatewayContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 蓝明乐
 */
public class RestfulSessionResponseBuilderFactory extends AbstractGatewayRestfulBuilderFactory<RestfulSessionResponseBuilder> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulSessionResponseBuilderFactory.class);

    @Override
    public RestfulSessionResponseBuilder getObject() throws Exception {
        return new RestfulSessionResponseBuilder().setGatewayContext(this.getGatewayContext());
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulSessionResponseBuilder.class;
    }

    @Autowired
    @Override
    public void setGatewayContext(GatewayContext gatewayContext) {
        super.setGatewayContext(gatewayContext);
    }

}
