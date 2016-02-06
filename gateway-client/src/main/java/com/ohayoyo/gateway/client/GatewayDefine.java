package com.ohayoyo.gateway.client;

import com.ohayoyo.gateway.define.RequestDefine;
import com.ohayoyo.gateway.define.ResponseDefine;

public interface GatewayDefine {

    RequestDefine getRequest();

    ResponseDefine getResponse();

}
