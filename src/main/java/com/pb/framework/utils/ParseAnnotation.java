package com.pb.framework.utils;

import com.pb.framework.annotation.RequestMapping;
import com.pb.framework.parse.XmlBean;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseAnnotation {

    public static Map<String, Method> parseRequestMapping() throws Exception {
        //获取要解析的包
        String scanner = XmlBean.scanner();
        //获取包中的类
        List<Class<?>> classes = LoadClass.getClasses(scanner);
        //解析
        return parseClass(classes);
    }

    public static Map<String,Method> parseClass(List<Class<?>> classes) {
        Map<String, Method> mappingMap  = new HashMap<>();
        for (Class<?> item : classes){
            RequestMapping annotation = item.getAnnotation(RequestMapping.class);
            String classMapping = "";
            if (annotation != null) {
                classMapping = annotation.value();
            }
            //获取所有方法
            Method[] declaredMethods = item.getDeclaredMethods();
            for (Method method: declaredMethods) {
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                String methodMapping = methodAnnotation.value();
                String mappingUrl = "";
                if (classMapping.endsWith("/") && (StringUtils.isNotBlank(methodMapping) && methodMapping.startsWith("/"))){
                    mappingUrl = classMapping + methodMapping.substring(1);
                }else if (classMapping.endsWith("/") || (StringUtils.isNotBlank(methodMapping) && methodMapping.startsWith("/"))) {
                    mappingUrl = classMapping + methodMapping;
                }else if (!classMapping.endsWith("/") && !(StringUtils.isNotBlank(methodMapping) && methodMapping.startsWith("/"))) {
                    mappingUrl = classMapping + "/" + methodMapping;
                }
                mappingMap.put(mappingUrl,method);
            }
        }
        return mappingMap;
    }

    public static Map<String, String> parseUrlMappingInstance(Map<String, Object> beans, Map<String, Method> urlMethodMap) {
        Map<String,String> urlIdMap = new HashMap<>();
        for (Map.Entry<String,Method> entry : urlMethodMap.entrySet()) {
            Method method = entry.getValue();
            Class<?> aClass = method.getDeclaringClass();
            for(Map.Entry<String,Object> bean : beans.entrySet()) {
                if (bean.getValue().getClass() == aClass) {
                    urlIdMap.put(entry.getKey(),bean.getKey());
                    break;
                }
            }
        }
        return urlIdMap;
    }
}
