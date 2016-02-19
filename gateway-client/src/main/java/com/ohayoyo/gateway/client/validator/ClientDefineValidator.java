package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.AbstractContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.exception.ValidatorException;
import com.ohayoyo.gateway.client.utils.ParameterDefineUtils;
import com.ohayoyo.gateway.client.utils.PathDefineUtils;
import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.http.*;
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
public class ClientDefineValidator extends AbstractContextAccessor implements GatewayDefineValidator {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientDefineValidator.class);

    private static final Set<String> CACHE = new HashSet<String>();

    public void validate(InterfaceDefine interfaceDefine) throws ValidatorException {

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
            Set<String> variableNames = PathDefineUtils.expandVariableNames(pathDefine);
            if (!CollectionUtils.isEmpty(variableNames)) {

                VariablesDefine variablesDefine = pathDefine.getVariables();
                if (ObjectUtils.isEmpty(variablesDefine)) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,然而不存在路径变量的定义.");
                }

                Set<Parameter> parameters = variablesDefine.getParameters();
                if (CollectionUtils.isEmpty(parameters)) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是没有存在相关的字段集合定义.");
                }

                if (ParameterDefineUtils.isExistEmptyParameterName(parameters)) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合中存在空值的字段名称.");
                }

                int variableNamesLen = variableNames.size();
                int fieldDefinesLen = parameters.size();
                if (variableNamesLen > fieldDefinesLen) {
                    ValidatorException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合实际的个数少于值中的变量个数,路径变量长度:%d,字段定义长度:%d.", variableNamesLen, fieldDefinesLen);
                }

                Set<String> fieldNames = ParameterDefineUtils.getParameterNames(parameters);
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

        QueriesDefine queriesDefine = requestDefine.getQueries();
        if (!ObjectUtils.isEmpty(queriesDefine)) {
            Set<Parameter> parameters = queriesDefine.getParameters();
            if (CollectionUtils.isEmpty(parameters)) {
                ValidatorException.exception("请求查询定义中的存在查询对象,但是没有存在相关的字段集合定义.");
            }
            if (ParameterDefineUtils.isExistEmptyParameterName(parameters)) {
                ValidatorException.exception("请求查询定义中的存在查询对象,但是查询对象字段集合中存在空值的字段名称.");
            }
        }

        HeadersDefine requestHeadersDefine = requestDefine.getHeaders();
        if (!ObjectUtils.isEmpty(requestHeadersDefine)) {
            Set<Parameter> parameters = requestHeadersDefine.getParameters();
            if (CollectionUtils.isEmpty(parameters)) {
                ValidatorException.exception("请求头定义中的存在查询对象,但是没有存在相关的字段集合定义.");
            }
            if (ParameterDefineUtils.isExistEmptyParameterName(parameters)) {
                ValidatorException.exception("请求头定义中的存在查询对象,但是头对象字段集合中存在空值的字段名称.");
            }
        }

        //EntityDefine requestEntityDefine = requestDefine.getEntity();
        //请求实体目前是非必须检查

        //响应目前是非必须检查
        //ResponseDefine responseDefine = interfaceDefine.getResponse();

        //Set<StatusDefine>  statusDefines = responseDefine.getStatuses() ;
        //响应状态集合目前是非必须检查

        //HeadersDefine responseHeadersDefine = responseDefine.getHeaders() ;
        //响应头目前是非必须检查

        //EntityDefine responseEntityDefine = responseDefine.getEntity();
        //响应实体目前是非必须检查

        CACHE.add(key);
    }

}
