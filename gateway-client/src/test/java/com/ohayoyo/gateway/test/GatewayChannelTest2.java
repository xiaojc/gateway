package com.ohayoyo.gateway.test;

import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.test.model.DDQ;
import com.ohayoyo.spring.test.junit4.SpringJunit4Test;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

@ContextConfiguration(locations = {
        "classpath:/conf/test-gateway-channel-bean.xml",
        "classpath:/interfaces/**/*.xml"
})
public class GatewayChannelTest2 extends SpringJunit4Test {

    @Autowired
    private GatewayChannel gatewayChannel;

    @Autowired
    private RestfulRequestBuilder restfulRequestBuilder;

    @Test
    public void testGatewayChannel() {
        logger.debug("{}", gatewayChannel);
    }

    @Test
    public void testOrgloanDataWsPublicOrgloanWS() throws Exception {
        String key = "orgloanDataWsPublicOrgloanWS";
        DDQ ddq = new DDQ();
        ddq.setService("idCardIdentification");
        String str = gatewayChannel.channel(String.class, key, restfulRequestBuilder
                .requestBody(ddq)
                .build());
        logger.debug("{}", str);
    }

    @Test
    public void testOrgloanDataWsPublicOrgloanWS2() throws Exception {
        String key = "orgloanDataWsPublicOrgloanWS";
        DDQ ddq = new DDQ();
        ddq.setService("idCardIdentification");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\Others\\b_60522160.jpg"));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        String picture = Base64.encodeBase64String(content);
        ddq.setPicture(picture);
        String str = gatewayChannel.channel(String.class, key, restfulRequestBuilder
                .requestBody(ddq)
                .build());
        logger.debug("{}", str);
    }

}
