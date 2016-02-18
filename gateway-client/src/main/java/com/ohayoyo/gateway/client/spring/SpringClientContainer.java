package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.define.memory.container.MemoryContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class SpringClientContainer implements GatewayContainer, InitializingBean, ApplicationContextAware {

    public static final String OVERRIDE_DELEGATE_GATEWAY_CONTAINER_NAME = "overrideDelegateGatewayContainer";

    @Autowired
    private ApplicationContext applicationContext;

    private GatewayContainer delegateGatewayContainer;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (ObjectUtils.isEmpty(this.delegateGatewayContainer)) {
            try {
                this.delegateGatewayContainer = applicationContext.getBean(OVERRIDE_DELEGATE_GATEWAY_CONTAINER_NAME, GatewayContainer.class);
            } catch (Exception e) {
                //none
            }
        }
        if (ObjectUtils.isEmpty(this.delegateGatewayContainer)) {
            this.delegateGatewayContainer = new MemoryContainer();
        }
        //自动扫描InterfaceDefine类型Bean
        try {
            Map<String, InterfaceDefine> interfaceDefineMap = applicationContext.getBeansOfType(InterfaceDefine.class);
            if (!CollectionUtils.isEmpty(interfaceDefineMap)) {
                Set<Map.Entry<String, InterfaceDefine>> entries = interfaceDefineMap.entrySet();
                for (Map.Entry<String, InterfaceDefine> entry : entries) {
                    InterfaceDefine interfaceDefine = entry.getValue();
                    if (!ObjectUtils.isEmpty(interfaceDefine)) {
                        this.save(interfaceDefine);
                    }
                }
            }
        } catch (Exception ex) {
            //none
        }
    }

    @Override
    public InterfaceDefine query(String interfaceDefineKey) {
        return delegateGatewayContainer.query(interfaceDefineKey);
    }

    @Override
    public GatewayContainer save(InterfaceDefine interfaceDefine) {
        return delegateGatewayContainer.save(interfaceDefine);
    }

    @Override
    public GatewayContainer remove(InterfaceDefine interfaceDefine) {
        return delegateGatewayContainer.remove(interfaceDefine);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
