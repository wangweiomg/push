package com.honeywen.push.mapper;

import com.honeywen.push.entity.Account;

import java.util.List;

/**
 * @author wangwei
 * @date 2019/4/21
 */
public interface AccountMapper {
    
    List<Account> findAll();
}
