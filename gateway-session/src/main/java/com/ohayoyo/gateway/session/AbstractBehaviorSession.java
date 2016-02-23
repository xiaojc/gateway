package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.*;
import com.ohayoyo.gateway.http.GatewayHttpRequestEntityBuilder;
import com.ohayoyo.gateway.utils.GatewayPathUtils;
import com.ohayoyo.gateway.utils.GatewaySelectorUtils;
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

import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
@SuppressWarnings("unchecked")
public abstract class AbstractBehaviorSession extends AbstractGatewaySession {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBehaviorSession.class);

    @Override
    protected <ResponseBody> void gatewayResultVerify(SessionResponse<ResponseBody> sessionResponse, Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface) throws GatewaySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayResultValidator gatewayResultValidator = gatewayContext.getGatewayResultValidator();
        if (!ObjectUtils.isEmpty(gatewayResultValidator)) {
            gatewayResultValidator.validate(gatewayInterface, responseBodyClass, sessionResponse);
        }
    }

    @Override
    protected <RequestBody> void gatewayDataVerify(GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayDataValidator gatewayDataValidator = gatewayContext.getGatewayDataValidator();
        if (!ObjectUtils.isEmpty(gatewayDataValidator)) {
            gatewayDataValidator.validate(gatewayInterface, sessionRequest);
        }
    }

    @Override
    protected <RequestBody> void gatewayDataAutofill(GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayDataAutoFill gatewayDataAutoFill = gatewayContext.getGatewayDataAutoFill();
        if (!ObjectUtils.isEmpty(gatewayDataAutoFill)) {
            gatewayDataAutoFill.dataAutoFill(gatewayInterface.getRequest(), sessionRequest);
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


    protected final <RequestBody> URI resolveRequestUri(GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) {
        String select = sessionRequest.getSelect();
        Map<String, String> requestPathVariables = sessionRequest.getRequestPathVariables();
        MultiValueMap<String, String> requestQueries = sessionRequest.getRequestQueries();
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

    protected final <RequestBody> RequestEntity<RequestBody> resolveRequestEntity(GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) {
        URI uri = this.resolveRequestUri(gatewayInterface, sessionRequest);
        HttpMethod httpMethod = this.resolveRequestHttpMethod(gatewayInterface, sessionRequest);
        MultiValueMap<String, String> requestHeaders = sessionRequest.getRequestHeaders();
        RequestBody requestEntity = sessionRequest.getRequestBody();
        return (RequestEntity<RequestBody>) GatewayHttpRequestEntityBuilder.newInstance().url(uri).headers(requestHeaders).httpMethod(httpMethod).body(requestEntity).build();
    }

    protected final <RequestBody> HttpMethod resolveRequestHttpMethod(GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) {
        String select = sessionRequest.getSelect();
        GatewayRequest gatewayRequest = gatewayInterface.getRequest();
        Set<GatewayMethod> gatewayMethods = gatewayRequest.getMethods();
        GatewayMethod gatewayMethod = GatewaySelectorUtils.selectMethodDefine(select, gatewayMethods);
        String name = gatewayMethod.getName();
        HttpMethod httpMethod = HttpMethod.resolve(name.toUpperCase());
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
        GatewayRequest gatewayRequest = gatewayInterface.getRequest();
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
