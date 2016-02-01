package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.core.EntityDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * 网关请求回调器组件
 */
public class GatewayRequestCallback extends AbstractGatewayComponent<RequestCallback> implements RequestCallback {

    /**
     * HTTP请求客户端
     */
    private ClientHttpRequest clientHttpRequest;

    /**
     * Gets called by {@link com.ohayoyo.gateway.client.core.GatewayExecutor#execute(URI, HttpMethod, RequestCallback, ResponseExtractor)}  } with an opened {@code ClientHttpRequest}.
     * Does not need to care about closing the request or about handling errors:
     * this will all be handled by the {@code RestTemplate}.
     *
     * @param request the active HTTP request
     * @throws IOException in case of I/O errors
     */
    @Override
    public void doWithRequest(ClientHttpRequest request) throws IOException {
        this.setClientHttpRequest(request);
        try {
            //处理请求数据
            this.doWithRequestData();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * 处理请求数据
     *
     * @throws GatewayException 抛出网关异常
     */
    protected void doWithRequestData() throws GatewayException, IOException {
        GatewayConfig gatewayConfig = this.getGatewayConfig();
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        ClientHttpRequest clientHttpRequest = this.getClientHttpRequest();
        this.doWithRequestHeaderData(gatewayRequest, clientHttpRequest);
        this.doWithRequestAcceptHeaderData(gatewayConfig, clientHttpRequest);
        this.doWithRequestEntityData(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest);
    }

    /**
     * 处理请求头数据
     *
     * @param gatewayRequest    网关请求
     * @param clientHttpRequest HTTP请求客户端
     * @throws GatewayException 抛出网关异常
     */
    private void doWithRequestHeaderData(GatewayRequest gatewayRequest, ClientHttpRequest clientHttpRequest) throws GatewayException {
        MultiValueMap<String, String> requestHeaders = gatewayRequest.getRequestHeaders();
        if (!CollectionUtils.isEmpty(requestHeaders)) {
            HttpHeaders httpHeaders = clientHttpRequest.getHeaders();
            Set<Map.Entry<String, List<String>>> requestHeadersEntries = requestHeaders.entrySet();
            for (Map.Entry<String, List<String>> requestHeadersEntry : requestHeadersEntries) {
                String headerName = requestHeadersEntry.getKey();
                List<String> headerValues = requestHeadersEntry.getValue();
                if (!CollectionUtils.isEmpty(headerValues)) {
                    for (String headerValue : headerValues) {
                        if (StringUtils.isEmpty(headerValue)) {
                            continue;
                        }
                        httpHeaders.add(headerName, headerValue);
                    }
                }
            }
        }
    }

    /**
     * 处理Accept请求数据
     *
     * @param gatewayConfig     网关配置
     * @param clientHttpRequest HTTP请求客户端
     * @throws GatewayException 抛出网关异常
     */
    private void doWithRequestAcceptHeaderData(GatewayConfig gatewayConfig, ClientHttpRequest clientHttpRequest) throws GatewayException {
        List<MediaType> accept = clientHttpRequest.getHeaders().getAccept(); //获取所支持接收的媒体类型
        if (CollectionUtils.isEmpty(accept)) { //如果不存在,自动设置所支持的媒体类型
            Set<MediaType> allSupportedMediaTypeSet = new HashSet<MediaType>();
            List<HttpMessageConverter<?>> httpMessageConverters = gatewayConfig.getHttpMessageConverters();
            for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
                List<MediaType> mediaTypes = httpMessageConverter.getSupportedMediaTypes();
                if (!CollectionUtils.isEmpty(mediaTypes)) {
                    for (MediaType mediaType : mediaTypes) {
                        allSupportedMediaTypeSet.add(mediaType);
                    }
                }
            }
            List<MediaType> mediaTypeList = new ArrayList<MediaType>();
            mediaTypeList.addAll(allSupportedMediaTypeSet);
            if (!allSupportedMediaTypeSet.isEmpty()) {
                MediaType.sortBySpecificity(mediaTypeList);
                clientHttpRequest.getHeaders().setAccept(mediaTypeList);
            }
        }
    }

    /**
     * 处理实体请求数据
     *
     * @param gatewayConfig     网关配置
     * @param gatewayRequest    网关请求
     * @param gatewayDefine     网关定义
     * @param clientHttpRequest HTTP请求客户端
     * @throws GatewayException 抛出网关异常
     */
    private void doWithRequestEntityData(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest) throws GatewayException, IOException {
        HttpEntity<?> httpRequestEntity = null;
        Object requestEntityData = gatewayRequest.getRequestEntity();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        if (null != requestDefine) {
            if (requestEntityData instanceof HttpEntity) {
                httpRequestEntity = (HttpEntity<?>) requestEntityData;
            } else if (requestEntityData != null) {
                httpRequestEntity = new HttpEntity<Object>(requestEntityData);
                EntityDefine entityDefine = requestDefine.getEntity();
                if (null != entityDefine) {
                    String entityDefineType = entityDefine.getType();
                    MediaType entityDefineMediaType = MediaType.ALL;
                    if (!StringUtils.isEmpty(entityDefineType)) {
                        //解析媒体类型
                        entityDefineMediaType = MediaType.parseMediaType(entityDefineType);
                    }
                    //获取实体请求报头
                    HttpHeaders httpHeaders = httpRequestEntity.getHeaders();
                    //设置内容类型
                    httpHeaders.setContentType(entityDefineMediaType);
                }
            }
        }
        //如果HTTP请求实体为空
        if (null == httpRequestEntity) {
            httpRequestEntity = HttpEntity.EMPTY;
        }
        //如果没有请求实体
        if (!httpRequestEntity.hasBody()) {
            //获取原来HTTP请求中的全部请求头
            HttpHeaders httpHeaders = clientHttpRequest.getHeaders();
            //获取原来请求实体中的全部请求头
            HttpHeaders requestHeaders = httpRequestEntity.getHeaders();
            //如果存在实体请求头,进行合并
            if (!requestHeaders.isEmpty()) {
                httpHeaders.putAll(requestHeaders);
            }
            //重置内容长度
            if (httpHeaders.getContentLength() == -1) {
                httpHeaders.setContentLength(0L);
            }
        } else {
            Object requestBodyData = httpRequestEntity.getBody();
            Class<?> requestType = requestBodyData.getClass();
            HttpHeaders requestHeaders = httpRequestEntity.getHeaders();
            MediaType requestContentType = requestHeaders.getContentType();
            for (HttpMessageConverter<?> httpMessageConverter : gatewayConfig.getHttpMessageConverters()) {
                if (httpMessageConverter.canWrite(requestType, requestContentType)) {
                    if (!requestHeaders.isEmpty()) {
                        this.getClientHttpRequest().getHeaders().putAll(requestHeaders);
                    }
                    ((HttpMessageConverter<Object>) httpMessageConverter).write(requestEntityData, requestContentType, clientHttpRequest);
                    return;
                }
            }
            throw new GatewayException("没有找到合适的HTTP消息转换器写入数据");
        }
    }

    /**
     * 获取网关请求回调器组件
     *
     * @return 返回网关请求回调器组件
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public RequestCallback getComponent() throws GatewayException {
        return this;
    }

    /**
     * 获取HTTP请求客户端
     *
     * @return 返回HTTP请求客户端
     */
    public ClientHttpRequest getClientHttpRequest() {
        return clientHttpRequest;
    }

    /**
     * 设置HTTP请求客户端
     *
     * @param clientHttpRequest HTTP请求客户端
     * @return 返回网关请求回调器组件
     */
    public GatewayRequestCallback setClientHttpRequest(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
        return this;
    }

}
