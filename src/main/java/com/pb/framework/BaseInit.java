package com.pb.framework;

import com.pb.framework.parse.ParseFile;
import com.pb.framework.parse.XmlParseFile;
import com.pb.framework.utils.ParseAnnotation;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseInit extends HttpServlet {
    private ParseFile parseFile = new XmlParseFile();

    public static Map<String, Method> methodMap;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            methodMap = ParseAnnotation.parseRequestMapping();

            String contextLocation = config.getInitParameter("contextLocation");
            if (StringUtils.isNotEmpty(contextLocation)) {
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextLocation);
                parseFile.load(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
