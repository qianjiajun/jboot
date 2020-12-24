package org.jot.util;

import org.jot.enumeration.StatusCode;
import org.springframework.lang.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author qjj
 * @Date 2020-12-23 11:01
 * @Version 1.0
 * @Class Result.java
 */

public class ResultSetBuilder {

    public static ResultSet success() {
        return new ResultSet(StatusCode.OK, null, null, null);
    }

    public static ResultSet success(Result result) {
        return new ResultSet(StatusCode.OK, null, null, result);
    }

    public static ResultSet fail(@NonNull StatusCode statusCode) {
        return new ResultSet(statusCode, null, null, null);
    }

    public static ResultSet fail(@NonNull StatusCode statusCode, String detail) {
        return new ResultSet(statusCode, detail, null, null);
    }

    public static ResultSet fail(@NonNull StatusCode statusCode, String detail, Throwable throwable) {
        return new ResultSet(statusCode, detail, throwable, null);
    }

    public static ResultSet fail(@NonNull StatusCode statusCode, Throwable throwable) {
        return new ResultSet(statusCode, null, throwable, null);
    }

    public static class ResultSet {

        private int code;
        private boolean success;
        private String message;
        private String detail;
        private Throwable throwable;
        private Result result;

        private ResultSet(int code, boolean success, String message, String detail, Throwable throwable, Result result) {
            this.code = code;
            this.success = success;
            this.message = message;
            this.detail = detail;
            this.throwable = throwable;
            this.result = result;
        }

        private ResultSet(StatusCode statusCode, String detail, Throwable throwable, Result result) {
            this.code = statusCode.getCode();
            this.success = statusCode.isSuccess();
            this.message = statusCode.getMessage();
            this.detail = detail;
            this.throwable = throwable;
            this.result = result;
        }

        public int getCode() {
            return code;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public String getDetail() {
            return detail;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public Result getResult() {
            return result;
        }

        public ResultSet setResult(Object result) {
            this.result = new Result().set("result", result);
            return this;
        }

    }

    public static class Result extends LinkedHashMap implements Map {

        public Result set(Object key, Object value) {
            this.put(key, value);
            return this;
        }

        public Result setAll(Map map) {
            this.putAll(map);
            return this;
        }

    }

}