package com.ohayoyo.gateway.session.autofill;


import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.define.http.GatewayRequest;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;

/**
 * @author 蓝明乐
 */
public class NullDataAutofill extends AbstractGatewayAccessor implements GatewayDataAutofill {

    @Override
    public <RequestBody> void autofill(GatewayRequest request, GatewaySessionRequest gatewaySessionRequest) {

    }

}
