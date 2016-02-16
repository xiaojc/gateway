package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.GatewayDefine;
import com.ohayoyo.gateway.client.GatewayException;
import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.GatewayResponse;
import com.ohayoyo.gateway.client.utils.PathDefineUtil;
import com.ohayoyo.gateway.client.utils.SelectDefineUtil;
import com.ohayoyo.gateway.define.http.*;
import com.ohayoyo.gateway.http.GatewayHttpClientHandler;
import com.ohayoyo.gateway.http.GatewayRequestEntityBuilder;
import com.ohayoyo.gateway.http.HttpClientException;
import com.ohayoyo.gateway.http.HttpClientHandler;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class RestfulGatewayClient extends AbstractGatewayClient {

    private static RestfulGatewayClient DEFAULT_GATEWAY_CLIENT = null;

    private static final Object LOCKED = new Object();

    public RestfulGatewayClient() {
        super(GatewayHttpClientHandler.getDefaultHttpClientHandler());
    }

    public static final RestfulGatewayClient getDefaultGatewayClient() {
        if (ObjectUtils.isEmpty(DEFAULT_GATEWAY_CLIENT)) {
            synchronized (LOCKED) {
                if (ObjectUtils.isEmpty(DEFAULT_GATEWAY_CLIENT)) {
                    DEFAULT_GATEWAY_CLIENT = new RestfulGatewayClient();
                }
            }
        }
        return DEFAULT_GATEWAY_CLIENT;
    }

    public RestfulGatewayRequestBuilder newRestfulGatewayRequestBuilder() {
        return RestfulGatewayRequestBuilder.newInstance().conversionService(this.getHttpClientHandler().getConversionService());
    }

    public RestfulGatewayResponseBuilder newRestfulGatewayResponseBuilder() {
        return RestfulGatewayResponseBuilder.newInstance().conversionService(this.getHttpClientHandler().getConversionService());
    }

    @Override
    protected void defineVerify(GatewayDefine gatewayDefine) throws GatewayException {
    }

    @Override
    protected <RequestBody> void requestVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
    }

    @Override
    protected <RequestBody> void requestFill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
    }

    @Override
    protected <ResponseBody, RequestBody> void doSession(HttpClientHandler httpClientHandler, RestfulGatewayResponseBuilder restfulGatewayResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        RequestEntity<RequestBody> requestEntity = this.resolveRequestEntity(gatewayDefine, gatewayRequest);
        MediaType customRequestContentType = this.resolveCustomRequestContentType(gatewayDefine);
        MediaType customResponseContentType = this.resolveCustomResponseContentType(gatewayDefine);
        try {
            ResponseEntity<ResponseBody> responseEntity = httpClientHandler.handler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
            restfulGatewayResponseBuilder.statusCode(responseEntity.getStatusCode().value());
            restfulGatewayResponseBuilder.reasonPhrase(responseEntity.getStatusCode().getReasonPhrase());
            restfulGatewayResponseBuilder.responseHeaders(responseEntity.getHeaders());
            restfulGatewayResponseBuilder.responseBody(responseEntity.getBody());
        } catch (HttpClientException hce) {
            throw new GatewayException(hce);
        }
    }

    protected <RequestBody> RequestEntity<RequestBody> resolveRequestEntity(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        URI uri = this.resolveRequestUri(gatewayDefine, gatewayRequest);
        HttpMethod httpMethod = this.resolveRequestHttpMethod(gatewayDefine, gatewayRequest);
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        RequestBody requestEntity = gatewayRequest.getRequestBody();
        return (RequestEntity<RequestBody>) GatewayRequestEntityBuilder.newInstance().url(uri).headers(requestHeaders).httpMethod(httpMethod).body(requestEntity).build();
    }

    protected <RequestBody> URI resolveRequestUri(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        String select = gatewayRequest.getSelect();
        Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        Set<HostDefine> hostDefines = requestDefine.getHosts();
        PathDefine pathDefine = requestDefine.getPath();
        String fragment = requestDefine.getFragment();
        ProtocolDefine protocolDefine = SelectDefineUtil.selectProtocolDefine(select, protocolDefines);
        HostDefine hostDefine = SelectDefineUtil.selectHostDefine(select, hostDefines);
        String scheme = protocolDefine.getName();
        String host = hostDefine.getHostname();
        int port = SelectDefineUtil.selectHostDefinePort(protocolDefine, hostDefine);
        String[] pathSegments = PathDefineUtil.pathSegments(pathDefine);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        uriComponentsBuilder.scheme(scheme);
        uriComponentsBuilder.host(host);
        uriComponentsBuilder.port(port);
        if (!ObjectUtils.isEmpty(pathSegments)) {
            uriComponentsBuilder.pathSegment(pathSegments);
        }
        if (!StringUtils.isEmpty(fragment)) {
            uriComponentsBuilder.fragment(fragment);
        }
        if (!CollectionUtils.isEmpty(requestQueries)) {
            uriComponentsBuilder.queryParams(requestQueries);
        }
        UriComponents uriComponents;
        if (!CollectionUtils.isEmpty(requestPathVariables)) {
            uriComponents = uriComponentsBuilder.buildAndExpand(requestPathVariables);
        } else {
            uriComponents = uriComponentsBuilder.build();
        }
        URI uri = uriComponents.encode().toUri();
        return uri;
    }

    protected <RequestBody> HttpMethod resolveRequestHttpMethod(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        String select = gatewayRequest.getSelect();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<MethodDefine> methodDefines = requestDefine.getMethods();
        MethodDefine methodDefine = SelectDefineUtil.selectMethodDefine(select, methodDefines);
        String name = methodDefine.getName();
        HttpMethod httpMethod = HttpMethod.resolve(name);
        return httpMethod;
    }

    protected MediaType resolveEntityDefineContentType(EntityDefine entityDefine) {
        MediaType customContentType = null;
        if (null != entityDefine) {
            String contentType = entityDefine.getContentType();
            if (!StringUtils.isEmpty(contentType)) {
                try {
                    customContentType = MediaType.parseMediaType(contentType);
                } catch (Exception ex) {
                    customContentType = null;
                }
            }
        }
        return customContentType;
    }

    protected MediaType resolveCustomRequestContentType(GatewayDefine gatewayDefine) {
        RequestDefine requestDefine = gatewayDefine.getRequest();
        MediaType customRequestContentType = null;
        if (null != requestDefine) {
            EntityDefine entityDefine = requestDefine.getEntity();
            customRequestContentType = resolveEntityDefineContentType(entityDefine);
        }
        return customRequestContentType;
    }

    protected MediaType resolveCustomResponseContentType(GatewayDefine gatewayDefine) {
        ResponseDefine responseDefine = gatewayDefine.getResponse();
        MediaType customResponseContentType = null;
        if (null != responseDefine) {
            EntityDefine entityDefine = responseDefine.getEntity();
            customResponseContentType = resolveEntityDefineContentType(entityDefine);
        }
        return customResponseContentType;
    }

    @Override
    protected <ResponseBody> void resultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException {

    }
}
