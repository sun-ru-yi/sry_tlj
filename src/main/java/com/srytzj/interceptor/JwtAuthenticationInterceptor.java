package com.srytzj.interceptor;

import com.srytzj.exception.MyException;
import com.srytzj.domain.Account;

import com.srytzj.service.accountservice.AccountService;
import com.srytzj.annotation.PassToken;
import com.srytzj.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author sry
 * @description
 * @date 2022/2/18 1:34 下午
 * @Version 1.0
 */
public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 从请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注解，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //默认全部检查
        else {
            System.out.println("被jwt拦截需要验证");
            // 执行认证
            if (token == null) {
                //这里其实是登录失效,没token了   这个错误也是我自定义的
                //  throw new NeedToLogin();
            }

            // 获取 token 中的 user Name
            String userId = JwtUtils.getAudience(token);


            //找找看是否有这个user   因为我们需要检查用户是否存在，读者可以自行修改逻辑
            Account user = accountService.getByUserName(userId);

            if (user == null) {
                //这个错误也是我自定义的
                throw new MyException("用户不存在");
            }

            // 验证 token 
            JwtUtils.verifyToken(token, userId);

            //获取载荷内容
            String userName = JwtUtils.getClaimByName(token, "userName").asString();
            String realName = JwtUtils.getClaimByName(token, "realName").asString();

            //放入attribute以便后面调用
            request.setAttribute("userName", userName);
            request.setAttribute("realName", realName);

            return true;

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
