package com.ohayoyo.gateway.container.test;

import com.ohayoyo.gateway.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.spring.test.junit4.SpringJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {
        "classpath:/spring/springGatewayContainerTest.xml"
})
public class GatewayContainerBeanTest extends SpringJunit4Test {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testLoadConfig() {
        String testKey = "api.huceo.com/meinv/other";
        GatewayContainer gatewayContainer = applicationContext.getBean("gatewayContainer", GatewayContainer.class);
        InterfaceDefine interfaceDefine = gatewayContainer.query(testKey);
        logger.debug("test ok");
    }

}
