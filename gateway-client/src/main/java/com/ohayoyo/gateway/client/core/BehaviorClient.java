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
import com.ohayoyo.gateway.http.core.HttpGateway;
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

    private GatewayDefineValidator gatewayDefineValidator;

    private GatewayDataValidator gatewayDataValidator;

    private GatewayResultValidator gatewayResultValidator;

    private GatewayAutofill gatewayAutofill;

    public BehaviorClient(HttpGateway httpGateway) {
        super(httpGateway);
    }

    public GatewayDefineValidator getGatewayDefineValidator() {
        return gatewayDefineValidator;
    }

    public BehaviorClient setGatewayDefineValidator(GatewayDefineValidator gatewayDefineValidator) {
        this.gatewayDefineValidator = gatewayDefineValidator;
        return this;
    }

    public GatewayDataValidator getGatewayDataValidator() {
        return gatewayDataValidator;
    }

    public BehaviorClient setGatewayDataValidator(GatewayDataValidator gatewayDataValidator) {
        this.gatewayDataValidator = gatewayDataValidator;
        return this;
    }

    public GatewayResultValidator getGatewayResultValidator() {
        return gatewayResultValidator;
    }

    public BehaviorClient setGatewayResultValidator(GatewayResultValidator gatewayResultValidator) {
        this.gatewayResultValidator = gatewayResultValidator;
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
            LOGGER.debug("存在网关定义验证器 .");
            this.gatewayDefineValidator.validate(gatewayDefine);
            LOGGER.debug("网关验证器验证完成 .");
        } else {
            LOGGER.debug("不存在网关定义验证器 .");
        }
    }

    @Override
    protected final <RequestBody> void gatewayAutofill(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        if (!ObjectUtils.isEmpty(this.gatewayAutofill)) {
            RequestDefine requestDefine = gatewayDefine.getRequest();
            LOGGER.debug("开始进行数据自动填充 .");
            this.gatewayAutofill.autofill(requestDefine, gatewayRequest);
            LOGGER.debug("结束进行数据自动填充 .");
        } else {
            LOGGER.debug("不存在自动填充器 .");
        }
    }

    @Override
    protected final <RequestBody> void gatewayDataVerify(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        if (!ObjectUtils.isEmpty(this.gatewayDataValidator)) {
            this.gatewayDataValidator.validate(gatewayDefine, gatewayRequest);
        }
    }

    @Override
    protected final <ResponseBody> void gatewayResultVerify(GatewayResponse<ResponseBody> gatewayResponse, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine) throws GatewayException {
        if (!ObjectUtils.isEmpty(this.gatewayResultValidator)) {
            this.gatewayResultValidator.validate(gatewayDefine, responseBodyClass, gatewayResponse);
        }
    }

    protected <RequestBody> URI resolveRequestUri(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) {

        String select = gatewayRequest.getSelect();

        LOGGER.debug("当前选择作用域环境:{}", select);

        Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        Set<HostDefine> hostDefines = requestDefine.getHosts();
        PathDefine pathDefine = requestDefine.getPath();
        String fragment = requestDefine.getFragment();


        ProtocolDefine protocolDefine = SelectDefineUtils.selectProtocolDefine(select, protocolDefines);

        LOGGER.debug("选择的协议定义名称:{} .", protocolDefine.getName());

        HostDefine hostDefine = SelectDefineUtils.selectHostDefine(select, hostDefines);

        LOGGER.debug("选择的主机定义名称:{},端口:{} .", hostDefine.getHostname(), hostDefine.getPort());

        String scheme = protocolDefine.getName().toLowerCase();
        String host = hostDefine.getHostname();

        int port = SelectDefineUtils.selectHostDefinePort(protocolDefine, hostDefine);

        LOGGER.debug("自动选择端口:{} .", port);

        String[] pathSegments = PathDefineUtils.pathSegments(pathDefine);

        LOGGER.debug("路径值片段集合:{} .", pathSegments);

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

        LOGGER.debug("反转的URL:{} .", uri);

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

        LOGGER.debug("当前选择作用域环境:{}", select);

        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<MethodDefine> methodDefines = requestDefine.getMethods();
        MethodDefine methodDefine = SelectDefineUtils.selectMethodDefine(select, methodDefines);
        String name = methodDefine.getName();

        LOGGER.debug("选择定义的方法名称:{} .", name);

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
