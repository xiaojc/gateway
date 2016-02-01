package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.define.core.EntityDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.List;

/**
 * 网关响应提取器组件 HeadersResponseExtractor
 *
 * @param <T> 响应实体类型
 */
public class GatewayResponseExtractor<T> extends AbstractGatewayComponent<ResponseExtractor<T>> implements ResponseExtractor<T> {

    /**
     * 响应实体类型
     */
    private Class<T> responseClass;

    /**
     * HTTP响应客户端
     */
    private ClientHttpResponse clientHttpResponse;

    private HttpMethod httpMethod;

    /**
     * 构建一个网关响应提取器组件
     *
     * @param responseClass 响应实体类型
     */
    public GatewayResponseExtractor(Class<T> responseClass, HttpMethod httpMethod) {
        this.responseClass = responseClass;
        this.httpMethod = httpMethod;
    }

    @Override
    public T extractData(ClientHttpResponse response) throws IOException {
        if (httpMethod.matches(HttpMethod.HEAD.name())) {
            return (T) response.getHeaders();
        }
        //设置HTTP响应客户端
        this.setClientHttpResponse(response);
        //提取器执行提取数据
        return doExtractData();
    }

    /**
     * 执行提取数据
     *
     * @return 返回响应实体, 如果没有实体则返回null
     * @throws IOException 抛出IO异常
     */
    protected T doExtractData() throws IOException {
        T result = null;
        //是否不存在HTTP消息转换器支持
        boolean isNotHttpMessageConverterSupport = false;
        //获取网关配置
        GatewayConfig gatewayConfig = this.getGatewayConfig();
        //获取HTTP响应客户端
        ClientHttpResponse clientHttpResponse = this.getClientHttpResponse();
        //HTTP响应实体包装器
        HttpResponseEntityWrapper httpResponseEntityWrapper = new HttpResponseEntityWrapper(clientHttpResponse);
        //判断是否存在响应实体
        if (httpResponseEntityWrapper.hasResponseEntity() && (!httpResponseEntityWrapper.hasEmptyResponseEntity())) {
            //获取全部支持的HTTP消息转换器
            List<HttpMessageConverter<?>> httpMessageConverters = gatewayConfig.getHttpMessageConverters();
            //获取响应的媒体类型
            MediaType responseMediaType = getResponseContentType(httpResponseEntityWrapper);
            //获取优选的媒体类型
            MediaType preferenceMediaType = getPreferenceMediaType(responseMediaType);
            for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
                //如果是一般类型的HTTP消息转换器
                if (httpMessageConverter instanceof GenericHttpMessageConverter) {
                    GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                    //如果可读
                    if (genericHttpMessageConverter.canRead(responseClass, responseClass, preferenceMediaType)) {
                        //读取数据并进行提取
                        result = (T) genericHttpMessageConverter.read(responseClass, responseClass, httpResponseEntityWrapper);
                        isNotHttpMessageConverterSupport = false;
                        break;
                    }
                }
                //如果可读
                if (httpMessageConverter.canRead(responseClass, preferenceMediaType)) {
                    //读取数据并进行提取
                    result = (T) httpMessageConverter.read((Class) responseClass, httpResponseEntityWrapper);
                    isNotHttpMessageConverterSupport = false;
                    break;
                }
                isNotHttpMessageConverterSupport = true;
            }
        }
        if (isNotHttpMessageConverterSupport) {
            throw new IOException("没有找到合适的HTTP消息转换器读取数据");
        }
        return result;
    }

    /**
     * 获取优选的媒体类型
     *
     * @param responseMediaType 响应结果媒体类型
     * @return 返回优选的媒体类型
     */
    protected MediaType getPreferenceMediaType(MediaType responseMediaType) {
        if (null == responseMediaType) {
            return MediaType.ALL;
        }
        MediaType resultPreferenceMediaType;
        MediaType resultDefineMediaType = MediaType.ALL;
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        if (null != gatewayDefine) {
            ResponseDefine responseDefine = gatewayDefine.getResponse();
            if (null != responseDefine) {
                EntityDefine entityDefine = responseDefine.getEntity();
                if (null != entityDefine) {
                    String defineMediaType = entityDefine.getType();
                    if (!StringUtils.isEmpty(defineMediaType)) {
                        try {
                            resultDefineMediaType = MediaType.parseMediaType(defineMediaType);
                        } catch (Exception ex) {
                            resultDefineMediaType = MediaType.ALL;
                        }
                    }
                }
            }
        }
        //如果包含响应媒体类型,直接返回响应媒体类型
        if (resultDefineMediaType.includes(responseMediaType)) {
            resultPreferenceMediaType = responseMediaType;
        } else {
            resultPreferenceMediaType = resultDefineMediaType;
        }
        return resultPreferenceMediaType;
    }

    /**
     * 获取响应内容类型
     *
     * @param clientHttpResponse HTTP响应客户端
     * @return 返回响应内容类型
     */
    private MediaType getResponseContentType(ClientHttpResponse clientHttpResponse) {
        MediaType contentType = clientHttpResponse.getHeaders().getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return contentType;
    }


    /**
     * 获取网关响应提取器组件
     *
     * @return 返回网关响应提取器组件
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public ResponseExtractor<T> getComponent() throws GatewayException {
        return this;
    }

    /**
     * 获取HTTP响应客户端
     *
     * @return 返回HTTP响应客户端
     */
    public ClientHttpResponse getClientHttpResponse() {
        return clientHttpResponse;
    }

    /**
     * 设置HTTP响应客户端
     *
     * @param clientHttpResponse HTTP响应客户端
     * @return 返回网关响应提取器组件
     */
    public GatewayResponseExtractor setClientHttpResponse(ClientHttpResponse clientHttpResponse) {
        this.clientHttpResponse = clientHttpResponse;
        return this;
    }

    /**
     * HTTP响应实体包装器
     */
    private static class HttpResponseEntityWrapper implements ClientHttpResponse {

        /**
         * 内部HTTP响应客户端
         */
        private final ClientHttpResponse internalClientHttpResponse;

        /**
         * 回推输入流
         */
        private PushbackInputStream pushbackInputStream;

        /**
         * 构建一个HTTP响应实体包装器
         *
         * @param internalClientHttpResponse 内部HTTP响应客户端
         */
        public HttpResponseEntityWrapper(ClientHttpResponse internalClientHttpResponse) {
            this.internalClientHttpResponse = internalClientHttpResponse;
        }

        /**
         * 是否有响应实体
         *
         * @return 返回是否有响应实体
         * @throws IOException 抛出IO异常
         */
        public boolean hasResponseEntity() throws IOException {
            HttpStatus responseStatus = this.getStatusCode();
            if (responseStatus.is1xxInformational() || responseStatus == HttpStatus.NO_CONTENT || responseStatus == HttpStatus.NOT_MODIFIED) {
                return false;
            } else if (this.getHeaders().getContentLength() == 0) {
                return false;
            }
            return true;
        }

        /**
         * 是否空的响应实体
         *
         * @return 返回是否空的响应实体
         * @throws IOException 抛出IO异常
         */
        public boolean hasEmptyResponseEntity() throws IOException {
            InputStream body = this.internalClientHttpResponse.getBody();
            if (body == null) {
                return true;
            } else if (body.markSupported()) {
                body.mark(1);
                if (body.read() == -1) {
                    return true;
                } else {
                    body.reset();
                    return false;
                }
            } else {
                this.pushbackInputStream = new PushbackInputStream(body);
                int b = this.pushbackInputStream.read();
                if (b == -1) {
                    return true;
                } else {
                    this.pushbackInputStream.unread(b);
                    return false;
                }
            }
        }

        /**
         * Return the headers of this message.
         *
         * @return a corresponding HttpHeaders object (never {@code null})
         */
        @Override
        public HttpHeaders getHeaders() {
            return this.internalClientHttpResponse.getHeaders();
        }

        /**
         * Return the body of the message as an input stream.
         *
         * @return the input stream body (never {@code null})
         * @throws IOException in case of I/O Errors
         */
        @Override
        public InputStream getBody() throws IOException {
            return (this.pushbackInputStream != null ? this.pushbackInputStream : this.internalClientHttpResponse.getBody());
        }

        /**
         * Return the HTTP status code of the response.
         *
         * @return the HTTP status as an HttpStatus enum value
         * @throws IOException in case of I/O errors
         */
        @Override
        public HttpStatus getStatusCode() throws IOException {
            return this.internalClientHttpResponse.getStatusCode();
        }

        /**
         * Return the HTTP status code of the response as integer
         *
         * @return the HTTP status as an integer
         * @throws IOException in case of I/O errors
         */
        @Override
        public int getRawStatusCode() throws IOException {
            return this.internalClientHttpResponse.getRawStatusCode();
        }

        /**
         * Return the HTTP status text of the response.
         *
         * @return the HTTP status text
         * @throws IOException in case of I/O errors
         */
        @Override
        public String getStatusText() throws IOException {
            return this.internalClientHttpResponse.getStatusText();
        }

        /**
         * Close this response, freeing any resources created.
         */
        @Override
        public void close() {
            this.internalClientHttpResponse.close();
        }

    }


}
