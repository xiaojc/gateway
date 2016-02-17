package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.autofill.ClientAutofill;
import com.ohayoyo.gateway.client.core.BehaviorClient;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.client.validator.ClientDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public class RestfulClient extends BehaviorClient {

    private static RestfulClient DEFAULT_RESTFUL_CLIENT = null;

    private static final Object LOCKED = new Object();

    public RestfulClient() {
        this(HttpGateway.DEFAULT_HTTP_GATEWAY);
    }

    public RestfulClient(HttpGateway httpGateway) {
        this(httpGateway, null, null);
    }

    public RestfulClient(HttpGateway httpGateway, GatewayDefineValidator gatewayDefineValidator, GatewayAutofill gatewayAutofill) {
        super(httpGateway, gatewayDefineValidator, gatewayAutofill);
    }

    public static final RestfulClient defaultRestfulClient() {
        if (ObjectUtils.isEmpty(DEFAULT_RESTFUL_CLIENT)) {
            synchronized (LOCKED) {
                if (ObjectUtils.isEmpty(DEFAULT_RESTFUL_CLIENT)) {
                    DEFAULT_RESTFUL_CLIENT = new RestfulClient(HttpGateway.DEFAULT_HTTP_GATEWAY, new ClientDefineValidator(), new ClientAutofill());
                }
            }
        }
        return DEFAULT_RESTFUL_CLIENT;
    }

    @Override
    protected <ResponseBody, RequestBody> void doSession(HttpGateway httpGateway, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException {
        RequestEntity<RequestBody> requestEntity = this.resolveRequestEntity(gatewayDefine, gatewayRequest);
        MediaType customRequestContentType = this.resolveCustomRequestContentType(gatewayDefine);
        MediaType customResponseContentType = this.resolveCustomResponseContentType(gatewayDefine);
        ResponseEntity<ResponseBody> responseEntity = httpGateway.handler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
        restfulResponseBuilder.statusCode(responseEntity.getStatusCode().value());
        restfulResponseBuilder.reasonPhrase(responseEntity.getStatusCode().getReasonPhrase());
        restfulResponseBuilder.responseHeaders(responseEntity.getHeaders());
        restfulResponseBuilder.responseBody(responseEntity.getBody());
    }

}
