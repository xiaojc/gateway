package com.ohayoyo.gateway.client;

import com.ohayoyo.gateway.define.http.RequestDefine;
import com.ohayoyo.gateway.define.http.ResponseDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayDefine {

    RequestDefine getRequest();

    ResponseDefine getResponse();

}
