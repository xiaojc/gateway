package com.ohayoyo.gateway.session.core;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.http.client.GatewayHttpClient;
import com.ohayoyo.gateway.session.builder.RestfulSessionResponseBuilder;
import com.ohayoyo.gateway.session.exception.GatewaySessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewaySession extends AbstractGatewayAccessor implements GatewaySession {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewaySession.class);

    @Override
    public final <ResponseBody, RequestBody> SessionResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException {
        Assert.notNull(responseBodyClass);
        Assert.notNull(gatewayInterface);
        GatewayContext gatewayContext = this.getGatewayContext();
        if (ObjectUtils.isEmpty(sessionRequest)) {
            sessionRequest = gatewayContext.newRestfulRequestBuilder().build();
        }
        try {
            GatewayHttpClient gatewayHttpClient = gatewayContext.getGatewayHttpClient();
            this.gatewayInterfaceVerify(gatewayInterface);
            this.gatewayDataAutofill(gatewayInterface, sessionRequest);
            this.gatewayDataVerify(gatewayInterface, sessionRequest);
            RestfulSessionResponseBuilder restfulSessionResponseBuilder = gatewayContext.newRestfulResponseBuilder();
            this.doSession(gatewayHttpClient, restfulSessionResponseBuilder, responseBodyClass, gatewayInterface, sessionRequest);
            SessionResponse<ResponseBody> sessionResponse = restfulSessionResponseBuilder.build();
            this.gatewayResultVerify(sessionResponse, responseBodyClass, gatewayInterface);
            return sessionResponse;
        } catch (Exception ex) {
            throw new GatewaySessionException(ex.getMessage());
        }
    }

    protected abstract <ResponseBody> void gatewayResultVerify(SessionResponse<ResponseBody> sessionResponse, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface) throws GatewaySessionException;

    protected abstract <ResponseBody, RequestBody> void doSession(GatewayHttpClient iGatewayHttpClient, RestfulSessionResponseBuilder restfulSessionResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException;

    protected abstract <RequestBody> void gatewayDataVerify(GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException;

    protected abstract <RequestBody> void gatewayDataAutofill(GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException;

    protected abstract void gatewayInterfaceVerify(GatewayInterface gatewayInterface) throws GatewaySessionException;

}
