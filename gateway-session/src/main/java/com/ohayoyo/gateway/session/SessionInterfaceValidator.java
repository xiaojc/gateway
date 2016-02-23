package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.*;
import com.ohayoyo.gateway.utils.GatewayFieldUtils;
import com.ohayoyo.gateway.utils.GatewayPathUtils;
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
@SuppressWarnings("unchecked")
public class SessionInterfaceValidator extends AbstractGatewayAccessor implements GatewayInterfaceValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInterfaceValidator.class);

    private static final Set<String> CACHE = new HashSet<String>();

    public void validate(GatewayInterface gatewayInterface) throws VerifySessionException {

        String key = gatewayInterface.getKey();

        if (StringUtils.isEmpty(key)) {
            VerifySessionException.exception("接口定义字段[key]对象必须是非空以及非空值.");
        }

        if (CACHE.contains(key)) {
            return;
        }

        GatewayRequest request = gatewayInterface.getRequest();
        if (ObjectUtils.isEmpty(request)) {
            VerifySessionException.exception("请求定义对象是必须存在的.");
        }

        Set<GatewayProtocol> protocols = request.getProtocols();
        if (CollectionUtils.isEmpty(protocols)) {
            VerifySessionException.exception("请求协议定义集合是至少存在一个.");
        }

        for (GatewayProtocol protocol : protocols) {
            String name = protocol.getName();
            if (StringUtils.isEmpty(name)) {
                VerifySessionException.exception("请求协议定义集合中至少存在一个定义不合法,字段名称必须是非空以及非空值.");
            }
            if (!((GatewayProtocol.HTTP_NAME.equalsIgnoreCase(name)) || (GatewayProtocol.HTTPS_NAME.equalsIgnoreCase(name)))) {
                VerifySessionException.exception("请求协议定义集合中至少存在一个定义不合法,字段名称的协议名称是不支持的,目前只支持协议HTTP/HTTPS(不区分大小写).错误的协议名称:%s", name);
            }
        }

        Set<GatewayHost> hosts = request.getHosts();
        if (CollectionUtils.isEmpty(hosts)) {
            VerifySessionException.exception("请求主机定义集合是至少存在一个.");
        }

        for (GatewayHost host : hosts) {
            String hostname = host.getHostname();
            if (StringUtils.isEmpty(hostname)) {
                VerifySessionException.exception("请求主机定义集合中至少存在一个定义不合法,字段主机名必须是非空以及非空值.");
            }
        }

        GatewayPath path = request.getPath();
        if (!ObjectUtils.isEmpty(path)) {
            Set<String> variableNames = GatewayPathUtils.expandVariableNames(path);
            if (!CollectionUtils.isEmpty(variableNames)) {

                GatewayVariables variablesDefine = path.getVariables();
                if (ObjectUtils.isEmpty(variablesDefine)) {
                    VerifySessionException.exception("请求路径定义中的存在路径变量,然而不存在路径变量的定义.");
                }

                Set<GatewayField> fields = variablesDefine.getFields();
                if (CollectionUtils.isEmpty(fields)) {
                    VerifySessionException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是没有存在相关的字段集合定义.");
                }

                if (GatewayFieldUtils.isExistEmptyFieldName(fields)) {
                    VerifySessionException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合中存在空值的字段名称.");
                }

                int variableNamesLen = variableNames.size();
                int fieldsLen = fields.size();
                if (variableNamesLen > fieldsLen) {
                    VerifySessionException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合实际的个数少于值中的变量个数,路径变量长度:%d,字段定义长度:%d.", variableNamesLen, fieldsLen);
                }

                Set<String> fieldNames = GatewayFieldUtils.getFieldNames(fields);
                Set<String> notExistVariableNames = new HashSet<String>();
                for (String variableName : variableNames) {
                    if (!fieldNames.contains(variableName)) {
                        notExistVariableNames.add(variableName);
                    }
                }
                if (!CollectionUtils.isEmpty(notExistVariableNames)) {
                    VerifySessionException.exception("请求路径定义中的存在路径变量,这个路径变量对象存在,但是路径变量字段集合中缺少字段名称[%s].", notExistVariableNames);
                }

            }
        }

        GatewayQueries queries = request.getQueries();
        if (!ObjectUtils.isEmpty(queries)) {
            Set<GatewayField> fields = queries.getFields();
            if (CollectionUtils.isEmpty(fields)) {
                VerifySessionException.exception("请求查询定义中的存在查询对象,但是没有存在相关的字段集合定义.");
            }
            if (GatewayFieldUtils.isExistEmptyFieldName(fields)) {
                VerifySessionException.exception("请求查询定义中的存在查询对象,但是查询对象字段集合中存在空值的字段名称.");
            }
        }

        GatewayHeaders headers = request.getHeaders();
        if (!ObjectUtils.isEmpty(headers)) {
            Set<GatewayField> fields = headers.getFields();
            if (CollectionUtils.isEmpty(fields)) {
                VerifySessionException.exception("请求头定义中的存在查询对象,但是没有存在相关的字段集合定义.");
            }
            if (GatewayFieldUtils.isExistEmptyFieldName(fields)) {
                VerifySessionException.exception("请求头定义中的存在查询对象,但是头对象字段集合中存在空值的字段名称.");
            }
        }

        CACHE.add(key);
    }

}
