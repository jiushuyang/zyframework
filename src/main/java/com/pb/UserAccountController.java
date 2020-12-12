package com.pb;

import com.pb.framework.annotation.RequestMapping;

@RequestMapping("/pb/account/")
public class UserAccountController {

    @RequestMapping("one")
    public String one(){

        return "/WEB-INF/pages/one.jsp";
    }
}
