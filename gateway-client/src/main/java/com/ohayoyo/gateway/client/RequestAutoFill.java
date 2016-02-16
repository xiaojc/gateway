package com.ohayoyo.gateway.client;

import com.ohayoyo.gateway.define.http.RequestDefine;

public interface RequestAutoFill {

    <RequestBody> void autoFill(RequestDefine requestDefine, GatewayRequest<RequestBody> gatewayRequest);

}
