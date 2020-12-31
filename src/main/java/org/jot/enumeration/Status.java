package org.jot.enumeration;

import org.jot.util.GlobalException;

/**
 * Status
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 13:52
 */
public enum Status {

    DELETED(-1, "删除"),
    INACTIVE(0, "禁用"),
    ACTIVE(1, "启用");

    private int value;
    private String name;

    Status(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Status ofValue(Integer value) {
        if (value == null) {
           return null;
        }
        for (Status status : Status.values()) {
            if (status.value == value.intValue()) {
                return status;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
