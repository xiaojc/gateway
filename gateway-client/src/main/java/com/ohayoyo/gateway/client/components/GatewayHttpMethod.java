package com.ohayoyo.gateway.client.components;


import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.utils.SelectDefineUtil;
import com.ohayoyo.gateway.define.core.MethodDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import org.springframework.http.HttpMethod;

import java.util.Set;

/**
 * HTTP方法组件
 */
public class GatewayHttpMethod extends AbstractGatewayComponent<HttpMethod> {

    /**
     * 获取HTTP方法组件
     *
     * @return 返回HTTP方法组件
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public HttpMethod getComponent() throws GatewayException {
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        String select = gatewayRequest.getSelect();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<MethodDefine> methodDefines = requestDefine.getMethods();
        MethodDefine methodDefine = SelectDefineUtil.selectMethodDefine(select, methodDefines);
        String name = methodDefine.getName();
        HttpMethod httpMethod = HttpMethod.resolve(name);
        return httpMethod;
    }

}
