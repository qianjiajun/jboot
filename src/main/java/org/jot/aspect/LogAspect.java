package org.jot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jot.entity.log.Log;
import org.jot.entity.user.User;
import org.jot.service.log.ILogService;
import org.jot.service.user.IUserService;
import org.jot.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @Author qjj
 * @Date 2020-12-23 10:27
 * @Version 1.0
 * @Class UserRespect.java
 */
@Order(2)
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ILogService logService;

    @Autowired
    private HttpSession session;

    private Log log;
    private Object proceed;

    @Pointcut("@annotation(org.jot.annotation.Log)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("类：" + className);
        System.out.println("方法：" + methodName);
        System.out.println("传入参数：");
        for (int i = 0; i < args.length; i++) {
            System.out.println("参数" + (i + 1) + "：" + args[i]);
        }

        System.out.println("前置通知完--------------------");
    }

    /**
     * 环绕通知：
     * proceed为执行方法后返回的值
     */
    @Around("pointCut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知：---------------------");
        //获得方法执行后的返回值
        proceed = pjp.proceed();
        Boolean isLogin = (Boolean) session.getAttribute(Const.SESSION_IS_LOGIN);
        if (isLogin) {
            User user = (User) session.getAttribute(Const.SESSION_USER);
            Long id = user.getId();
            System.out.println(id);
            Log record = logService.add(log);
            System.out.println("logId=" + record.getId());
        }
        System.out.println("环绕通知完--------------------");
        return proceed;
    }

}
