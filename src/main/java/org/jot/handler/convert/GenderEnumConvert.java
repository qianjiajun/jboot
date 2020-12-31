package org.jot.handler.convert;

import org.jot.enumeration.Gender;

import javax.persistence.AttributeConverter;

/**
 * EnumConvert
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 14:13
 */
public class GenderEnumConvert implements AttributeConverter<Gender, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer integer) {
        return Gender.ofValue(integer);
    }
}
