package org.jot.handler.convert;

import org.jot.enumeration.Status;

import javax.persistence.AttributeConverter;

/**
 * EnumConvert
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 14:13
 */
public class StatusEnumConvert implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Integer integer){
            return Status.ofValue(integer);
    }
}
