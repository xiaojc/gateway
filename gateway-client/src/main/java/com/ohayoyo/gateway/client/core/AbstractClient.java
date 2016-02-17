package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public abstract class AbstractClient implements GatewayClient, ApplicationContextAware {

    private HttpGateway httpGateway;

    private ApplicationContext applicationContext;

    public AbstractClient(HttpGateway httpGateway) {
        Assert.notNull(httpGateway);
        this.httpGateway = httpGateway;
    }

    public HttpGateway getHttpGateway() {
        return httpGateway;
    }

    public AbstractClient setHttpGateway(HttpGateway httpGateway) {
        Assert.notNull(httpGateway);
        this.httpGateway = httpGateway;
        return this;
    }

    @Override
    public final <RequestBody> GatewayResponse<String> session(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException {
        return this.session(String.class, gatewayDefine, gatewayRequest);
    }

    @Override
    public final <ResponseBody, RequestBody> GatewayResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(gatewayDefine);
        HttpGateway httpGateway = this.getHttpGateway();
        this.gatewayDefineVerify(gatewayDefine);
        this.gatewayAutofill(gatewayDefine, gatewayRequest);
        this.gatewayDataVerify(gatewayDefine, gatewayRequest);
        RestfulResponseBuilder restfulResponseBuilder = autoSupportSpringRestfulResponseBuilder();
        this.doSession(httpGateway, restfulResponseBuilder, responseBodyClass, gatewayDefine, gatewayRequest);
        GatewayResponse<ResponseBody> gatewayResponse = restfulResponseBuilder.build();
        this.gatewayResultVerify(gatewayResponse, responseBodyClass, gatewayDefine);
        return gatewayResponse;
    }

    private RestfulResponseBuilder autoSupportSpringRestfulResponseBuilder() {
        RestfulResponseBuilder restfulResponseBuilder = null;
        if (null != this.applicationContext) {
            try {
                restfulResponseBuilder = this.applicationContext.getBean(RestfulResponseBuilder.class);
            } catch (Exception ex) {
                //none
            }
        }
        if (null == restfulResponseBuilder) {
            restfulResponseBuilder = RestfulResponseBuilder.newInstance();
        }
        return restfulResponseBuilder;
    }

    protected abstract void gatewayDefineVerify(GatewayDefine gatewayDefine) throws GatewayException, IOException, HttpGatewayException;

    protected abstract <RequestBody> void gatewayDataVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException;

    protected abstract <RequestBody> void gatewayAutofill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException;

    protected abstract <ResponseBody, RequestBody> void doSession(HttpGateway httpGateway, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException;

    protected abstract <ResponseBody> void gatewayResultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException, IOException, HttpGatewayException;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
