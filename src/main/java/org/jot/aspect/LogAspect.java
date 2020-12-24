package org.jot.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.jot.annotation.Verification;
import org.jot.entity.log.Log;
import org.jot.entity.user.User;
import org.jot.enumeration.StatusCode;
import org.jot.service.log.ILogService;
import org.jot.util.Const;
import org.jot.util.ResultSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialClob;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
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
        User user = (User) session.getAttribute(Const.SESSION_USER);
        if (user == null || user.getId() == null) {
            log.setCreatedBy(0L);
        } else {
            log.setCreatedBy(user.getId());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.setApi(className + "." + methodName);
        log.setName(logAnnotation.value());
        log.setMethod(request.getMethod());
        log.setUrl(request.getRequestURL().toString());
        if (logAnnotation.isRecordParameters()) {
            Object[] args = joinPoint.getArgs();
            List<Object> list = new LinkedList<>();
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    list.add(((HttpServletRequest) arg).getParameterMap());
                } else if (arg instanceof HttpServletResponse || arg instanceof HttpSession || arg instanceof MultipartFile || arg instanceof MultipartFile[]) {

                } else {
                    list.add(arg);
                }
            }
            log.setParam(JSONArray.toJSONString(list));
        }
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
            log.setSuccess(resultSet.isSuccess());
            result = resultSet.getResult();
        } else {
            log.setSuccess(true);
        }
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
        log.setSuccess(false);
        if (logAnnotation.isRecordResultData() && e != null) {
            log.setResult(e.toString());
        }
        logService.add(log);
        log = null;
    }


}
