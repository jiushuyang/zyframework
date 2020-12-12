package com.pb.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BeanProxy implements InvocationHandler {

    private Object instance;

    private Object beforeInstance;

    private Method beforeMethod;

    public BeanProxy(Object instance, Object beforeInstance, Method beforeMethod) {
        this.instance = instance;
        this.beforeInstance = beforeInstance;
        this.beforeMethod = beforeMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //前置增强
        beforeMethod.invoke(beforeInstance);
        return method.invoke(instance);
    }
}
