package com.srytzj.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sry
 * @description
 * @date 2022/2/18 11:06 上午
 * @Version 1.0
 */
@Data
@Getter
@Setter
public class Account {

    private String id;

    private String userName;

    private String passWord;

    private String realName;
}
