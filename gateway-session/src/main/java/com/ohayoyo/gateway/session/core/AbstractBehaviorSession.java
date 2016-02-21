package com.ohayoyo.gateway.session.core;

import com.ohayoyo.gateway.define.http.*;
import com.ohayoyo.gateway.http.builder.GatewayHttpRequestEntityBuilder;
import com.ohayoyo.gateway.session.autofill.GatewayDataAutoFill;
import com.ohayoyo.gateway.session.exception.GatewaySessionException;
import com.ohayoyo.gateway.session.utils.GatewayPathUtils;
import com.ohayoyo.gateway.session.utils.GatewaySelectorUtils;
import com.ohayoyo.gateway.session.validator.GatewayDataValidator;
import com.ohayoyo.gateway.session.validator.GatewayInterfaceValidator;
import com.ohayoyo.gateway.session.validator.GatewayResultValidator;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
@SuppressWarnings("unchecked")
public abstract class AbstractBehaviorSession extends AbstractGatewaySession {

    @Override
    protected <ResponseBody> void gatewayResultVerify(GatewaySessionResponse<ResponseBody> gatewaySessionResponse, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface) throws GatewaySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayResultValidator gatewayResultValidator = gatewayContext.getGatewayResultValidator();
        if (!ObjectUtils.isEmpty(gatewayResultValidator)) {
            gatewayResultValidator.validate(gatewayInterface, responseBodyClass, gatewaySessionResponse);
        }
    }

    @Override
    protected <RequestBody> void gatewayDataVerify(GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) throws GatewaySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayDataValidator gatewayDataValidator = gatewayContext.getGatewayDataValidator();
        if (!ObjectUtils.isEmpty(gatewayDataValidator)) {
            gatewayDataValidator.validate(gatewayInterface, gatewaySessionRequest);
        }
    }

    @Override
    protected <RequestBody> void gatewayDataAutofill(GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) throws GatewaySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayDataAutoFill gatewayDataAutoFill = gatewayContext.getGatewayDataAutoFill();
        if (!ObjectUtils.isEmpty(gatewayDataAutoFill)) {
            gatewayDataAutoFill.dataAutoFill(gatewayInterface.getRequest(), gatewaySessionRequest);
        }
    }

    @Override
    protected void gatewayInterfaceVerify(GatewayInterface gatewayInterface) throws GatewaySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayInterfaceValidator gatewayInterfaceValidator = gatewayContext.getGatewayInterfaceValidator();
        if (!ObjectUtils.isEmpty(gatewayInterfaceValidator)) {
            gatewayInterfaceValidator.validate(gatewayInterface);
        }
    }


    protected final <RequestBody> URI resolveRequestUri(GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) {
        String select = gatewaySessionRequest.getSelect();
        Map<String, String> requestPathVariables = gatewaySessionRequest.getRequestPathVariables();
        MultiValueMap<String, String> requestQueries = gatewaySessionRequest.getRequestQueries();
        GatewayRequest gatewayRequest = gatewayInterface.getRequest();
        Set<GatewayProtocol> gatewayProtocols = gatewayRequest.getProtocols();
        Set<GatewayHost> gatewayHosts = gatewayRequest.getHosts();
        GatewayPath gatewayPath = gatewayRequest.getPath();
        String fragment = gatewayRequest.getFragment();
        GatewayProtocol gatewayProtocol = GatewaySelectorUtils.selectProtocolDefine(select, gatewayProtocols);
        GatewayHost gatewayHost = GatewaySelectorUtils.selectHostDefine(select, gatewayHosts);
        String scheme = gatewayProtocol.getName().toLowerCase();
        String host = gatewayHost.getHostname();
        int port = GatewaySelectorUtils.selectHostDefinePort(gatewayProtocol, gatewayHost);
        String[] pathSegments = GatewayPathUtils.pathSegments(gatewayPath);
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
        LOGGER.info("构建的URL:{}", uri);
        return uri;
    }

    protected final <RequestBody> RequestEntity<RequestBody> resolveRequestEntity(GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) {
        URI uri = this.resolveRequestUri(gatewayInterface, gatewaySessionRequest);
        HttpMethod httpMethod = this.resolveRequestHttpMethod(gatewayInterface, gatewaySessionRequest);
        MultiValueMap<String, String> requestHeaders = gatewaySessionRequest.getRequestHeaders();
        RequestBody requestEntity = gatewaySessionRequest.getRequestBody();
        return (RequestEntity<RequestBody>) GatewayHttpRequestEntityBuilder.newInstance().url(uri).headers(requestHeaders).httpMethod(httpMethod).body(requestEntity).build();
    }

    protected final <RequestBody> HttpMethod resolveRequestHttpMethod(GatewayInterface gatewayInterface, GatewaySessionRequest<RequestBody> gatewaySessionRequest) {
        String select = gatewaySessionRequest.getSelect();
        GatewayRequest gatewayRequest = gatewayInterface.getRequest();
        Set<GatewayMethod> gatewayMethods = gatewayRequest.getMethods();
        GatewayMethod gatewayMethod = GatewaySelectorUtils.selectMethodDefine(select, gatewayMethods);
        String name = gatewayMethod.getName();
        HttpMethod httpMethod = HttpMethod.resolve(name);
        return httpMethod;
    }

    protected final MediaType resolveGatewayEntityType(GatewayEntity gatewayEntity) {
        MediaType customContentType = null;
        if (!ObjectUtils.isEmpty(gatewayEntity)) {
            String contentType = gatewayEntity.getType();
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

    protected final MediaType resolveCustomRequestContentType(GatewayInterface gatewayInterface) {
        com.ohayoyo.gateway.define.http.GatewayRequest gatewayRequest = gatewayInterface.getRequest();
        MediaType customRequestContentType = null;
        if (!ObjectUtils.isEmpty(gatewayRequest)) {
            GatewayEntity gatewayEntity = gatewayRequest.getEntity();
            customRequestContentType = resolveGatewayEntityType(gatewayEntity);
        }
        return customRequestContentType;
    }

    protected final MediaType resolveCustomResponseContentType(GatewayInterface gatewayInterface) {
        GatewayResponse gatewayResponse = gatewayInterface.getResponse();
        MediaType customResponseContentType = null;
        if (!ObjectUtils.isEmpty(gatewayResponse)) {
            GatewayEntity gatewayEntity = gatewayResponse.getEntity();
            customResponseContentType = resolveGatewayEntityType(gatewayEntity);
        }
        return customResponseContentType;
    }

}
