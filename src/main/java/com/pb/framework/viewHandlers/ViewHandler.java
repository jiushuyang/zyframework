package com.pb.framework.viewHandlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewHandler {

    default void printView(HttpServletResponse response, Object result) {

    }

    default void forwardView(HttpServletRequest request, HttpServletResponse response, Object result){}

}
