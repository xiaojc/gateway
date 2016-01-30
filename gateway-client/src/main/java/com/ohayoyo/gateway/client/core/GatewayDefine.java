package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.define.core.RequestDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;

public interface GatewayDefine {

    RequestDefine getRequest();

    ResponseDefine getResponse();

}
