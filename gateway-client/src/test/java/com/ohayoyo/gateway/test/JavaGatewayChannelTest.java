package com.ohayoyo.gateway.test;

import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.client.spring.SpringClientConfig;
import com.ohayoyo.gateway.define.Builders;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.test.model.TestPack;
import com.ohayoyo.spring.test.junit4.SpringJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        SpringClientConfig.class,
        JavaGatewayChannelTest.TestConfig.class
})
public class JavaGatewayChannelTest extends SpringJunit4Test {

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
                                .name("http").then()
                            .host()
                                .hostname("api.huceo.com").then()
                            .path()
                                .project("meinv/other").then()
                            .method()
                                .name("GET").then().then()
                        .response()
                        .entity()
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).then().then()
                    .build() ;
            //@formatter:on
            return interfaceDefine;
        }

    }

}
