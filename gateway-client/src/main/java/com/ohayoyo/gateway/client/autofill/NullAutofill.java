package com.ohayoyo.gateway.client.autofill;


import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.http.RequestDefine;

/**
 * @author 蓝明乐
 */
public class NullAutofill extends AbstractAutofill {

    @Override
    public <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest) {

    }

}
