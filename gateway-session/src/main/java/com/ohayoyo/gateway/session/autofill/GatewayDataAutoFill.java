package com.ohayoyo.gateway.session.autofill;

import com.ohayoyo.gateway.session.core.GatewayAccessor;
import com.ohayoyo.gateway.define.http.GatewayRequest;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;

/**
 * @author 蓝明乐
 */
public interface GatewayDataAutoFill extends GatewayAccessor {

    String GATEWAY_AUTO_FILL_SELECT_ENVIRONMENT = "gateway.ohayoyo.com";

    <RequestBody> void dataAutoFill(GatewayRequest request, GatewaySessionRequest<RequestBody> gatewaySessionRequest);

}
