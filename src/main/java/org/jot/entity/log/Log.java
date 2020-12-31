package org.jot.entity.log;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jot.entity.BaseEntity;
import org.jot.enumeration.State;
import org.jot.handler.convert.StateEnumConvert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class Role.java
 */

@Entity
@Table(name = "log")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Log extends BaseEntity implements Serializable {

    @Column(name = "name", columnDefinition = "varchar(60) not null default '' comment '操作名称'")
    private String name;

    @Column(name = "method", columnDefinition = "varchar(60) not null default '' comment '操作方法（GET/POST...）'")
    private String method;

    @Column(name = "function_name", columnDefinition = "varchar(100) not null default '' comment '调用函数'")
    private String functionName;

    @Column(name = "url", columnDefinition = "varchar(255) not null default '' comment '调用接口地址'")
    private String url;


    //    @Enumerated(EnumType.ORDINAL)
    @Convert(converter = StateEnumConvert.class)
    @Column(name = "success", columnDefinition = "tinyint(1) not null default 0 comment '调用成功'")
    private State success = State.FAIL;

    @Type(type = "json")
    @Column(name = "param", columnDefinition = "json comment '接口入参'")
    private String param;

    @Type(type = "json")
    @Column(name = "result", columnDefinition = "json comment '接口返回结果'")
    private String result;

    @Type(type = "json")
    @Column(name = "cause", columnDefinition = "json comment '异常原因'")
    private String cause;

    public Log() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public State getSuccess() {
        return success;
    }

    public void setSuccess(State success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
