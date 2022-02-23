package com.srytzj.controller.login;

import com.srytzj.domain.Account;
import com.srytzj.domain.AccountVo;
import com.srytzj.service.accountservice.AccountService;
import com.srytzj.annotation.PassToken;
import com.srytzj.util.JwtUtils;
import com.srytzj.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController("/user")
public class LoginController {

    @Autowired
    private AccountService accountService;


    @GetMapping(value = "/login")
    @PassToken
    public R login(String userName, String password) throws UnsupportedEncodingException {

        if (userName == null) {
            return R.error("用户名不能为空");
        }

        if (password == null) {
            return R.error("密码不能为空");
        }

        Account user = accountService.login(userName, password);

        if (user == null) {
            return R.error("此用户不存在");
        }

         //  AccountVO account = accountService.getAccountByUserName(userName);

        //给分配一个token 然后返回
        String jwtToken = JwtUtils.createToken(user.getId(), user.getUserName(), user.getRealName());

        //我的处理方式是把token放到accountVO里去了
        AccountVo accountVo = new AccountVo();
        accountVo.setToken(jwtToken);

        return R.ok().putData(accountVo);

    }

    @GetMapping("file")
    public void file(HttpServletRequest request) {


    }

}
