package com.srytzj.service.accountservice;

import com.srytzj.mapper.account.AccountMapper;
import com.srytzj.domain.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sry
 * @description
 * @date 2022/2/18 10:51 上午
 * @Version 1.0
 */
@Service
public class AccountService implements AccountMapper {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account login(String userName, String password) {
        return accountMapper.login(userName, password);
    }

    @Override
    public Account getByUserName(String id) {
        return accountMapper.getByUserName(id);
    }
}
