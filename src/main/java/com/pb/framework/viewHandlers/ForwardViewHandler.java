package com.pb.framework.viewHandlers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardViewHandler implements ViewHandler {

    @Override
    public void forwardView(HttpServletRequest request, HttpServletResponse response, Object result) {
        try {
            request.getRequestDispatcher(result.toString()).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
