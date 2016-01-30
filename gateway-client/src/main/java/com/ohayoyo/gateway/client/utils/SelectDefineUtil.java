package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.core.HostDefine;
import com.ohayoyo.gateway.define.core.MethodDefine;
import com.ohayoyo.gateway.define.core.ProtocolDefine;
import com.ohayoyo.gateway.define.core.UserDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Set;

public class SelectDefineUtil {

    private static boolean selectCompareRule(String select, String option) {
        if (!StringUtils.isEmpty(select) && !StringUtils.isEmpty(option)) {
            return select.equals(option);
        }
        return false;
    }

    private static boolean hasSelectOptions(String select, Set<String> options) {
        boolean isOption = false;
        if (!CollectionUtils.isEmpty(options)) {
            for (String option : options) {
                if (selectCompareRule(select, option)) {
                    isOption = true;
                    break;
                }
            }
        }
        return isOption;
    }

    public static ProtocolDefine selectProtocolDefine(String select, Set<ProtocolDefine> protocolDefines) {
        ProtocolDefine selectProtocolDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(protocolDefines))) {
            for (ProtocolDefine protocolDefine : protocolDefines) {
                Set<String> options = protocolDefine.getOptions();
                if (hasSelectOptions(select, options)) {
                    selectProtocolDefine = protocolDefine;
                    break;
                }
            }
        }
        if (null == selectProtocolDefine && (!CollectionUtils.isEmpty(protocolDefines))) {
            Iterator<ProtocolDefine> protocolDefineIterator = protocolDefines.iterator();
            if (protocolDefineIterator.hasNext()) {
                selectProtocolDefine = protocolDefineIterator.next();
            }
        }
        return selectProtocolDefine;
    }

    public static UserDefine selectUserDefine(String select, Set<UserDefine> userDefines) {
        UserDefine selectUserDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(userDefines))) {
            for (UserDefine userDefine : userDefines) {
                Set<String> options = userDefine.getOptions();
                if (hasSelectOptions(select, options)) {
                    selectUserDefine = userDefine;
                    break;
                }
            }
        }
        if (null == selectUserDefine && (!CollectionUtils.isEmpty(userDefines))) {
            Iterator<UserDefine> userDefineIterator = userDefines.iterator();
            if (userDefineIterator.hasNext()) {
                selectUserDefine = userDefineIterator.next();
            }
        }
        return selectUserDefine;
    }

    public static MethodDefine selectMethodDefine(String select, Set<MethodDefine> methodDefines) {
        MethodDefine selectMethodDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(methodDefines))) {
            for (MethodDefine methodDefine : methodDefines) {
                Set<String> options = methodDefine.getOptions();
                if (hasSelectOptions(select, options)) {
                    selectMethodDefine = methodDefine;
                    break;
                }
            }
        }
        if (null == selectMethodDefine && (!CollectionUtils.isEmpty(methodDefines))) {
            Iterator<MethodDefine> methodDefineIterator = methodDefines.iterator();
            if (methodDefineIterator.hasNext()) {
                selectMethodDefine = methodDefineIterator.next();
            }
        }
        return selectMethodDefine;
    }

    public static HostDefine selectHostDefine(String select, Set<HostDefine> hostDefines) {
        HostDefine selectHostDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(hostDefines))) {
            for (HostDefine hostDefine : hostDefines) {
                String option = hostDefine.getOption();
                if (selectCompareRule(select, option)) {
                    selectHostDefine = hostDefine;
                    break;
                }
            }
        }
        if (null == selectHostDefine && (!CollectionUtils.isEmpty(hostDefines))) {
            Iterator<HostDefine> hostDefineIterator = hostDefines.iterator();
            if (hostDefineIterator.hasNext()) {
                selectHostDefine = hostDefineIterator.next();
            }
        }
        return selectHostDefine;
    }

    public static Integer selectHostDefinePort(ProtocolDefine protocolDefine, HostDefine hostDefine) {
        return (!(hostDefine.getPort() != null && hostDefine.getPort() <= 0)) ? (ProtocolDefine.HTTPS.equals(protocolDefine.getName()) ? HostDefine.DEFAULT_HTTPS_PORT : HostDefine.DEFAULT_HTTP_PORT) : hostDefine.getPort();
    }

}
