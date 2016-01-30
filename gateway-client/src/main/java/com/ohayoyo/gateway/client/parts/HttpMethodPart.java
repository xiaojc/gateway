package com.ohayoyo.gateway.client.parts;


import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.utils.SelectDefineUtil;
import com.ohayoyo.gateway.define.core.MethodDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.util.Set;

public class HttpMethodPart extends AbstractGatewayPart<HttpMethod> {

    @Override
    public HttpMethod getPart() throws GatewayException {

        GatewayConfig gatewayConfig = this.getGatewayConfig();
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        ClientHttpRequest clientHttpRequest = this.getClientHttpRequest();
        ClientHttpResponse clientHttpResponse = this.getClientHttpResponse();

        String select = gatewayRequest.getSelect();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<MethodDefine> methodDefines = requestDefine.getMethods();
        MethodDefine methodDefine = SelectDefineUtil.selectMethodDefine(select, methodDefines);
        String name = methodDefine.getName();
        HttpMethod httpMethod = HttpMethod.resolve(name);
        return httpMethod;
    }

}
