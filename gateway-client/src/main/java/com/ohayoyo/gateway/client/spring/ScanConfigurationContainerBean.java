package com.ohayoyo.gateway.client.spring;

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
public class ScanConfigurationContainerBean extends MemoryGatewayContainer implements InitializingBean, ApplicationContextAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientChannelBean.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
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
            LOGGER.info("扫描实现InterfaceDefine接口了类型Bean出现了错误:{}", ex.getMessage());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
