package com.ohayoyo.gateway.channel.test;

import com.ohayoyo.gateway.channel.GatewayChannel;
import com.ohayoyo.gateway.channel.test.model.TestPack;
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

    @Test
    public void testGatewayChannel() {
        logger.debug("{}", gatewayChannel);
    }

    @Test
    public void testApiHuceoComMeinvOther() throws Exception {
        String serviceCode = "GWM0001";
        String key = "api.huceo.com/meinv/other";
        TestPack testPack = gatewayChannel.channel(TestPack.class, key, gatewayChannel.newRestfulGatewayRequestBuilder()
                .requestQueries("key", "29e069ec39101eb669121554bf67024f")
                .requestQueries("num", "20")
                .build());
        logger.debug("{}", testPack);
    }

}
