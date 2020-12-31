package org.jot.enumeration;

/**
 * Gender
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 12:32
 */
public enum Gender {

    UNKNOWN(-1, ""),
    FEMALE(0, "女"),
    MALE(1, "男");

    private int value;
    private String name;

    Gender(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Gender ofValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (Gender gender : Gender.values()) {
            if (gender.value == value.intValue()) {
                return gender;
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
