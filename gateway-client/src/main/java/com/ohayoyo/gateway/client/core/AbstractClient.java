package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public abstract class AbstractClient implements GatewayClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractClient.class);

    private GatewayContext gatewayContext;

    @Override
    public final <ResponseBody, RequestBody> GatewayResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(gatewayDefine);
        if (ObjectUtils.isEmpty(gatewayRequest)) {
            gatewayRequest = this.gatewayContext.newRestfulRequestBuilder().build();
        }
        try {
            HttpGateway httpGateway = this.gatewayContext.getHttpGateway();
            this.gatewayDefineVerify(gatewayDefine);
            this.gatewayAutofill(gatewayDefine, gatewayRequest);
            this.gatewayDataVerify(gatewayDefine, gatewayRequest);
            RestfulResponseBuilder restfulResponseBuilder = this.gatewayContext.newRestfulResponseBuilder();
            this.doSession(httpGateway, restfulResponseBuilder, responseBodyClass, gatewayDefine, gatewayRequest);
            GatewayResponse<ResponseBody> gatewayResponse = restfulResponseBuilder.build();
            this.gatewayResultVerify(gatewayResponse, responseBodyClass, gatewayDefine);
            return gatewayResponse;
        } catch (Exception ex) {
            throw new GatewayException(ex);
        }
    }

    protected abstract void gatewayDefineVerify(GatewayDefine gatewayDefine) throws GatewayException, IOException, HttpGatewayException;

    protected abstract <RequestBody> void gatewayDataVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <RequestBody> void gatewayAutofill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <ResponseBody, RequestBody> void doSession(HttpGateway httpGateway, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <ResponseBody> void gatewayResultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException;

    @Override
    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    @Override
    public AbstractClient setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
        return this;
    }

}
