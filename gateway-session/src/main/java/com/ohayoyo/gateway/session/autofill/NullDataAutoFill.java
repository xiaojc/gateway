package com.ohayoyo.gateway.session.autofill;


import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.define.http.GatewayRequest;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;

/**
 * @author 蓝明乐
 */
public class NullDataAutoFill extends AbstractGatewayAccessor implements GatewayDataAutoFill {

    @Override
    public <RequestBody> void dataAutoFill(GatewayRequest request, GatewaySessionRequest<RequestBody> gatewaySessionRequest) {

    }

}
