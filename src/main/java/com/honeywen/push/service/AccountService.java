package com.honeywen.push.service;

import com.honeywen.push.entity.Account;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/4/20
 */
public interface AccountService {
    
    List<Account> findAll();
}
