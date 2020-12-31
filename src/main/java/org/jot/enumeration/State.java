package org.jot.enumeration;

/**
 * Status
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 13:52
 */
public enum State {

    FAIL(0, "失败"), SUCCESS(1, "成功");

    private int value;
    private String name;

    State(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static State ofValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (State state : State.values()) {
            if (state.value == value.intValue()) {
                return state;
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
