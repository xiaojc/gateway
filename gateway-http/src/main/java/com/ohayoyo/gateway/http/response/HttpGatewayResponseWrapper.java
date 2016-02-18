package com.ohayoyo.gateway.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * @author 蓝明乐
 */
public class HttpGatewayResponseWrapper implements ClientHttpResponse {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayResponseWrapper.class);

    private final ClientHttpResponse clientHttpResponse;

    private PushbackInputStream pushbackInputStream;

    public HttpGatewayResponseWrapper(ClientHttpResponse clientHttpResponse) throws IOException {
        Assert.notNull(clientHttpResponse);
        this.clientHttpResponse = clientHttpResponse;
    }

    public boolean hasMessageBody() throws IOException {
        HttpStatus responseStatus = this.getStatusCode();
        if (responseStatus.is1xxInformational() || responseStatus == HttpStatus.NO_CONTENT || responseStatus == HttpStatus.NOT_MODIFIED) {
            return false;
        } else if (this.getHeaders().getContentLength() == 0) {
            return false;
        }
        return true;
    }

    public boolean hasEmptyMessageBody() throws IOException {
        InputStream body = this.clientHttpResponse.getBody();
        if (ObjectUtils.isEmpty(body)) {
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
        return this.clientHttpResponse.getHeaders();
    }

    @Override
    public InputStream getBody() throws IOException {
        return (this.pushbackInputStream != null ? this.pushbackInputStream : this.clientHttpResponse.getBody());
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return this.clientHttpResponse.getStatusCode();
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return this.clientHttpResponse.getRawStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return this.clientHttpResponse.getStatusText();
    }

    @Override
    public void close() {
        this.clientHttpResponse.close();
    }

}
