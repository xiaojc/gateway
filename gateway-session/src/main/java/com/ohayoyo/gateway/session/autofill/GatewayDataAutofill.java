package com.ohayoyo.gateway.session.autofill;

import com.ohayoyo.gateway.session.core.GatewayAccessor;
import com.ohayoyo.gateway.define.http.GatewayRequest;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;

/**
 * @author 蓝明乐
 */
public interface GatewayDataAutofill extends GatewayAccessor {

    String CLIENT_AUTOFILL_SELECT_ENVIRONMENT = "gateway.ohayoyo.com";

    <RequestBody> void autofill(GatewayRequest request, GatewaySessionRequest gatewaySessionRequest);

}
