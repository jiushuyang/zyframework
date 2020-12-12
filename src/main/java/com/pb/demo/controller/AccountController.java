package com.pb.demo.controller;

import com.pb.demo.model.Account;
import com.pb.demo.service.AccountService;
import com.pb.framework.annotation.RequestMapping;

@RequestMapping("/com/pb/accountController/")
public class AccountController {

    private AccountService accountService;

    @RequestMapping("one")
    public String one(){
        String one = accountService.one();
        System.out.println("one : "+one);
        return one;
    }

    @RequestMapping("getAccount")
    public Account getAccount(){
        Account account = new Account();
        account.setName("张三");
        account.setAge(18);
        return account;
    }

}
