package com.honeywen.push.controller;

import com.honeywen.push.entity.Account;
import com.honeywen.push.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/4/20
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/hello")
    public String test() {
        return "hello world!";
    }


    @GetMapping("/account")
    public List<Account> getAccount() {

        List<Account> list = accountService.findAll();
        
        return list;

    }

}
