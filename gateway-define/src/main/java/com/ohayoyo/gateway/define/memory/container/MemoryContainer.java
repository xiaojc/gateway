package com.ohayoyo.gateway.define.memory.container;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.define.utils.CollectionUtils;
import com.ohayoyo.gateway.define.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public class MemoryContainer implements GatewayContainer {

    private Map<String, InterfaceDefine> interfaceDefineMap;

    public Map<String, InterfaceDefine> getInterfaceDefineMap() {
        return this.interfaceDefineMap;
    }

    public MemoryContainer setInterfaceDefineMap(Map<String, InterfaceDefine> interfaceDefineMap) {
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
        if (CollectionUtils.isNotEmpty(this.interfaceDefineMap)) {
            if (StringUtils.isNotEmpty(interfaceDefineKey) && this.interfaceDefineMap.containsKey(interfaceDefineKey)) {
                return this.interfaceDefineMap.get(interfaceDefineKey);
            }
        }
        return null;
    }

    public MemoryContainer save(InterfaceDefine interfaceDefine) {
        doInterfaceDefineMap();
        if (null != interfaceDefine) {
            String key = interfaceDefine.getKey();
            if (StringUtils.isNotEmpty(key)) {
                this.interfaceDefineMap.put(key, interfaceDefine);
            }
        }
        return this;
    }

    @Override
    public GatewayContainer remove(InterfaceDefine interfaceDefine) {
        doInterfaceDefineMap();
        if (null != interfaceDefine) {
            String key = interfaceDefine.getKey();
            if (StringUtils.isNotEmpty(key)) {
                if (this.interfaceDefineMap.containsKey(key)) {
                    this.interfaceDefineMap.remove(key);
                }
            }
        }
        return this;
    }

}
