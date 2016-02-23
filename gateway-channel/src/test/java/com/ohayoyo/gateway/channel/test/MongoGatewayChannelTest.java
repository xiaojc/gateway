package com.ohayoyo.gateway.channel.test;

import com.ohayoyo.gateway.channel.GatewayChannel;
import com.ohayoyo.gateway.channel.test.model.TestPack;
import com.ohayoyo.gateway.mongo.model.MongoGatewayField;
import com.ohayoyo.gateway.mongo.model.*;
import com.ohayoyo.gateway.mongo.repository.MongoGatewayInterfaceRepository;
import com.ohayoyo.gateway.session.RestfulSessionRequestBuilder;
import com.ohayoyo.spring.config.profile.ProfileVersion;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@ContextConfiguration(locations = {
        "classpath:/conf/mongo/test-gateway-channel-mongo.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ProfileVersion.DEV_SNAPSHOT)
public class MongoGatewayChannelTest {

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
    private RestfulSessionRequestBuilder restfulSessionRequestBuilder;

    @Test
    public void testGatewayChannel() {
        logger.debug("{}", gatewayChannel);
    }

    @Test
    public void testConfigure() {
        logger.debug("test configure.");
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private MongoGatewayInterfaceRepository mongoGatewayInterfaceRepository;

    @Test
    public void testSave() {
        MongoGatewayInterface mongoGatewayInterface = new MongoGatewayInterface();
        mongoGatewayInterface.setKey("meinv");
        MongoGatewayRequest mongoGatewayRequest = new MongoGatewayRequest();
        Set<MongoGatewayProtocol> protocols = new HashSet<MongoGatewayProtocol>();
        MongoGatewayProtocol httpMongoGatewayProtocol = new MongoGatewayProtocol();
        httpMongoGatewayProtocol.setName(MongoGatewayProtocol.HTTP_NAME);
        protocols.add(httpMongoGatewayProtocol);
        mongoGatewayRequest.setProtocols(protocols);
        Set<MongoGatewayHost> hosts = new HashSet<MongoGatewayHost>();
        MongoGatewayHost mongoGatewayHost = new MongoGatewayHost();
        mongoGatewayHost.setHostname("api.huceo.com");
        hosts.add(mongoGatewayHost);
        mongoGatewayRequest.setHosts(hosts);
        MongoGatewayPath mongoGatewayPath = new MongoGatewayPath();
        mongoGatewayPath.setModule("meinv");
        mongoGatewayRequest.setPath(mongoGatewayPath);
        MongoGatewayQueries mongoGatewayQueries = new MongoGatewayQueries();
        Set<MongoGatewayField> fields = new HashSet<MongoGatewayField>();
        MongoGatewayField keyMongoGatewayField = new MongoGatewayField();
        keyMongoGatewayField.setName("key");
        keyMongoGatewayField.setType("string");
        keyMongoGatewayField.setNullable(false);
        keyMongoGatewayField.setDefaultValue("29e069ec39101eb669121554bf67024f");
        fields.add(keyMongoGatewayField);
        MongoGatewayField numMongoGatewayField = new MongoGatewayField();
        numMongoGatewayField.setName("num");
        numMongoGatewayField.setType("int");
        numMongoGatewayField.setNullable(false);
        numMongoGatewayField.setDefaultValue(10);
        fields.add(numMongoGatewayField);
        mongoGatewayQueries.setFields(fields);
        mongoGatewayRequest.setQueries(mongoGatewayQueries);
        Set<MongoGatewayMethod> methods = new HashSet<MongoGatewayMethod>();
        MongoGatewayMethod getMongoGatewayMethod = new MongoGatewayMethod();
        getMongoGatewayMethod.setName(MongoGatewayMethod.GET_NAME);
        methods.add(getMongoGatewayMethod);
        mongoGatewayRequest.setMethods(methods);
        MongoGatewayEntity mongoGatewayEntity = new MongoGatewayEntity();
        mongoGatewayEntity.setType("application/x-www-form-urlencoded");
        mongoGatewayRequest.setEntity(mongoGatewayEntity);
        MongoGatewayResponse mongoGatewayResponse = new MongoGatewayResponse();
        MongoGatewayEntity responseMongoGatewayEntity = new MongoGatewayEntity();
        responseMongoGatewayEntity.setType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        mongoGatewayResponse.setEntity(responseMongoGatewayEntity);
        mongoGatewayInterface.setRequest(mongoGatewayRequest);
        mongoGatewayInterface.setResponse(mongoGatewayResponse);
        mongoGatewayInterfaceRepository.save(mongoGatewayInterface);
    }

    @Test
    public void testApiHuceoComMeinvOther() throws Exception {
        String key = "meinv";
        TestPack testPack = gatewayChannel.channel(TestPack.class, key, restfulSessionRequestBuilder
                .requestQueries("key", "29e069ec39101eb669121554bf67024f")
                .requestQueries("num", "30")
                .build());
        logger.debug("{}", testPack);
    }

    @Test
    public void testApiHuceoComMeinvOther2() throws Exception {
        String key = "meinv";
        long start1 = System.currentTimeMillis();
        gatewayChannel.channel(TestPack.class, key);
        long end1 = System.currentTimeMillis();
        TestPack testPack = gatewayChannel.channel(TestPack.class, key);
        long end2 = System.currentTimeMillis();
        logger.debug("{}", testPack);
        System.out.println((end1 - start1) + ":" + (end2 - end1));
    }

    @Test
    public void testApiHuceoComMeinvOther3() throws Exception {
        String key = "meinv";
        TestPack testPack = gatewayChannel.channel(TestPack.class, key, restfulSessionRequestBuilder
                .requestQueries("num", "30")
                .requestQueries("word", "北京")
                .requestQueries("page", "1")
                .build());
        logger.debug("{}", testPack);
    }

}