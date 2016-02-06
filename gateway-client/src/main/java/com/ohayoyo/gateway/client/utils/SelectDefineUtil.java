package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.HostDefine;
import com.ohayoyo.gateway.define.MethodDefine;
import com.ohayoyo.gateway.define.ProtocolDefine;
import com.ohayoyo.gateway.define.UserDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Set;

/**
 * 选择定义的作用域工具
 */
public class SelectDefineUtil {

    /**
     * 选择作用域比较规则
     *
     * @param select 选择定义的作用域
     * @param option 可选定义的作用域
     * @return 返回是否比较一样
     */
    private static boolean selectCompareRule(String select, String option) {
        if (!StringUtils.isEmpty(select) && !StringUtils.isEmpty(option)) {
            return select.equals(option);
        }
        return false;
    }

    /**
     * 是否存在作用域比较
     *
     * @param select  选择定义的作用域
     * @param options 可选定义的作用域集合
     * @return 返回是否存在作用域比较
     */
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

    /**
     * 给定一个协议定义集合就会根据给定选择定义的作用域,选择合适的协议定义,另外需要注意的是如果不存在或者都拥有同一个作用域的就会随机选择其中的一个
     *
     * @param select          选择定义的作用域
     * @param protocolDefines 协议定义集合
     * @return 返回选择定义的作用域选择的协议定义
     */
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

    /**
     * 给定一个用户定义集合就会根据给定选择定义的作用域,选择合适的用户定义,另外需要注意的是如果不存在或者都拥有同一个作用域的就会随机选择其中的一个
     * <p>
     * 注意:
     * <b>暂时不支持</b>
     * </p>
     *
     * @param select      选择定义的作用域
     * @param userDefines 用户定义集合
     * @return 返回选择定义的作用域选择的用户定义
     */
    @Deprecated
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

    /**
     * 给定一个方法定义集合就会根据给定选择定义的作用域,选择合适的方法定义,另外需要注意的是如果不存在或者都拥有同一个作用域的就会随机选择其中的一个
     *
     * @param select        选择定义的作用域
     * @param methodDefines 方法定义集合
     * @return 返回选择定义的作用域选择的方法定义
     */
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

    /**
     * 给定一个主机定义集合就会根据给定选择定义的作用域,选择合适的主机定义,另外需要注意的是如果不存在或者都拥有同一个作用域的就会随机选择其中的一个
     *
     * @param select      选择定义的作用域
     * @param hostDefines 主机定义集合
     * @return 返回选择定义的作用域选择的主机定义
     */
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

    /**
     * 选择主机定义的端口,如果没有定义会根据协议来进行选择默认的端口,详细看{@link HostDefine#DEFAULT_HTTP_PORT},{@link HostDefine#DEFAULT_HTTPS_PORT}.
     *
     * @param protocolDefine 协议定义
     * @param hostDefine     主机定义
     * @return 返回主机端口
     */
    public static Integer selectHostDefinePort(ProtocolDefine protocolDefine, HostDefine hostDefine) {
        return (!(hostDefine.getPort() != null && hostDefine.getPort() <= 0)) ? (ProtocolDefine.HTTPS_NAME.equals(protocolDefine.getName()) ? HostDefine.DEFAULT_HTTPS_PORT : HostDefine.DEFAULT_HTTP_PORT) : hostDefine.getPort();
    }

}
