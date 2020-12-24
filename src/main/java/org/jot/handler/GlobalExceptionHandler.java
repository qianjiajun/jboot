package org.jot.handler;

import org.jot.enumeration.StatusCode;
import org.jot.util.ResultSetBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResultSetBuilder.ResultSet exceptionHandler(HttpServletRequest request, Exception exception) {
        if (exception != null && exception.getCause() != null && exception.getCause().getMessage() != null) {
            String message = exception.getCause().getMessage();
            StatusCode statusCode = StatusCode.getBeanFromJSONString(message);
            return ResultSetBuilder.fail(statusCode);
        }
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        StatusCode finalStatusCode = StatusCode.getByCode(code);
        return ResultSetBuilder.fail(finalStatusCode, exception);
    }

}
