package org.jot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jot.enumeration.StatusCode;
import org.jot.service.user.IUserService;
import org.jot.util.Const;
import org.jot.util.ResultSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Map;

/**
 * @Author qjj
 * @Date 2020-12-23 10:27
 * @Version 1.0
 * @Class VerificationAspect.java
 */
@Order(1)
@Aspect
@Component
public class VerificationAspect {

    @Autowired
    private IUserService userService;

    @Autowired
    private HttpSession session;

    private Object proceed;

    @Pointcut("execution(* org.jot.controller..*.*(..))")
    public void pointCut() {
    }

    /**
     * 环绕通知：
     * proceed为执行方法后返回的值
     */
    @Around("pointCut()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        proceedingJoinPoint.getTarget();
        //获得方法执行后的返回值
        Boolean isLogin = (Boolean) session.getAttribute(Const.SESSION_IS_LOGIN);
        if (isLogin) {
            proceed = proceedingJoinPoint.proceed();
            if (proceed instanceof ResultSetBuilder.ResultSet) {
                return proceed;
            } else if (!(proceed instanceof Serializable)) {
                return proceed;
            } else if (proceed instanceof Map) {
                return ResultSetBuilder.success(new ResultSetBuilder.Result().setAll((Map) proceed));
            } else {
                return ResultSetBuilder.success(new ResultSetBuilder.Result().set("data", proceed));
            }
        }
        return ResultSetBuilder.fail(StatusCode.VERIFIED_FAIL);
    }

}