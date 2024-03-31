package com.zxh.common.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultData extends HashMap<String, Object> {
    public ResultData setData(Object data) {
        put("data", data);
        return this;
    }

    //利用fastjson进行反序列化
    public <T> T getData(TypeReference<T> typeReference) {
        Object data = get("data"); //默认是data
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }

    //利用fastjson进行反序列化
    public <T> T getData(String key, TypeReference<T> typeReference) {
        Object data = get(key);    //默认是map
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    public ResultData() {
        put("code", 0);
        put("msg", "success");
    }

    public static ResultData ok(String msg) {
        ResultData r = new ResultData();
        r.put("msg", msg);
        return r;
    }

    public static ResultData error(int code, String msg) {
        ResultData resultData = new ResultData();
        resultData.put("code", code);
        resultData.put("msg", msg);
        return resultData;
    }

    public static ResultData ok(Map<String, Object> map) {
        ResultData r = new ResultData();
        r.putAll(map);
        return r;
    }

    public static ResultData ok() {
        return new ResultData();
    }

    public ResultData put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

}
