package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.utils.PathDefineUtil;
import com.ohayoyo.gateway.client.utils.SelectDefineUtil;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.define.core.*;
import com.ohayoyo.gateway.http.builder.HttpGatewayRequestEntityBuilder;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
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

    private GatewayDefineValidator gatewayDefineValidator;

    private GatewayAutofill gatewayAutofill;

    public BehaviorClient(HttpGateway httpGateway) {
        this(httpGateway, null, null);
    }

    public BehaviorClient(HttpGateway httpGateway, GatewayDefineValidator gatewayDefineValidator, GatewayAutofill gatewayAutofill) {
        super(httpGateway);
        this.gatewayDefineValidator = gatewayDefineValidator;
        this.gatewayAutofill = gatewayAutofill;
    }

    public GatewayDefineValidator getGatewayDefineValidator() {
        return gatewayDefineValidator;
    }

    public BehaviorClient setGatewayDefineValidator(GatewayDefineValidator gatewayDefineValidator) {
        this.gatewayDefineValidator = gatewayDefineValidator;
        return this;
    }

    public GatewayAutofill getGatewayAutofill() {
        return gatewayAutofill;
    }

    public BehaviorClient setGatewayAutofill(GatewayAutofill gatewayAutofill) {
        this.gatewayAutofill = gatewayAutofill;
        return this;
    }

    @Override
    protected final void gatewayDefineVerify(GatewayDefine gatewayDefine) throws GatewayException, IOException, HttpGatewayException {
        if (!ObjectUtils.isEmpty(this.gatewayDefineValidator)) {
            Class<?> supportType = gatewayDefine.getClass();
            if (this.gatewayDefineValidator.supports(supportType)) {
                this.gatewayDefineValidator.validate(gatewayDefine);
            }
        }
    }

    @Override
    protected final <RequestBody> void gatewayAutofill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException {
        if (!ObjectUtils.isEmpty(this.gatewayAutofill)) {
            RequestDefine requestDefine = gatewayDefine.getRequest();
            if (!ObjectUtils.isEmpty(requestDefine)) {
                this.gatewayAutofill.autofill(requestDefine, gatewayRequest);
            }
        }
    }

    @Override
    protected final <RequestBody> void gatewayDataVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException, IOException, HttpGatewayException {
    }

    @Override
    protected final <ResponseBody> void gatewayResultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException, IOException, HttpGatewayException {
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
        String scheme = protocolDefine.getName().toLowerCase();
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

    protected <RequestBody> RequestEntity<RequestBody> resolveRequestEntity(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {
        URI uri = this.resolveRequestUri(gatewayDefine, gatewayRequest);
        HttpMethod httpMethod = this.resolveRequestHttpMethod(gatewayDefine, gatewayRequest);
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        RequestBody requestEntity = gatewayRequest.getRequestBody();
        return (RequestEntity<RequestBody>) HttpGatewayRequestEntityBuilder.newInstance().url(uri).headers(requestHeaders).httpMethod(httpMethod).body(requestEntity).build();
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

    protected MediaType resolveCustomRequestContentType(GatewayDefine gatewayDefine) {
        RequestDefine requestDefine = gatewayDefine.getRequest();
        MediaType customRequestContentType = null;
        if (!ObjectUtils.isEmpty(requestDefine)) {
            EntityDefine entityDefine = requestDefine.getEntity();
            customRequestContentType = resolveEntityDefineContentType(entityDefine);
        }
        return customRequestContentType;
    }

    protected MediaType resolveCustomResponseContentType(GatewayDefine gatewayDefine) {
        ResponseDefine responseDefine = gatewayDefine.getResponse();
        MediaType customResponseContentType = null;
        if (!ObjectUtils.isEmpty(responseDefine)) {
            EntityDefine entityDefine = responseDefine.getEntity();
            customResponseContentType = resolveEntityDefineContentType(entityDefine);
        }
        return customResponseContentType;
    }

}
