package com.pb.framework.factory;

public interface BeanFactory {

    Object getBean(String id);

    Object getUrlBean(String url);
}
