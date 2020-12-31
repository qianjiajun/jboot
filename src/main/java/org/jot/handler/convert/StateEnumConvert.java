package org.jot.handler.convert;

import org.jot.enumeration.State;

import javax.persistence.AttributeConverter;

/**
 * EnumConvert
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 14:13
 */
public class StateEnumConvert implements AttributeConverter<State, Integer> {
    @Override
    public Integer convertToDatabaseColumn(State state) {
        return state.getValue();
    }

    @Override
    public State convertToEntityAttribute(Integer value) {
        return State.ofValue(value);
    }
}
