package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.GatewayContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.http.RequestDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayAutofill extends GatewayContextAccessor {

    String CLIENT_AUTOFILL_SELECT_ENVIRONMENT = "gateway.ohayoyo.com";

    <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest);

}
