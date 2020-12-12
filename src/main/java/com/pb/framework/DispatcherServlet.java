package com.pb.framework;

import com.pb.framework.adapter.ViewAdapter;
import com.pb.framework.factory.XmlBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends BaseInit {

    /**
     * 获取请求url
     * 根据请求url 获取对应Method并反射调用
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String requestURI = req.getRequestURI();
            Method method = methodMap.get(requestURI);
            XmlBeanFactory xmlBeanFactory = new XmlBeanFactory();
            Object instance = xmlBeanFactory.getUrlBean(requestURI);
            Object result = method.invoke(instance);
            if (result != null) {
                //执行渲染
                ViewAdapter viewAdapter = new ViewAdapter();
                viewAdapter.render(req,res,result);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
