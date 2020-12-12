package com.pb.framework.proxy;

import com.pb.framework.parse.XmlBean;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ProxyBeanFactory {

    /**
     * 增强操作
     */
    public static void proxy(Map<String,Object> beans) throws Exception {
        //获取对应前置增强节点信息
        Map<String, String> beforeMap = XmlBean.before();
        if (beforeMap == null) {
            return;
        }
        //获取指定的包
        String aPackage = beforeMap.get("package");
        //获取增强类实例
        Object beforeInstance = beans.get(beforeMap.get("ref"));
        Method beforeMethod = beforeInstance.getClass().getDeclaredMethod(beforeMap.get("method"));
        //增强方法
        for (Map.Entry<String,Object> entry: beans.entrySet()) {
            Object instance = entry.getValue();
            String packageName = instance.getClass().getPackage().getName();
            if (packageName.equals(aPackage)) {
                //创建代理对象
                Object proxyInstance = Proxy.newProxyInstance(instance.getClass().getClassLoader()
                        , instance.getClass().getInterfaces(), new BeanProxy(instance, beforeInstance, beforeMethod));
                //将beans中该实例替换为代理对象
                entry.setValue(proxyInstance);
                XmlBean.replaceProxy(beans,entry.getKey(),proxyInstance);
            }
        }
    }
}
