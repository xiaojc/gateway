package com.ohayoyo.gateway.test.restful;

import com.google.common.collect.Sets;
import com.ohayoyo.gateway.client.GatewayDefine;
import com.ohayoyo.gateway.client.GatewayException;
import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.GatewayResponse;
import com.ohayoyo.gateway.client.restful.RestfulGatewayClient;
import com.ohayoyo.gateway.client.restful.RestfulGatewayDefine;
import com.ohayoyo.gateway.client.restful.RestfulGatewayRequest;
import com.ohayoyo.gateway.client.restful.RestfulGatewayRequestBuilder;
import com.ohayoyo.gateway.define.memory.*;
import com.ohayoyo.gateway.http.DefaultHttpClientHandler;
import com.ohayoyo.gateway.http.HttpClientHandler;
import com.ohayoyo.gateway.test.model.TestPack;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

public class RestfulGatewayClientTest {

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

    @Test
    public void testRestfulClient0() throws GatewayException {
        RestfulGatewayClient restfulGatewayClient = new RestfulGatewayClient();
        HttpClientHandler httpClientHandler = new DefaultHttpClientHandler();
        httpClientHandler.setClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restfulGatewayClient.setHttpClientHandler(httpClientHandler);
        GatewayRequest gatewayRequest = restfulGatewayClient.newRestfulGatewayRequestBuilder()
                .requestQueries("key","29e069ec39101eb669121554bf67024f")
                .requestQueries("num",10)
                .build();
        GatewayDefine gatewayDefine = new RestfulGatewayDefine(
                new MemoryInterfaceDefine()
                        .setRequest(new MemoryRequestDefine()
                                        .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                        .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("api.huceo.com")))
                                        .setPath(new MemoryPathDefine()
                                                        .setModule("meinv")
                                                        .setResource("other")
                                        )
                                        .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                        )
                        .setResponse(new MemoryResponseDefine()
                                        .setEntity(new MemoryEntityDefine().setType(MediaType.APPLICATION_JSON_UTF8.toString()))
                        )
        );

        long startTime = System.currentTimeMillis();
        GatewayResponse<String> objectGatewayResponse = restfulGatewayClient.session(gatewayDefine, gatewayRequest);
        long endTime = System.currentTimeMillis();
        logger.debug("{}",(endTime-startTime));
        logger.debug("{}", objectGatewayResponse.getResponseBody());

    }


    @Test
    public void testRestfulClient1() throws GatewayException {
        RestfulGatewayClient restfulGatewayClient = new RestfulGatewayClient();
        MultiValueMap<String, String> requestQueries = new LinkedMultiValueMap<String, String>();
        requestQueries.set("key", "29e069ec39101eb669121554bf67024f");
        requestQueries.set("num", "10");
        GatewayRequest gatewayRequest = new RestfulGatewayRequest()
                .setRequestQueries(requestQueries);
        GatewayDefine gatewayDefine = new RestfulGatewayDefine(
                new MemoryInterfaceDefine()
                        .setRequest(new MemoryRequestDefine()
                                        .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                        .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("api.huceo.com")))
                                        .setPath(new MemoryPathDefine()
                                                        .setModule("meinv")
                                                        .setResource("other")
                                        )
                                        .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                        )
                        .setResponse(new MemoryResponseDefine()
                                        .setEntity(new MemoryEntityDefine().setType(MediaType.APPLICATION_JSON_UTF8.toString()))
                        )
        );

        GatewayResponse<Map> objectGatewayResponse = restfulGatewayClient.session(Map.class, gatewayDefine, gatewayRequest);

        logger.debug("{}", objectGatewayResponse.getResponseBody());

    }

    @Test
    public void testRestfulClient2() throws GatewayException {
        RestfulGatewayClient restfulGatewayClient = new RestfulGatewayClient();
        HttpClientHandler httpClientHandler = new DefaultHttpClientHandler();
        httpClientHandler.setClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restfulGatewayClient.setHttpClientHandler(httpClientHandler);
        MultiValueMap<String, String> requestQueries = new LinkedMultiValueMap<String, String>();
        requestQueries.set("key", "29e069ec39101eb669121554bf67024f");
        requestQueries.set("num", "10");
        GatewayRequest gatewayRequest = new RestfulGatewayRequest()
                .setRequestQueries(requestQueries);
        GatewayDefine gatewayDefine = new RestfulGatewayDefine(
                new MemoryInterfaceDefine()
                        .setRequest(new MemoryRequestDefine()
                                        .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                        .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("api.huceo.com")))
                                        .setPath(new MemoryPathDefine()
                                                        .setModule("meinv")
                                                        .setResource("other")
                                        )
                                        .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                        )
                        .setResponse(new MemoryResponseDefine()
                                        .setEntity(new MemoryEntityDefine().setType(MediaType.APPLICATION_JSON_UTF8.toString()))
                        )
        );
        long startTime = System.currentTimeMillis();
        GatewayResponse<TestPack> objectGatewayResponse = restfulGatewayClient.session(TestPack.class, gatewayDefine, gatewayRequest);
        long endTime = System.currentTimeMillis();
        logger.debug("{}",(endTime-startTime));
        logger.debug("{}", objectGatewayResponse.getResponseBody());
    }

    @Test
    public void testRestfulClient3() throws GatewayException {
        RestfulGatewayClient restfulGatewayClient = new RestfulGatewayClient();
        GatewayRequest gatewayRequest = new RestfulGatewayRequest();
        //http://t1.27270.com/uploads/tu/201508/05/slt.jpg
        GatewayDefine gatewayDefine = new RestfulGatewayDefine(
                new MemoryInterfaceDefine()
                        .setKey("vg_txapi_mvtp_meinv")
                        .setRequest(new MemoryRequestDefine()
                                        .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                        .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("t1.27270.com")))
                                        .setPath(new MemoryPathDefine()
                                                        .setModule("uploads")
                                                        .setResource("tu/201508/05/slt.jpg")
                                        )
                                        .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                        )
        );

        GatewayResponse<BufferedImage> objectGatewayResponse = restfulGatewayClient.session(BufferedImage.class, gatewayDefine, gatewayRequest);

        logger.debug("{}", objectGatewayResponse.getResponseBody());

    }

    @Test
    public void testRestfulClient4() throws GatewayException {
        RestfulGatewayClient restfulGatewayClient = new RestfulGatewayClient();
        GatewayRequest gatewayRequest = new RestfulGatewayRequest();
        GatewayDefine gatewayDefine = new RestfulGatewayDefine(
                new MemoryInterfaceDefine()
                        .setRequest(new MemoryRequestDefine()
                                        .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                        .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("repo.ohayoyo.com")))
                                        .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                        )
        );
        GatewayResponse<String> objectGatewayResponse = restfulGatewayClient.session(gatewayDefine, gatewayRequest);
        logger.debug("{}", objectGatewayResponse.getResponseBody());

    }

}
