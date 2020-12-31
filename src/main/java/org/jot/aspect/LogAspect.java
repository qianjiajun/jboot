package org.jot.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.jot.entity.log.Log;
import org.jot.entity.user.User;
import org.jot.enumeration.State;
import org.jot.service.log.ILogService;
import org.jot.util.Const;
import org.jot.util.HashMapUtils;
import org.jot.util.ResultSetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.*;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private ILogService logService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    private Log log = null;
    private org.jot.annotation.Log logAnnotation = null;

    @Pointcut("@annotation(org.jot.annotation.Log)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        logAnnotation = method.getAnnotation(org.jot.annotation.Log.class);
        if (logAnnotation == null || !logAnnotation.isOpen()) {
            return;
        }
        log = new Log();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.setFunctionName(className + "." + methodName + "()");
        log.setName(logAnnotation.value());
        log.setMethod(request.getMethod());
        log.setUrl(request.getRequestURL().toString());
        if (logAnnotation.isRecordParameters()) {
            try {
                log.setParam(JSON.toJSONString(getNameAndValue(joinPoint)));
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }
    }

    private Map<String, Object> getNameAndValue(JoinPoint joinPoint) throws Exception {
        Map<String, Object> param = new HashMap<>();
        Object[] values = joinPoint.getArgs();
        String[] names = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || "".equals(names[i].trim())) {
                continue;
            }
            if ("password".equalsIgnoreCase(names[i].trim())) {
                param.put(names[i], "secret");
                continue;
            }
            param.put(names[i], values[i]);

        }
        return param;
    }

    /**
     * 环绕通知：
     * proceed为执行方法后返回的值
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed();
    }

    @AfterReturning(value = "pointCut()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) {
        if (log == null) {
            return;
        }
        Object result = null;
        if (ret instanceof ResultSetBuilder.ResultSet) {
            ResultSetBuilder.ResultSet resultSet = (ResultSetBuilder.ResultSet) ret;
            log.setSuccess(State.ofValue(resultSet.isSuccess() == true ? 1 : 0));
            result = resultSet.getResult();
        } else {
            log.setSuccess(State.SUCCESS);
        }
        setOperatorId();
        if (logAnnotation.isRecordResultData() && result != null) {
            log.setResult(JSONObject.toJSONString(result));

        }
        logService.add(log);
        log = null;

    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        if (log == null) {
            return;
        }
        log.setSuccess(State.FAIL);
        setOperatorId();
        if (logAnnotation.isRecordCause() && e != null) {
            log.setCause(new HashMapUtils() {{
                add("cause", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            }}.toJSONString());
        }
        logService.add(log);
        log = null;
    }


    private void setOperatorId() {
        User user = (User) session.getAttribute(Const.SESSION_USER);
        if (user == null || user.getId() == null) {
            log.setCreatedBy(0L);
        } else {
            log.setCreatedBy(user.getId());
        }
    }

}
