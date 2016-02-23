package com.ohayoyo.gateway.session;


import com.ohayoyo.gateway.define.GatewayInterface;
import com.ohayoyo.gateway.http.GatewayHttpClient;
import com.ohayoyo.gateway.http.GatewayHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author 蓝明乐
 */
public class RestfulGatewaySession extends AbstractBehaviorSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulGatewaySession.class);

    @Override
    protected <ResponseBody, RequestBody> void doSession(GatewayHttpClient gatewayHttpClient, RestfulSessionResponseBuilder restfulSessionResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException {
        try {
            RequestEntity<RequestBody> requestEntity = this.resolveRequestEntity(gatewayInterface, sessionRequest);
            MediaType customRequestContentType = this.resolveCustomRequestContentType(gatewayInterface);
            MediaType customResponseContentType = this.resolveCustomResponseContentType(gatewayInterface);
            ResponseEntity<ResponseBody> responseEntity = gatewayHttpClient.handler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
            restfulSessionResponseBuilder.statusCode(responseEntity.getStatusCode().value());
            restfulSessionResponseBuilder.reasonPhrase(responseEntity.getStatusCode().getReasonPhrase());
            restfulSessionResponseBuilder.responseHeaders(responseEntity.getHeaders());
            restfulSessionResponseBuilder.responseBody(responseEntity.getBody());
        } catch (GatewayHttpException ex) {
            throw new GatewaySessionException(ex);
        }
    }

}
