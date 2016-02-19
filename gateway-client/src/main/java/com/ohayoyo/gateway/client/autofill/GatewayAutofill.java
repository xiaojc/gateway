package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.http.RequestDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayAutofill {

    String CLIENT_AUTOFILL_SELECT_ENVIRONMENT = "gateway.ohayoyo.com";

    GatewayContext getGatewayContext();

    GatewayAutofill setGatewayContext(GatewayContext gatewayContext);

    <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest);

}
