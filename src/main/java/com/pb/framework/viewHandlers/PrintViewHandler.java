package com.pb.framework.viewHandlers;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class PrintViewHandler implements ViewHandler {

    @Override
    public void printView(HttpServletResponse response, Object result) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
