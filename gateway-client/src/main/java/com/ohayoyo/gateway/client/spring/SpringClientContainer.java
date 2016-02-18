package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.gateway.define.memory.container.MemoryGatewayContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringClientContainer.class);

    public static final String OVERRIDE_DELEGATE_GATEWAY_CONTAINER_NAME = "overrideDelegateGatewayContainer";

    @Autowired
    private ApplicationContext applicationContext;

    private GatewayContainer delegateGatewayContainer;

    @Override
    public void afterPropertiesSet() throws Exception {

        LOGGER.debug("初始化 SpringClientContainer Bean .");

        if (ObjectUtils.isEmpty(this.delegateGatewayContainer)) {

            LOGGER.debug("不存在委托网关容器 .");

            try {
                LOGGER.debug("加载定义 SpringClientContainer Bean {} .", OVERRIDE_DELEGATE_GATEWAY_CONTAINER_NAME);
                this.delegateGatewayContainer = applicationContext.getBean(OVERRIDE_DELEGATE_GATEWAY_CONTAINER_NAME, GatewayContainer.class);
                LOGGER.debug("加载成功 SpringClientContainer Bean {} .", OVERRIDE_DELEGATE_GATEWAY_CONTAINER_NAME);
            } catch (Exception e) {
                LOGGER.info("加载失败 SpringClientContainer Bean {} .", OVERRIDE_DELEGATE_GATEWAY_CONTAINER_NAME);
            }
        }
        if (ObjectUtils.isEmpty(this.delegateGatewayContainer)) {
            LOGGER.debug("手动初始化委托网关容器,使用内存容器 .");
            this.delegateGatewayContainer = new MemoryGatewayContainer();
        }
        try {
            LOGGER.debug("开始扫描实现InterfaceDefine接口了类型Bean");
            Map<String, InterfaceDefine> interfaceDefineMap = applicationContext.getBeansOfType(InterfaceDefine.class);
            LOGGER.debug("扫描到interfaceDefineMap:{}", interfaceDefineMap);
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
            LOGGER.info("扫描实现InterfaceDefine接口了类型Bean出现了错误:{}", ex.getMessage());
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
