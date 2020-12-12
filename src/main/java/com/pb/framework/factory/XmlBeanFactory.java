package com.pb.framework.factory;

import com.pb.framework.proxy.ProxyBeanFactory;
import com.pb.framework.utils.ParseAnnotation;
import com.pb.framework.parse.XmlBean;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XmlBeanFactory implements BeanFactory {

    private static Map<String,Object> beans = new HashMap<>();
    private static Map<String,Method> urlMethodMap = new HashMap<>();
    private static Map<String,String> urlIdMap = new HashMap<>();

    public XmlBeanFactory() {
        initBeans();
    }

    public XmlBeanFactory(String config) {
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(config);
            XmlBean.load(resourceAsStream);
            initBeans();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initBeans(){
        try {
            beans = XmlBean.initBeans();
            //方法增强
            ProxyBeanFactory.proxy(beans);
            urlMethodMap = ParseAnnotation.parseRequestMapping();
            urlIdMap = ParseAnnotation.parseUrlMappingInstance(beans,urlMethodMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) {
        return beans.get(id);
    }

    @Override
    public Object getUrlBean(String url) {
        String id = urlIdMap.get(url);
        if (StringUtils.isNotEmpty(id)) {
            return beans.get(id);
        }
        return null;
    }
}
