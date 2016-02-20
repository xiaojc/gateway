package com.ohayoyo.gateway.session.core;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.http.client.GatewayHttpClient;
import com.ohayoyo.gateway.session.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.session.exception.GatewaySessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewaySession extends AbstractGatewayAccessor implements GatewaySession {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewaySession.class);

    @Override
    public final <ResponseBody, RequestBody> GatewaySessionResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) throws GatewaySessionException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(gatewayInterface);
        GatewayContext gatewayContext = this.getGatewayContext();
        if (ObjectUtils.isEmpty(gatewaySessionRequest)) {
            gatewaySessionRequest = gatewayContext.newRestfulRequestBuilder().build();
        }
        try {
            GatewayHttpClient IGatewayHttpClient = gatewayContext.getGatewayHttpClient();
            this.gatewayInterfaceVerify(gatewayInterface);
            this.gatewayDataAutofill(gatewayInterface, gatewaySessionRequest);
            this.gatewayDataVerify(gatewayInterface, gatewaySessionRequest);
            RestfulResponseBuilder restfulResponseBuilder = gatewayContext.newRestfulResponseBuilder();
            this.doSession(IGatewayHttpClient, restfulResponseBuilder, responseBodyClass, gatewayInterface, gatewaySessionRequest);
            GatewaySessionResponse<ResponseBody> gatewaySessionResponse = restfulResponseBuilder.build();
            this.gatewayResultVerify(gatewaySessionResponse, responseBodyClass, gatewayInterface);
            return gatewaySessionResponse;
        } catch (Exception ex) {
            throw new GatewaySessionException(ex.getMessage());
        }
    }

    protected abstract <ResponseBody> void gatewayResultVerify(GatewaySessionResponse<ResponseBody> gatewaySessionResponse, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface) throws GatewaySessionException;

    protected abstract <ResponseBody, RequestBody> void doSession(GatewayHttpClient iGatewayHttpClient, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) throws GatewaySessionException;

    protected abstract <RequestBody> void gatewayDataVerify(GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) throws GatewaySessionException;

    protected abstract <RequestBody> void gatewayDataAutofill(GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) throws GatewaySessionException;

    protected abstract void gatewayInterfaceVerify(GatewayInterface gatewayInterface) throws GatewaySessionException;

}
