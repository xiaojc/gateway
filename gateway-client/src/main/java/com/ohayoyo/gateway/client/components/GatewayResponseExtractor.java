package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.TypeUtils;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 网关响应提取器
 *
 * @param <T> 响应实体类型
 */
public class GatewayResponseExtractor<T> extends AbstractGatewayComponent<ResponseExtractor<T>> implements ResponseExtractor<T> {

    private Class<T> responseType;

    private ClientHttpResponse clientHttpResponse;

    public GatewayResponseExtractor(Class<T> responseType) {
        this.responseType = responseType;
    }

    @Override
    public T extractData(ClientHttpResponse response) throws IOException {
        this.setClientHttpResponse(response);
        T result = null;
        boolean isNotHttpMessageConverterSupport = false;
        GatewayConfig gatewayConfig = this.getGatewayConfig();
        ClientHttpResponse clientHttpResponse = this.getClientHttpResponse();
        MessageBodyWrapper messageBodyWrapper = new MessageBodyWrapper(clientHttpResponse);
        if (messageBodyWrapper.hasMessageBody() && (!messageBodyWrapper.hasEmptyMessageBody())) {
            List<HttpMessageConverter<?>> httpMessageConverters = gatewayConfig.getHttpMessageConverters();
            Set<Class<?>> autoRecognitionClassSupports = gatewayConfig.getAutoRecognitionClassSupports();
            MediaType responseMediaType = getContentType(messageBodyWrapper);
            Class<?> responseClass = autoRecognitionMediaTypeConvertibleClass(responseType, responseMediaType, httpMessageConverters, autoRecognitionClassSupports);
            Set<MediaType> contentTypes = autoRecognitionClassConvertibleMediaType(responseClass, httpMessageConverters);
            List<MediaType> mediaTypeList = new ArrayList<MediaType>(contentTypes);
            MediaType.sortBySpecificity(mediaTypeList);
            mediaTypeList.add(0, responseMediaType);
            for (MediaType contentType : mediaTypeList) {
                boolean isSucceedRead = false;
                try {
                    for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
                        if (httpMessageConverter instanceof GenericHttpMessageConverter) {
                            GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                            if (genericHttpMessageConverter.canRead(responseClass, responseClass, contentType)) {
                                result = (T) genericHttpMessageConverter.read(responseClass, responseClass, messageBodyWrapper);
                                isSucceedRead = true;
                                isNotHttpMessageConverterSupport = false;
                                break;
                            }
                        }
                        if (httpMessageConverter.canRead(responseClass, contentType)) {
                            result = (T) httpMessageConverter.read((Class) responseClass, messageBodyWrapper);
                            isNotHttpMessageConverterSupport = false;
                            isSucceedRead = true;
                            break;
                        }
                        isNotHttpMessageConverterSupport = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result = null;
                    isSucceedRead = false;
                }
                if (isSucceedRead) {
                    break;
                }
            }
        }
        if (isNotHttpMessageConverterSupport) {
            throw new IOException("没有找到合适的HTTP消息转换器");
        }
        return result;
    }

    private boolean isIncludeMediaType(MediaType mediaType, List<MediaType> mediaTypes) {
        if (mediaType == null) {
            return true;
        }
        for (MediaType supportedMediaType : mediaTypes) {
            if (supportedMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    private Set<MediaType> autoRecognitionClassConvertibleMediaType(Class<?> responseType, List<HttpMessageConverter<?>> httpMessageConverters) {
        Set<MediaType> autoRecognitionClassConvertibleMediaTypes = new HashSet<MediaType>();
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
            List<MediaType> mediaTypes = httpMessageConverter.getSupportedMediaTypes();
            for (MediaType mediaType : mediaTypes) {
                if (httpMessageConverter instanceof GenericHttpMessageConverter) {
                    GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                    if (genericHttpMessageConverter.canRead(responseType, responseType, mediaType)) {
                        autoRecognitionClassConvertibleMediaTypes.add(mediaType);
                        break;
                    }
                    continue;
                }
                if (httpMessageConverter.canRead(responseType, mediaType)) {
                    autoRecognitionClassConvertibleMediaTypes.add(mediaType);
                    break;
                }
            }
        }
        if (CollectionUtils.isEmpty(autoRecognitionClassConvertibleMediaTypes)) {
            autoRecognitionClassConvertibleMediaTypes.add(MediaType.ALL);
        }
        return autoRecognitionClassConvertibleMediaTypes;
    }

    private Class<?> autoRecognitionMediaTypeConvertibleClass(Class<?> responseType, MediaType mediaType, List<HttpMessageConverter<?>> httpMessageConverters, Set<Class<?>> autoRecognitionClassSupports) {
        Class<?> resultAutoRecognitionClassSupport = responseType;
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
            List<MediaType> mediaTypes = httpMessageConverter.getSupportedMediaTypes();
            if (isIncludeMediaType(mediaType, mediaTypes)) {
                boolean isSelect = false;
                for (Class<?> autoRecognitionClassSupport : autoRecognitionClassSupports) {
                    if (!TypeUtils.isAssignable(responseType, autoRecognitionClassSupport)) {
                        continue;
                    }
                    if (httpMessageConverter instanceof GenericHttpMessageConverter) {
                        GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                        if (genericHttpMessageConverter.canRead(autoRecognitionClassSupport, autoRecognitionClassSupport, mediaType)) {
                            resultAutoRecognitionClassSupport = autoRecognitionClassSupport;
                            isSelect = true;
                            break;
                        }
                        continue;
                    }
                    if (httpMessageConverter.canRead(autoRecognitionClassSupport, mediaType)) {
                        resultAutoRecognitionClassSupport = autoRecognitionClassSupport;
                        isSelect = true;
                        break;
                    }
                }
                if (isSelect) {
                    break;
                }
            }
        }
        return resultAutoRecognitionClassSupport;
    }

    private MediaType getContentType(ClientHttpResponse response) {
        MediaType contentType = response.getHeaders().getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return contentType;
    }

    @Override
    public ResponseExtractor<T> getComponent() throws GatewayException {
        return this;
    }

    public ClientHttpResponse getClientHttpResponse() {
        return clientHttpResponse;
    }

    public GatewayResponseExtractor setClientHttpResponse(ClientHttpResponse clientHttpResponse) {
        this.clientHttpResponse = clientHttpResponse;
        return this;
    }

    private static class MessageBodyWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;

        private PushbackInputStream pushbackInputStream;


        public MessageBodyWrapper(ClientHttpResponse response) throws IOException {
            this.response = response;
        }

        public boolean hasMessageBody() throws IOException {
            HttpStatus responseStatus = this.getStatusCode();
            if (responseStatus.is1xxInformational() || responseStatus == HttpStatus.NO_CONTENT ||
                    responseStatus == HttpStatus.NOT_MODIFIED) {
                return false;
            } else if (this.getHeaders().getContentLength() == 0) {
                return false;
            }
            return true;
        }

        public boolean hasEmptyMessageBody() throws IOException {
            InputStream body = this.response.getBody();
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

        @Override
        public HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException {
            return (this.pushbackInputStream != null ? this.pushbackInputStream : this.response.getBody());
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return this.response.getStatusCode();
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        @Override
        public void close() {
            this.response.close();
        }

    }

}
