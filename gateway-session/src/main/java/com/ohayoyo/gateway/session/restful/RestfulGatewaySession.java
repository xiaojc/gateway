package com.ohayoyo.gateway.session.restful;


import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.http.client.GatewayHttpClient;
import com.ohayoyo.gateway.http.exception.GatewayHttpException;
import com.ohayoyo.gateway.session.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.session.core.AbstractBehaviorSession;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;
import com.ohayoyo.gateway.session.exception.GatewaySessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author 蓝明乐
 */
public class RestfulGatewaySession extends AbstractBehaviorSession {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestfulGatewaySession.class);

    @Override
    protected <ResponseBody, RequestBody> void doSession(GatewayHttpClient gatewayHttpClient, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) throws GatewaySessionException {
        try {
            RequestEntity<RequestBody> requestEntity = this.resolveRequestEntity(gatewayInterface, gatewaySessionRequest);
            MediaType customRequestContentType = this.resolveCustomRequestContentType(gatewayInterface);
            MediaType customResponseContentType = this.resolveCustomResponseContentType(gatewayInterface);
            ResponseEntity<ResponseBody> responseEntity = gatewayHttpClient.handler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
            restfulResponseBuilder.statusCode(responseEntity.getStatusCode().value());
            restfulResponseBuilder.reasonPhrase(responseEntity.getStatusCode().getReasonPhrase());
            restfulResponseBuilder.responseHeaders(responseEntity.getHeaders());
            restfulResponseBuilder.responseBody(responseEntity.getBody());
        } catch (GatewayHttpException ex) {
            throw new GatewaySessionException(ex);
        }
    }

}
