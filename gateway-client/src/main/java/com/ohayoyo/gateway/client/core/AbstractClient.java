package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public abstract class AbstractClient implements GatewayClient, ApplicationContextAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractClient.class);

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
    public final <RequestBody> GatewayResponse<String> session(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        return this.session(String.class, gatewayDefine, gatewayRequest);
    }

    @Override
    public final <ResponseBody, RequestBody> GatewayResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(gatewayDefine);

        if (ObjectUtils.isEmpty(gatewayRequest)) {
            gatewayRequest = autoSupportSpringRestfulRequestBuilder().build();
        }
        try {
            HttpGateway httpGateway = this.getHttpGateway();
            LOGGER.debug("网关客户端进行定义验证 .");
            this.gatewayDefineVerify(gatewayDefine);
            LOGGER.debug("网关客户端进行自动填充 .");
            this.gatewayAutofill(gatewayDefine, gatewayRequest);
            LOGGER.debug("网关客户端进行数据验证 .");
            this.gatewayDataVerify(gatewayDefine, gatewayRequest);
            LOGGER.debug("构建响应实体构建器 .");
            RestfulResponseBuilder restfulResponseBuilder = autoSupportSpringRestfulResponseBuilder();
            LOGGER.debug("网关客户端进行实在的会话 .");
            this.doSession(httpGateway, restfulResponseBuilder, responseBodyClass, gatewayDefine, gatewayRequest);
            LOGGER.debug("网关客户端这次会话结束 .");
            GatewayResponse<ResponseBody> gatewayResponse = restfulResponseBuilder.build();
            LOGGER.debug("网关客户端进行结果验证 .");
            this.gatewayResultVerify(gatewayResponse, responseBodyClass, gatewayDefine);
            return gatewayResponse;
        } catch (Exception ex) {
            throw new GatewayException(ex);
        }
    }

    private RestfulRequestBuilder autoSupportSpringRestfulRequestBuilder() {
        RestfulRequestBuilder restfulRequestBuilder = null;
        if (!ObjectUtils.isEmpty(this.applicationContext)) {
            try {
                restfulRequestBuilder = this.applicationContext.getBean(RestfulRequestBuilder.class);
            } catch (Exception ex) {
                LOGGER.info("向spring容器获取 RestfulRequestBuilder 异常 : {} .", ex.getMessage());
            }
        }
        if (ObjectUtils.isEmpty(applicationContext)) {
            restfulRequestBuilder = RestfulRequestBuilder.newInstance();
        }
        return restfulRequestBuilder;
    }

    private RestfulResponseBuilder autoSupportSpringRestfulResponseBuilder() {
        RestfulResponseBuilder restfulResponseBuilder = null;
        if (!ObjectUtils.isEmpty(this.applicationContext)) {
            try {
                restfulResponseBuilder = this.applicationContext.getBean(RestfulResponseBuilder.class);
            } catch (Exception ex) {
                LOGGER.info("向spring容器获取 RestfulResponseBuilder 异常 : {} .", ex.getMessage());
            }
        }
        if (ObjectUtils.isEmpty(restfulResponseBuilder)) {
            restfulResponseBuilder = RestfulResponseBuilder.newInstance();
        }
        return restfulResponseBuilder;
    }

    protected abstract void gatewayDefineVerify(GatewayDefine gatewayDefine) throws GatewayException, IOException, HttpGatewayException;

    protected abstract <RequestBody> void gatewayDataVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <RequestBody> void gatewayAutofill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <ResponseBody, RequestBody> void doSession(HttpGateway httpGateway, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    protected abstract <ResponseBody> void gatewayResultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
