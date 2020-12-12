package com.pb.demo.service.impl;

import com.pb.demo.service.AccountService;

public class AccountServiceImpl implements AccountService {
    @Override
    public String one() {
        return "/WEB-INF/pages/one.jsp";
    }
}
