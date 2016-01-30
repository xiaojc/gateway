package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.*;
import com.ohayoyo.gateway.client.utils.PartUtil;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import java.net.URI;
import java.util.Set;

public class RestfulClient implements GatewayClient {

    private GatewayConfig gatewayConfig;

    private GatewayExecutor gatewayExecutor;

    public RestfulClient() {
        this(null, null);
    }

    public RestfulClient(GatewayConfig gatewayConfig) {
        this(gatewayConfig, null);
    }

    public RestfulClient(GatewayExecutor gatewayExecutor) {
        this(null, gatewayExecutor);
    }

    public RestfulClient(GatewayConfig gatewayConfig, GatewayExecutor gatewayExecutor) {
        this.gatewayConfig = gatewayConfig;
        this.gatewayExecutor = gatewayExecutor;
        if (null == this.gatewayConfig) {
            this.gatewayConfig = new RestfulConfig();
        }
        if (null == this.gatewayExecutor) {
            this.gatewayExecutor = new RestfulExecutor();
        }
    }

    @Override
    public GatewayResponse session(GatewayRequest gatewayRequest, GatewayDefine gatewayDefine) throws GatewayException {
        GatewayResponse gatewayResponse = null;
        GatewayConfig gatewayConfig = this.getGatewayConfig();
        Set<Class<? extends GatewayPart<?>>> gatewayPartClasses = gatewayConfig.getGatewayPartClasses();
        try {
            URI uri = PartUtil.newPartInstance(gatewayPartClasses, URI.class, gatewayConfig, gatewayRequest, gatewayDefine);
            HttpMethod httpMethod = PartUtil.newPartInstance(gatewayPartClasses, HttpMethod.class, gatewayConfig, gatewayRequest, gatewayDefine);
            RequestCallback requestCallback = PartUtil.newPartInstance(gatewayPartClasses, RequestCallback.class, gatewayConfig, gatewayRequest, gatewayDefine);
            ResponseExtractor<?> responseExtractor = PartUtil.newPartInstance(gatewayPartClasses, ResponseExtractor.class, gatewayConfig, gatewayRequest, gatewayDefine);
            Object result = gatewayExecutor.execute(uri, httpMethod, requestCallback, responseExtractor);
            if (null != result && result instanceof GatewayEntity) {
                GatewayWrapping gatewayWrapping = PartUtil.newPartInstance(gatewayPartClasses, GatewayWrapping.class, gatewayConfig, gatewayRequest, gatewayDefine);
                GatewayEntity gatewayEntity = (GatewayEntity) result;
                gatewayWrapping.wrap(gatewayEntity, gatewayConfig);
            }
        } catch (Exception ex) {
            throw new GatewayException(ex);
        }
        return gatewayResponse;
    }

    @Override
    public GatewayConfig getGatewayConfig() {
        return gatewayConfig;
    }

    @Override
    public RestfulClient setGatewayConfig(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
        return this;
    }

    @Override
    public GatewayExecutor getGatewayExecutor() {
        return gatewayExecutor;
    }

    @Override
    public RestfulClient setGatewayExecutor(GatewayExecutor gatewayExecutor) {
        this.gatewayExecutor = gatewayExecutor;
        return this;
    }


}
