package com.ohayoyo.gateway.client.wrapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * HTTP响应客户端包装器
 */
public class ClientHttpResponseWrapper implements ClientHttpResponse {

    /**
     * HTTP响应客户端
     */
    private final ClientHttpResponse clientHttpResponse;

    /**
     * 回推输入流
     */
    private PushbackInputStream pushbackInputStream;

    /**
     * 构建一个HTTP响应客户端包装器
     *
     * @param clientHttpResponse HTTP响应客户端
     */
    public ClientHttpResponseWrapper(ClientHttpResponse clientHttpResponse) {
        this.clientHttpResponse = clientHttpResponse;
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
        InputStream body = this.clientHttpResponse.getBody();
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
        return this.clientHttpResponse.getHeaders();
    }

    /**
     * Return the body of the message as an input stream.
     *
     * @return the input stream body (never {@code null})
     * @throws IOException in case of I/O Errors
     */
    @Override
    public InputStream getBody() throws IOException {
        return (this.pushbackInputStream != null ? this.pushbackInputStream : this.clientHttpResponse.getBody());
    }

    /**
     * Return the HTTP status code of the response.
     *
     * @return the HTTP status as an HttpStatus enum value
     * @throws IOException in case of I/O errors
     */
    @Override
    public HttpStatus getStatusCode() throws IOException {
        return this.clientHttpResponse.getStatusCode();
    }

    /**
     * Return the HTTP status code of the response as integer
     *
     * @return the HTTP status as an integer
     * @throws IOException in case of I/O errors
     */
    @Override
    public int getRawStatusCode() throws IOException {
        return this.clientHttpResponse.getRawStatusCode();
    }

    /**
     * Return the HTTP status text of the response.
     *
     * @return the HTTP status text
     * @throws IOException in case of I/O errors
     */
    @Override
    public String getStatusText() throws IOException {
        return this.clientHttpResponse.getStatusText();
    }

    /**
     * Close this response, freeing any resources created.
     */
    @Override
    public void close() {
        this.clientHttpResponse.close();
    }

}
