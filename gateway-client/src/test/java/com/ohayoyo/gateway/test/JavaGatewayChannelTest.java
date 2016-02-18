package com.ohayoyo.gateway.test;

import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.client.spring.SpringClientConfig;
import com.ohayoyo.gateway.define.core.DefineBuilders;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.gateway.test.model.TestPack;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {
        SpringClientConfig.class,
        JavaGatewayChannelTest.TestConfig.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public class JavaGatewayChannelTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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

    @Test
    public void testApiHuceoComMeinvOther2() throws Exception {
        String key = "api.huceo.com/meinv/other";
        TestPack testPack = gatewayChannel.channel(TestPack.class, key, restfulRequestBuilder
                .requestQueries("num", "30")
                .build());
        logger.debug("{}", testPack);
    }

    @Test
    public void testApiHuceoComMeinvOther3() throws Exception {
        String key = "api.huceo.com/meinv/other";
        TestPack testPack = gatewayChannel.channel(TestPack.class, key);
        logger.debug("{}", testPack);
    }

    @Configuration
    public static class TestConfig {

        @Bean
        public InterfaceDefine apiHuceoComMeinvOther() {
            //@formatter:off
            InterfaceDefine interfaceDefine = DefineBuilders
                    .memory()
                        .key("api.huceo.com/meinv/other")
                        .request()
                            .protocol()
                                .name("http").then()
                            .host()
                                .hostname("api.huceo.com").then()
                            .path()
                                .project("meinv/other").then()
                            .queries()
                                .parameter()
                                    .name("key")
                                    .dataType("STRING")
                                    .defaultValue("29e069ec39101eb669121554bf67024f").then()
                                .parameter()
                                    .name("num")
                                    .dataType("INT")
                                    .defaultValue("10").then()
                                .then()
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
