package com.ohayoyo.gateway.test;

import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.restful.builder.RestfulRequestBuilder;
import com.ohayoyo.gateway.test.model.TestPack;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {
        "classpath:/conf/gateway-channel-bean.xml",
        "classpath:/interfaces/**/*.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class GatewayChannelTest0 {

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
                .requestQueries("num", "30")
                .build());
        logger.debug("{}", testPack);
    }

    @Test
    public void testApiHuceoComMeinvOther2() throws Exception {
        String key = "api.huceo.com/meinv/other";
        long start1 = System.currentTimeMillis();
        gatewayChannel.channel(TestPack.class, key);
        long end1 = System.currentTimeMillis();
        TestPack testPack = gatewayChannel.channel(TestPack.class, key);
        long end2 = System.currentTimeMillis();
        logger.debug("{}", testPack);
        System.out.println((end1-start1)+":"+(end2-end1));
    }

    @Test
    public void testApiHuceoComMeinvOther3() throws Exception {
        String key = "api.huceo.com/meinv/other";
        TestPack testPack = gatewayChannel.channel(TestPack.class, key, restfulRequestBuilder
                .requestQueries("num", "30")
                .build());
        logger.debug("{}", testPack);
    }

}
