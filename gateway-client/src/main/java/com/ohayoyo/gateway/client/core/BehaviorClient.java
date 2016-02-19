package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.utils.PathDefineUtils;
import com.ohayoyo.gateway.client.utils.SelectDefineUtils;
import com.ohayoyo.gateway.client.validator.GatewayDataValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayResultValidator;
import com.ohayoyo.gateway.define.http.*;
import com.ohayoyo.gateway.http.builder.HttpGatewayRequestEntityBuilder;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class BehaviorClient extends AbstractClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(BehaviorClient.class);

    @Override
    protected final void gatewayDefineVerify(GatewayDefine gatewayDefine) throws GatewayException, IOException, HttpGatewayException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayDefineValidator gatewayDefineValidator = gatewayContext.getGatewayDefineValidator();
        if (!ObjectUtils.isEmpty(gatewayDefineValidator)) {
            gatewayDefineValidator.validate(gatewayDefine);
        }
    }

    @Override
    protected final <RequestBody> void gatewayAutofill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayAutofill gatewayAutofill = gatewayContext.getGatewayAutofill();
        if (!ObjectUtils.isEmpty(gatewayAutofill)) {
            gatewayAutofill.autofill(gatewayDefine.getRequest(), gatewayRequest);
        }
    }

    @Override
    protected final <RequestBody> void gatewayDataVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayDataValidator gatewayDataValidator = gatewayContext.getGatewayDataValidator();
        if (!ObjectUtils.isEmpty(gatewayDataValidator)) {
            gatewayDataValidator.validate(gatewayDefine, gatewayRequest);
        }
    }

    @Override
    protected final <ResponseBody> void gatewayResultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayResultValidator gatewayResultValidator = gatewayContext.getGatewayResultValidator();
        if (!ObjectUtils.isEmpty(gatewayResultValidator)) {
            gatewayResultValidator.validate(gatewayDefine, responseBodyClass, gatewayResponse);
        }
    }

    protected final <RequestBody> URI resolveRequestUri(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        String select = gatewayRequest.getSelect();
        Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        Set<HostDefine> hostDefines = requestDefine.getHosts();
        PathDefine pathDefine = requestDefine.getPath();
        String fragment = requestDefine.getFragment();
        ProtocolDefine protocolDefine = SelectDefineUtils.selectProtocolDefine(select, protocolDefines);
        HostDefine hostDefine = SelectDefineUtils.selectHostDefine(select, hostDefines);
        String scheme = protocolDefine.getName().toLowerCase();
        String host = hostDefine.getHostname();
        int port = SelectDefineUtils.selectHostDefinePort(protocolDefine, hostDefine);
        String[] pathSegments = PathDefineUtils.pathSegments(pathDefine);
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
        LOGGER.info("构建的URL:{}",uri);
        return uri;
    }

    @SuppressWarnings("unchecked")
    protected final <RequestBody> RequestEntity<RequestBody> resolveRequestEntity(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        URI uri = this.resolveRequestUri(gatewayDefine, gatewayRequest);
        HttpMethod httpMethod = this.resolveRequestHttpMethod(gatewayDefine, gatewayRequest);
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        RequestBody requestEntity = gatewayRequest.getRequestBody();
        return (RequestEntity<RequestBody>) HttpGatewayRequestEntityBuilder.newInstance().url(uri).headers(requestHeaders).httpMethod(httpMethod).body(requestEntity).build();
    }

    protected final <RequestBody> HttpMethod resolveRequestHttpMethod(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        String select = gatewayRequest.getSelect();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<MethodDefine> methodDefines = requestDefine.getMethods();
        MethodDefine methodDefine = SelectDefineUtils.selectMethodDefine(select, methodDefines);
        String name = methodDefine.getName();
        HttpMethod httpMethod = HttpMethod.resolve(name);
        return httpMethod;
    }

    protected final MediaType resolveEntityDefineContentType(EntityDefine entityDefine) {
        MediaType customContentType = null;
        if (!ObjectUtils.isEmpty(entityDefine)) {
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

    protected final MediaType resolveCustomRequestContentType(GatewayDefine gatewayDefine) {
        RequestDefine requestDefine = gatewayDefine.getRequest();
        MediaType customRequestContentType = null;
        if (!ObjectUtils.isEmpty(requestDefine)) {
            EntityDefine entityDefine = requestDefine.getEntity();
            customRequestContentType = resolveEntityDefineContentType(entityDefine);
        }
        return customRequestContentType;
    }

    protected final MediaType resolveCustomResponseContentType(GatewayDefine gatewayDefine) {
        ResponseDefine responseDefine = gatewayDefine.getResponse();
        MediaType customResponseContentType = null;
        if (!ObjectUtils.isEmpty(responseDefine)) {
            EntityDefine entityDefine = responseDefine.getEntity();
            customResponseContentType = resolveEntityDefineContentType(entityDefine);
        }
        return customResponseContentType;
    }

}
