package com.pb.framework.adapter;

import com.pb.framework.viewHandlers.ForwardViewHandler;
import com.pb.framework.viewHandlers.PrintViewHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAdapter implements View {
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response, Object result) {
        if (result instanceof String){
            ForwardViewHandler forwardViewHandler = new ForwardViewHandler();
            forwardViewHandler.forwardView(request, response, result);
        }else {
            PrintViewHandler printViewHandler = new PrintViewHandler();
            printViewHandler.printView(response, result);
        }
    }
}
