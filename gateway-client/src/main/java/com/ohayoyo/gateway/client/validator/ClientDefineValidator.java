package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.exception.ValidatorException;
import com.ohayoyo.gateway.client.utils.ParameterDefineUtil;
import com.ohayoyo.gateway.client.utils.PathDefineUtil;
import com.ohayoyo.gateway.define.ParameterDefine;
import com.ohayoyo.gateway.define.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ClientDefineValidator implements GatewayDefineValidator {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientDefineValidator.class);

    private static final Set<String> CACHE = new HashSet<String>();

    public void validate(InterfaceDefine interfaceDefine) throws ValidatorException {

        LOGGER.debug("客户端定义验证器进行实际的验证处理 .");

        String key = interfaceDefine.getKey();

        if (StringUtils.isEmpty(key)) {
            ValidatorException.exception("接口定义字段[key]对象必须是非空以及非空值.");
        }

        if (CACHE.contains(key)) {
            return;
        }

        RequestDefine requestDefine = interfaceDefine.getRequest();
        if (ObjectUtils.isEmpty(requestDefine)) {
            ValidatorException.exception("请求定义对象是必须存在的.");
        }

        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        if (CollectionUtils.isEmpty(protocolDefines)) {
            ValidatorException.exception("请求协议定义集合是至少存在一个.");
        }

        for (ProtocolDefine protocolDefine : protocolDefines) {
            String protocolName = protocolDefine.getName();
            if (StringUtils.isEmpty(protocolName)) {
                ValidatorException.exception("请求协议定义集合中至少存在一个定义不合法,字段名称必须是非空以及非空值.");
            }
            if (!((ProtocolDefine.HTTP_NAME.equalsIgnoreCase(protocolName)) || (ProtocolDefine.HTTPS_NAME.equalsIgnoreCase(protocolName)))) {
                ValidatorException.exception("请求协议定义集合中至少存在一个定义不合法,字段名称的协议名称是不支持的,目前只支持协议HTTP/HTTPS(不区分大小写).错误的协议名称:%s", protocolName);
            }
        }

        Set<HostDefine> hostDefines = requestDefine.getHosts();
        if (CollectionUtils.isEmpty(hostDefines)) {
            ValidatorException.exception("请求主机定义集合是至少存在一个.");
        }

        for (HostDefine hostDefine : hostDefines) {
            String hostname = hostDefine.getHostname();
            if (StringUtils.isEmpty(hostname)) {
                ValidatorException.exception("请求主机定义集合中至少存在一个定义不合法,字段主机名必须是非空以及非空值.");
            }
        }

        PathDefine pathDefine = requestDefine.getPath();
        if (!ObjectUtils.isEmpty(pathDefine)) {
            Set<String> variableNames = PathDefineUtil.expandVariableNames(pathDefine);
            if (!CollectionUtils.isEmpty(variableNames)) {

                VariablesDefine variablesDefine = pathDefine.getVariables();
                if (ObjectUtils.isEmpty(variablesDefine)) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,然而不存在路径变量的定义.");
                }

                Set<ParameterDefine> parameterDefines = variablesDefine.getFields();
                if (CollectionUtils.isEmpty(parameterDefines)) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是没有存在相关的字段集合定义.");
                }

                if (ParameterDefineUtil.isExistEmptyParameterName(parameterDefines)) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合中存在空值的字段名称.");
                }

                int variableNamesLen = variableNames.size();
                int fieldDefinesLen = parameterDefines.size();
                if (variableNamesLen > fieldDefinesLen) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合实际的个数少于值中的变量个数,路径变量长度:%d,字段定义长度:%d.", variableNamesLen, fieldDefinesLen);
                }

                Set<String> fieldNames = ParameterDefineUtil.getParameterNames(parameterDefines);
                Set<String> notExistDefineVariableNames = new HashSet<String>();
                for (String variableName : variableNames) {
                    if (!fieldNames.contains(variableName)) {
                        notExistDefineVariableNames.add(variableName);
                    }
                }
                if (!CollectionUtils.isEmpty(notExistDefineVariableNames)) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合中缺少字段名称[%s].", notExistDefineVariableNames);
                }

            }
        }

//        QueriesDefine queriesDefine = requestDefine.getQueries();
//        if (!ObjectUtils.isEmpty(queriesDefine)) {
//
//        }

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
