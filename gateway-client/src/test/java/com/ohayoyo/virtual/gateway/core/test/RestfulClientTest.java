package com.ohayoyo.virtual.gateway.core.test;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.restful.RestfulClient;
import com.ohayoyo.gateway.client.restful.RestfulDefine;
import com.ohayoyo.gateway.client.restful.RestfulRequest;
import com.ohayoyo.gateway.define.core.QueryDefine;
import com.ohayoyo.gateway.define.memory.*;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class RestfulClientTest extends SimpleTest {

    @Test
    public void testRestfulClient1() {

        RestfulClient restfulClient = new RestfulClient();

        logger.debug("{}", restfulClient);

    }

    @Test
    public void testRestfulClient2() throws GatewayException {

        RestfulClient restfulClient = new RestfulClient();

        Map<String, Object> requestQueries = Maps.newHashMap();
        requestQueries.put("num", "20");

        Map<String, Object> requestHeaders = Maps.newHashMap();

        requestHeaders.put("apikey", "6df781b1b4d9d3d31712fdd9df616032");

        GatewayRequest gatewayRequest = new RestfulRequest()
                .setRequestQueries(requestQueries)
                .setRequestHeaders(requestHeaders);

        GatewayDefine gatewayDefine = new RestfulDefine(
                new MemoryInterfaceDefine()
                        .setKey("vg_txapi_mvtp_meinv")
                        .setRequest(new MemoryRequestDefine()
                                .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("apis.baidu.com")))
                                .setPath(new MemoryPathDefine()
                                        .setModule("txapi")
                                        .setOperate("mvtp")
                                        .setResource("meinv")
                                )
                                .setQueries((Set) Sets.newHashSet(
                                        new MemoryQueryDefine()
                                                .setName("num")
                                                .setType(QueryDefine.STRING)
                                                .setRequired(false)
                                ))
                                .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                                .setHeaders((Set) Sets.newHashSet(
                                        new MemoryHeaderDefine()
                                                .setName("apikey")
                                                .setType(QueryDefine.STRING)
                                                .setRequired(true)
                                ))
                        )
        );

        restfulClient.session(gatewayRequest, gatewayDefine);

        logger.debug("{}", restfulClient);

    }

    @Test
    public void testRestfulClient3() throws GatewayException {

        RestfulClient restfulClient = new RestfulClient();

        Map<String, Object> requestQueries = Maps.newHashMap();
        requestQueries.put("num", "20");

        Map<String, Object> requestHeaders = Maps.newHashMap();

        requestHeaders.put("apikey", "6df781b1b4d9d3d31712fdd9df616032");

        GatewayRequest gatewayRequest = new RestfulRequest()
                .setResponseType(String.class)
                .setRequestQueries(requestQueries)
                .setRequestHeaders(requestHeaders);

        GatewayDefine gatewayDefine = new RestfulDefine(
                new MemoryInterfaceDefine()
                        .setKey("vg_txapi_mvtp_meinv")
                        .setRequest(new MemoryRequestDefine()
                                .setProtocols((Set) Sets.newHashSet(MemoryProtocolDefine.HTTP))
                                .setHosts((Set) Sets.newHashSet(new MemoryHostDefine().setHostname("apis.baidu.com")))
                                .setPath(new MemoryPathDefine()
                                        .setModule("txapi")
                                        .setOperate("mvtp")
                                        .setResource("meinv")
                                )
                                .setQueries((Set) Sets.newHashSet(
                                        new MemoryQueryDefine()
                                                .setName("num")
                                                .setType(QueryDefine.STRING)
                                                .setRequired(false)
                                ))
                                .setMethods((Set) Sets.newHashSet(MemoryMethodDefine.GET))
                                .setHeaders((Set) Sets.newHashSet(
                                        new MemoryHeaderDefine()
                                                .setName("apikey")
                                                .setType(QueryDefine.STRING)
                                                .setRequired(true)
                                ))
                        )
        );

        restfulClient.session(gatewayRequest, gatewayDefine);

        logger.debug("{}", restfulClient);

    }

}
