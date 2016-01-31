package com.ohayoyo.gateway.test;

import com.google.common.collect.Sets;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.client.restful.RestfulClient;
import com.ohayoyo.gateway.client.restful.RestfulDefine;
import com.ohayoyo.gateway.client.restful.RestfulRequest;
import com.ohayoyo.gateway.define.memory.*;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Set;

public class RestfulClientTest {

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
    public void testRestfulClient1() throws GatewayException {

        RestfulClient restfulClient = new RestfulClient();

        MultiValueMap<String, String> requestQueries = new LinkedMultiValueMap<String, String>();
        requestQueries.set("key", "29e069ec39101eb669121554bf67024f");
        requestQueries.set("num", "10");

        GatewayRequest gatewayRequest = new RestfulRequest()
                .setRequestQueries(requestQueries);

        GatewayDefine gatewayDefine = new RestfulDefine(
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
        );

        GatewayResponse<Map> objectGatewayResponse = restfulClient.session(Map.class, gatewayDefine, gatewayRequest);

        logger.debug("{}", objectGatewayResponse.getResponseEntity());

    }

    @Test
    public void testRestfulClient2() throws GatewayException {

        RestfulClient restfulClient = new RestfulClient();

        MultiValueMap<String, String> requestQueries = new LinkedMultiValueMap<String, String>();
        requestQueries.set("key", "29e069ec39101eb669121554bf67024f");
        requestQueries.set("num", "10");

        GatewayRequest gatewayRequest = new RestfulRequest()
                .setRequestQueries(requestQueries);

        GatewayDefine gatewayDefine = new RestfulDefine(
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
        );

        GatewayResponse<TestPack> objectGatewayResponse = restfulClient.session(TestPack.class, gatewayDefine, gatewayRequest);

        logger.debug("{}", objectGatewayResponse.getResponseEntity());

    }

    @Test
    public void testRestfulClient3() throws GatewayException {

        RestfulClient restfulClient = new RestfulClient();

        GatewayRequest gatewayRequest = new RestfulRequest();
        //http://t1.27270.com/uploads/tu/201508/05/slt.jpg
        GatewayDefine gatewayDefine = new RestfulDefine(
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

        GatewayResponse<Object> objectGatewayResponse = restfulClient.session(gatewayDefine, gatewayRequest);

        logger.debug("{}", objectGatewayResponse.getResponseEntity());

    }

    @Test
    public void testRestfulClient4() throws GatewayException {
        RestfulClient restfulClient = new RestfulClient();
        GatewayRequest gatewayRequest = new RestfulRequest();
        GatewayDefine gatewayDefine = new RestfulDefine(
                new MemoryInterfaceDefine()
                        .setRequest(new MemoryRequestDefine()
                                .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("repo.ohayoyo.com")))
                                .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                        )
        );
        GatewayResponse<Object> objectGatewayResponse = restfulClient.session(gatewayDefine, gatewayRequest);
        Object object = objectGatewayResponse.getResponseEntity();
        ConversionService conversionService = restfulClient.getGatewayConfig().getConversionService();
        if (conversionService.canConvert(object.getClass(), String.class)) {
            logger.debug("{}", new String((byte[]) object));
        }
        logger.debug("{}", objectGatewayResponse.getResponseEntity());

    }

}
