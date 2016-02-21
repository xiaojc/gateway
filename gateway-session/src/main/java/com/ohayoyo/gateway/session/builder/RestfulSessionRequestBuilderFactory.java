package com.ohayoyo.gateway.session.builder;

import com.ohayoyo.gateway.session.core.GatewayContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 蓝明乐
 */
public class RestfulSessionRequestBuilderFactory extends AbstractGatewayRestfulBuilderFactory<RestfulSessionRequestBuilder> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulSessionRequestBuilderFactory.class);

    @Override
    public RestfulSessionRequestBuilder getObject() throws Exception {
        return new RestfulSessionRequestBuilder().setGatewayContext(this.getGatewayContext());
    }

    @Override
    public Class<?> getObjectType() {
        return RestfulSessionRequestBuilder.class;
    }

    @Autowired
    @Override
    public void setGatewayContext(GatewayContext gatewayContext) {
        super.setGatewayContext(gatewayContext);
    }

}
