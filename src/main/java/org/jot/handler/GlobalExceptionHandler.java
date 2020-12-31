package org.jot.handler;

import org.jot.enumeration.StatusCode;
import org.jot.util.GlobalException;
import org.jot.util.ResultSetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${org.jot.print-error:true}")
    private boolean printError;

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResultSetBuilder.ResultSet exceptionHandler(HttpServletRequest request, Exception exception) {
        if (printError) {
            LOGGER.info("异常地址 {}", request.getRequestURL());
            LOGGER.error("异常拦截", exception);
        }
        if (exception == null) {
            return ResultSetBuilder.fail(StatusCode.UNKNOWN_ERROR);
        }
        if (exception instanceof  UndeclaredThrowableException) {
            Throwable ut =  ((UndeclaredThrowableException) exception).getUndeclaredThrowable();
            if (ut instanceof GlobalException) {
                return ResultSetBuilder.fail((GlobalException) ut);
            }
        }
        if (exception instanceof GlobalException) {
            return ResultSetBuilder.fail((GlobalException) exception);
        }
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        StatusCode finalStatusCode = StatusCode.getByCode(code);
        return ResultSetBuilder.fail(finalStatusCode, exception);
    }

}
