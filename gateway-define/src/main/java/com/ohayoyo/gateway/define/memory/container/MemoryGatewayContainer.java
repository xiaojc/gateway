package com.ohayoyo.gateway.define.memory.container;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public class MemoryGatewayContainer implements GatewayContainer {

    private Map<String, InterfaceDefine> interfaceDefineMap;

    public Map<String, InterfaceDefine> getInterfaceDefineMap() {
        return this.interfaceDefineMap;
    }

    public MemoryGatewayContainer setInterfaceDefineMap(Map<String, InterfaceDefine> interfaceDefineMap) {
        this.doInterfaceDefineMap();
        this.interfaceDefineMap.putAll(interfaceDefineMap);
        return this;
    }

    protected void doInterfaceDefineMap() {
        if (CollectionUtils.isEmpty(this.interfaceDefineMap)) {
            this.interfaceDefineMap = new HashMap<String, InterfaceDefine>();
        }
    }

    @Override
    public InterfaceDefine query(String interfaceDefineKey) {
        if (!CollectionUtils.isEmpty(this.interfaceDefineMap)) {
            if ((!StringUtils.isEmpty(interfaceDefineKey)) && this.interfaceDefineMap.containsKey(interfaceDefineKey)) {
                return this.interfaceDefineMap.get(interfaceDefineKey);
            }
        }
        return null;
    }

    public MemoryGatewayContainer save(InterfaceDefine interfaceDefine) {
        doInterfaceDefineMap();
        if (!ObjectUtils.isEmpty(interfaceDefine)) {
            String key = interfaceDefine.getKey();
            if (!StringUtils.isEmpty(key)) {
                this.interfaceDefineMap.put(key, interfaceDefine);
            }
        }
        return this;
    }

    @Override
    public GatewayContainer remove(InterfaceDefine interfaceDefine) {
        doInterfaceDefineMap();
        if (!ObjectUtils.isEmpty(interfaceDefine)) {
            String key = interfaceDefine.getKey();
            if (!StringUtils.isEmpty(key)) {
                if (this.interfaceDefineMap.containsKey(key)) {
                    this.interfaceDefineMap.remove(key);
                }
            }
        }
        return this;
    }

}
