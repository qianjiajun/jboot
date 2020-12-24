package org.jot.enumeration;

/**
 * @Author qjj
 * @Date 2020-12-23 11:08
 * @Version 1.0
 * @Class StatusCode.java
 */
public enum StatusCode {


    /**
     * Http状态码
     */
    OK(200, true, "请求成功"),
    MOVED_PERMANENTLY(301, false, "资源（网页等）被永久转移到其它URL"),
    BAD_REQUEST(400, false, "客户端请求的语法错误，服务器无法理解"),
    NOT_FOUND(404, false, "请求的资源（网页等）不存在"),
    METHOD_NOT_ALLOWED(405, false, "客户端请求中的方法被禁止"),
    INTERNAL_SERVER_ERROR(500, false, "内部服务器错误"),
    /**
     * 自定义状态码
     */
    /**
     * 50001-50009 令牌-token
     */
    VERIFIED_FAIL(50001, false, "登录验证失败"),
    NON_LOGIN(50002, false, "用户未登录"),
    NON_TOKEN(50003, false, "缺失令牌"),
    INVALID_TOKEN(50004, false, "非法的令牌"),
    TIMEOUT_TOKEN(50005, false, "令牌已过时"),
    /**
     * 50011-50019 用户登录-login
     */
    NON_USER(50011, false, "请输入用户名"),
    NON_PASSWORD(50012, false, "请输入密码"),
    NON_VERIFY_CODE(50013, false, "请输入验证码"),
    USER_NOT_EXIST_OR_PASSWORD_ERROR(50014, false, "用户不存在或密码错误"),
    USER_NOT_EXIST(50015, false, "用户不存在"),
    PASSWORD_ERROR(50016, false, "密码错误"),
    VERIFY_CODE_ERROR(50017, false, "验证码错误"),
    USER_FORBIDDEN(50018, false, "用户已被禁用"),
    /**
     * 未知异常
     */
    UNKNOWN_ERROR(99999, false, "未知错误");

    private int code;
    private boolean success;
    private String message;

    StatusCode(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public static StatusCode getByCode(Integer code) {
        if (code == null) {
            return UNKNOWN_ERROR;
        }
        for (StatusCode statusCode : StatusCode.values()) {
            if (statusCode.code == code.intValue()) {
                return statusCode;
            }
        }
        return UNKNOWN_ERROR;
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

    @Override
    public String toString() {
        StringBuilder statusCode = new StringBuilder();
        statusCode.append("{\"code\": ")
                .append(this.code)
                .append(",\"message\": \"")
                .append(this.message)
                .append("\"}");
        return statusCode.toString();
    }

    public static StatusCode getBeanFromJSONString(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        jsonString = jsonString.trim();
        if ("".equals(jsonString) || jsonString.length() <= 1 || jsonString.charAt(0) != '{' || jsonString.charAt(jsonString.length() - 1) != '}') {
            return null;
        }
        try {
            String[] keyValues = jsonString.substring(1, jsonString.length()).split(",");
            for (String keyValue : keyValues) {
                String[] kv = keyValue.split(":");
                if ("\"code\"".equals(kv[0])) {
                    return getByCode(Integer.valueOf(kv[1].trim()));
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
