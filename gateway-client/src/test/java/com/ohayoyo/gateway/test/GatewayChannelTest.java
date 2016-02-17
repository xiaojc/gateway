package com.ohayoyo.gateway.test;

import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.test.model.TestPack;
import com.ohayoyo.spring.test.junit4.SpringJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {
        "classpath:/conf/gateway-channel-bean.xml",
        "classpath:/interfaces/**/*.xml"
})
public class GatewayChannelTest extends SpringJunit4Test {

    @Autowired
    private GatewayChannel gatewayChannel;

    @Autowired
    private RestfulRequestBuilder restfulRequestBuilder;

    @Test
    public void testGatewayChannel() {
        logger.debug("{}", gatewayChannel);
    }

    @Test
    public void testApiHuceoComMeinvOther() throws Exception {
        String key = "api.huceo.com/meinv/other";
        TestPack testPack = gatewayChannel.channel(TestPack.class, key, restfulRequestBuilder
                .requestQueries("key", "29e069ec39101eb669121554bf67024f")
                .requestQueries("num", "20")
                .build());
        logger.debug("{}", testPack);
    }

}
