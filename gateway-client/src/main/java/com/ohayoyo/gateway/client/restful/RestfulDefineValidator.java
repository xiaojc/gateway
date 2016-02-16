package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.DefineValidator;
import com.ohayoyo.gateway.client.ValidatorException;
import com.ohayoyo.gateway.define.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class RestfulDefineValidator implements DefineValidator {

    private static final Set<String> CACHE = new HashSet<String>();

    @Override
    public boolean supports(Class<?> clazz) {
        if (null != clazz) {
            return clazz.isAssignableFrom(InterfaceDefine.class);
        }
        return false;
    }

    public void validate(Object target) throws ValidatorException {

        InterfaceDefine interfaceDefine = (InterfaceDefine) target;

        String key = interfaceDefine.getKey();

        if (StringUtils.isEmpty(key)) {
            throw new ValidatorException("接口定义字段[key]对象必须是非空以及非空值.");
        }

        if (CACHE.contains(key)) {
            return;
        }

        RequestDefine requestDefine = interfaceDefine.getRequest();
        if (null == requestDefine) {
            throw new ValidatorException("请求定义对象是必须存在的.");
        }

        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        if (CollectionUtils.isEmpty(protocolDefines)) {
            throw new ValidatorException("请求协议定义集合是至少存在一个.");
        }

        for (ProtocolDefine protocolDefine : protocolDefines) {
            String protocolName = protocolDefine.getName();
            if (StringUtils.isEmpty(protocolName)) {
                throw new ValidatorException("请求协议定义集合中至少存在一个定义不合法,字段名称必须是非空以及非空值.");
            }
            if ((!ProtocolDefine.HTTP_NAME.equalsIgnoreCase(protocolName) || (!ProtocolDefine.HTTPS_NAME.equalsIgnoreCase(protocolName)))) {
                throw new ValidatorException("请求协议定义集合中至少存在一个定义不合法,字段名称的协议名称是不支持的,目前只支持协议HTTP/HTTPS(不区分大小写).");
            }
        }

        Set<HostDefine> hostDefines = requestDefine.getHosts();
        if (CollectionUtils.isEmpty(hostDefines)) {
            throw new ValidatorException("请求主机定义集合是至少存在一个.");
        }

        for (HostDefine hostDefine : hostDefines) {
            String hostname = hostDefine.getHostname();
            if (StringUtils.isEmpty(hostname)) {
                throw new ValidatorException("请求主机定义集合中至少存在一个定义不合法,字段主机名必须是非空以及非空值.");
            }
        }

        PathDefine pathDefine = requestDefine.getPath();
        if (null != pathDefine) {

        }

        //QueriesDefine queriesDefine = requestDefine.getQueries();
        //查询参数不进行检查

        //HeadersDefine requestHeadersDefine = requestDefine.getHeaders() ;
        //请求头不进行检查

        //EntityDefine requestEntityDefine = requestDefine.getEntity();
        //请求实体不进行检查

        //响应定义不进行检查
        //ResponseDefine responseDefine = interfaceDefine.getResponse();
        //if (null == responseDefine) {
        //    throw new GatewayException("响应定义对象是必须存在的.");
        //}

        //Set<StatusDefine>  statusDefines = responseDefine.getStatuses() ;
        //响应状态集合不进行检查

        //HeadersDefine responseHeadersDefine = responseDefine.getHeaders() ;
        //响应头不进行检查

        //EntityDefine responseEntityDefine = responseDefine.getEntity();
        //响应实体不进行检查

        CACHE.add(key);
    }

}
