package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.core.RequestDefine;

public interface GatewayAutofill {

    <RequestBody> void autofill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest);

}
