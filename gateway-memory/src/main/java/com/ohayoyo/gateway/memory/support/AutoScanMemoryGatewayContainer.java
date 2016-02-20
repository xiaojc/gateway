package com.ohayoyo.gateway.memory.support;

import com.ohayoyo.gateway.define.support.AutoScanGatewayContainer;
import com.ohayoyo.gateway.memory.container.MemoryGatewayContainer;
import com.ohayoyo.gateway.memory.http.MemoryGatewayInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Set;

public class AutoScanMemoryGatewayContainer extends MemoryGatewayContainer implements AutoScanGatewayContainer<MemoryGatewayInterface> {

    public static final Logger LOGGER = LoggerFactory.getLogger(AutoScanMemoryGatewayContainer.class);

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.autoScanGatewayInterfaces();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void autoScanGatewayInterfaces() {
        try {
            Map<String, MemoryGatewayInterface> memoryGatewayInterfaceMap = applicationContext.getBeansOfType(MemoryGatewayInterface.class);
            if (!CollectionUtils.isEmpty(memoryGatewayInterfaceMap)) {
                Set<Map.Entry<String, MemoryGatewayInterface>> entries = memoryGatewayInterfaceMap.entrySet();
                for (Map.Entry<String, MemoryGatewayInterface> entry : entries) {
                    MemoryGatewayInterface interfaces = entry.getValue();
                    if (!ObjectUtils.isEmpty(interfaces)) {
                        this.save(interfaces);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.info("自动扫描错误,信息:{}", ex.getMessage());
        }
    }

}
