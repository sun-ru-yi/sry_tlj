package com.srytzj.exception;

/**
 * @author sry
 * @description
 * @date 2022/2/18 10:44 上午
 * @Version 1.0
 */
public class TokenUnavailable  extends Exception{

    //创建自定义异常，继承Exception
    public TokenUnavailable(String ErrorMessage) {    //
        // 构造方法
        super(ErrorMessage);                    //父类构造方法
    }


}
