package com.ohayoyo.gateway.session;


import com.ohayoyo.gateway.define.GatewayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 蓝明乐
 */
public class NullDataAutoFill extends AbstractGatewayAccessor implements GatewayDataAutoFill {

    private static final Logger LOGGER = LoggerFactory.getLogger(NullDataAutoFill.class);

    @Override
    public <RequestBody> void dataAutoFill(GatewayRequest request, SessionRequest<RequestBody> sessionRequest) {

    }

}
