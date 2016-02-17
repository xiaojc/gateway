package com.ohayoyo.gateway.test.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohayoyo.gateway.test.model.DDQ;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DDQHttpMessageConverter implements HttpMessageConverter {

    private static final List<MediaType> SUPPORTED_MEDIA_TYPES = new ArrayList<MediaType>();

    static {
        SUPPORTED_MEDIA_TYPES.add(MediaType.parseMediaType("application/ddq-xml"));
    }

    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return DDQ.class.isAssignableFrom(clazz);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return SUPPORTED_MEDIA_TYPES;
    }

    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        DDQ ddq = (DDQ) o;
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ddq);
        String service = ddq.getService();
        String body = String.format(Locale.CHINA, TEMPLATE, service, json, service);
        OutputStream outputStream = outputMessage.getBody();
        outputStream.write(body.getBytes(Charset.forName("UTF-8")));
        outputStream.flush();
        outputStream.close();
    }

    private static final String TEMPLATE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.vcredit.com/\"><soapenv:Header/><soapenv:Body><web:%s><json>%s</json></web:%s></soapenv:Body></soapenv:Envelope>";

}
