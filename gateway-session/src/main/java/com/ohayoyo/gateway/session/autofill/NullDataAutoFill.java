package com.ohayoyo.gateway.session.autofill;


import com.ohayoyo.gateway.define.http.GatewayRequest;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.SessionRequest;
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
