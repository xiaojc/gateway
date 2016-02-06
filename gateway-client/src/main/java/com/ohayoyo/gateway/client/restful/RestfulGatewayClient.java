package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.GatewayDefine;
import com.ohayoyo.gateway.client.GatewayException;
import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.GatewayResponse;
import com.ohayoyo.gateway.client.utils.PathDefineUtil;
import com.ohayoyo.gateway.client.utils.SelectDefineUtil;
import com.ohayoyo.gateway.define.*;
import com.ohayoyo.gateway.http.DefaultRequestEntityBuilder;
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

public class RestfulGatewayClient extends AbstractGatewayClient {


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
    protected <ResponseBody, RequestBody> void doSession(HttpClientHandler httpClientHandler, GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        RequestEntity<RequestBody> requestEntity = this.resolveRequestEntity(gatewayDefine, gatewayRequest);
        MediaType customRequestContentType = this.resolveCustomRequestContentType(gatewayDefine);
        MediaType customResponseContentType = this.resolveCustomResponseContentType(gatewayDefine);
        try {
            ResponseEntity<ResponseBody> responseEntity = httpClientHandler.handler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
            gatewayResponse.setStatusCode(responseEntity.getStatusCode().value());
            gatewayResponse.setReasonPhrase(responseEntity.getStatusCode().getReasonPhrase());
            gatewayResponse.setResponseHeaders(responseEntity.getHeaders());
            gatewayResponse.setResponseBody(responseEntity.getBody());
        } catch (HttpClientException hce) {
            throw new GatewayException(hce);
        }
    }

    protected <RequestBody> RequestEntity<RequestBody> resolveRequestEntity(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        URI uri = this.resolveRequestUri(gatewayDefine, gatewayRequest);
        HttpMethod httpMethod = this.resolveRequestHttpMethod(gatewayDefine, gatewayRequest);
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        RequestBody requestEntity = gatewayRequest.getRequestBody();
        return new DefaultRequestEntityBuilder<RequestBody>().url(uri).headers(requestHeaders).httpMethod(httpMethod).body(requestEntity).build();
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

    protected MediaType resolveCustomRequestContentType(GatewayDefine gatewayDefine) {
        MediaType customRequestContentType = null;
        RequestDefine requestDefine = gatewayDefine.getRequest();
        if (null != requestDefine) {
            EntityDefine entityDefine = requestDefine.getEntity();
            if (null != entityDefine) {
                String contentType = entityDefine.getType();
                if (!StringUtils.isEmpty(contentType)) {
                    try {
                        customRequestContentType = MediaType.parseMediaType(contentType);
                    } catch (Exception ex) {
                        customRequestContentType = null;
                    }
                }
            }
        }
        return customRequestContentType;
    }

    protected MediaType resolveCustomResponseContentType(GatewayDefine gatewayDefine) {
        MediaType customResponseContentType = null;
        ResponseDefine responseDefine = gatewayDefine.getResponse();
        if (null != responseDefine) {
            EntityDefine entityDefine = responseDefine.getEntity();
            if (null != entityDefine) {
                String contentType = entityDefine.getType();
                if (!StringUtils.isEmpty(contentType)) {
                    try {
                        customResponseContentType = MediaType.parseMediaType(contentType);
                    } catch (Exception ex) {
                        customResponseContentType = null;
                    }
                }
            }
        }
        return customResponseContentType;
    }

    @Override
    protected <ResponseBody> void resultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException {

    }
}
