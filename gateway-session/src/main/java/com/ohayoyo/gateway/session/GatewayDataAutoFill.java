package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayRequest;

/**
 * @author 蓝明乐
 */
public interface GatewayDataAutoFill extends GatewayAccessor {

    String GATEWAY_AUTO_FILL_SELECT_ENVIRONMENT = "gateway.ohayoyo.com";

    <RequestBody> void dataAutoFill(GatewayRequest request, SessionRequest<RequestBody> sessionRequest);

}
