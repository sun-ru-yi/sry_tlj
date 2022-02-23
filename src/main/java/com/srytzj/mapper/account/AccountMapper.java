package com.srytzj.mapper.account;

import com.srytzj.domain.Account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface AccountMapper {


    public Account login(@Param("userName") String userName,@Param("passWord") String  password);

    public Account getByUserName(@Param("id") String id);



}
