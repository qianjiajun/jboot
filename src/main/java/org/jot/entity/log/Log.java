package org.jot.entity.log;

import org.jot.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class Role.java
 */

@Entity
@Table(name = "role")
public class Log extends BaseEntity implements Serializable {

    @Column(name = "option", nullable = false, length = 60)
    private String option;

    @Column(name = "method", nullable = false, length = 60)
    private String method;

    @Column(name = "api", nullable = false)
    private String api;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "param", length = 36)
    private String param;

    @Column(name = "success")
    private Boolean success;

    @Column(name = "result", nullable = false, length = 60)
    private String result;

    public Log() {
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
