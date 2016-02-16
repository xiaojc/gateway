package com.ohayoyo.gateway.channel.test;

import com.ohayoyo.gateway.channel.GatewayChannel;
import com.ohayoyo.gateway.channel.SpringGatewayConfig;
import com.ohayoyo.gateway.channel.test.model.TestPack;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.gateway.define.http.builder.Builders;
import com.ohayoyo.spring.test.junit4.SpringJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        SpringGatewayConfig.class,
        JavaGatewayChannelTest.TestConfig.class
})
public class JavaGatewayChannelTest extends SpringJunit4Test {

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

    @Configuration
    public static class TestConfig {

        @Bean
        public InterfaceDefine apiHuceoComMeinvOther() {
            //@formatter:off
            InterfaceDefine interfaceDefine = Builders
                    .memory()
                        .key("api.huceo.com/meinv/other")
                        .request()
                            .protocol()
                                .name("http").parent()
                            .host()
                                .hostname("api.huceo.com").parent()
                            .path()
                                .project("meinv/other").parent()
                            .method()
                                .name("GET").parent().parent()
                        .response()
                        .entity()
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).parent().parent()
                    .build() ;
            //@formatter:on
            return interfaceDefine;
        }

    }

}
