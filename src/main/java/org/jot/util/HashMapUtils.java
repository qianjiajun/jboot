package org.jot.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMapUtils
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 11:22
 */
public class HashMapUtils extends HashMap implements Map{

    public HashMapUtils() {
    }

    public HashMapUtils getSelf() {
        return this;
    }


    public HashMapUtils add(Object key, Object value){
        this.put(key, value);
        return this;
    }

    public HashMapUtils addAll(Map map){
        this.putAll(map);
        return this;
    }

    public  String toJSONString(){
        return JSONObject.toJSONString(this);
    }

}
