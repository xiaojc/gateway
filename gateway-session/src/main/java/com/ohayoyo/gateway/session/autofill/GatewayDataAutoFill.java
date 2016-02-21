package com.ohayoyo.gateway.session.autofill;

import com.ohayoyo.gateway.session.core.GatewayAccessor;
import com.ohayoyo.gateway.define.http.GatewayRequest;
import com.ohayoyo.gateway.session.core.SessionRequest;

/**
 * @author 蓝明乐
 */
public interface GatewayDataAutoFill extends GatewayAccessor {

    String GATEWAY_AUTO_FILL_SELECT_ENVIRONMENT = "gateway.ohayoyo.com";

    <RequestBody> void dataAutoFill(GatewayRequest request, SessionRequest<RequestBody> sessionRequest);

}
