package com.pb.framework.parse;

import java.io.InputStream;

public class XmlParseFile extends ParseFile {


    @Override
    public void load(InputStream is) throws Exception {
        XmlBean.load(is);
    }
}
