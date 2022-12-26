package com.example.mbti.advice;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class ResultInfo {

    private int code;

    @JsonIgnore
    private String message;
    private Map<String, Object> result;


    public enum Code {
        SUCCESS(200),
        FAIL(100),
        ERROR(300);

        int value;

        private Code(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public ResultInfo() {
    }

    public ResultInfo(Code code) {
        this.code = code.getValue();
    }

    public static ResultInfo success() {
        ResultInfo resultInfo = new ResultInfo(Code.SUCCESS);
        return resultInfo;
    }

    public static ResultInfo fail() {
        ResultInfo resultInfo = new ResultInfo(Code.FAIL);
        return resultInfo;
    }

    public static ResultInfo error() {
        ResultInfo resultInfo = new ResultInfo(Code.ERROR);
        return resultInfo;
    }

    public ResultInfo(Code code, String message) {
        this.code = code.getValue();
        this.message = message;
    }

    public static ResultInfo success(String message) {
        ResultInfo resultInfo = new ResultInfo(Code.SUCCESS, message);
        return resultInfo;
    }

    public static ResultInfo fail(String message) {
        ResultInfo resultInfo = new ResultInfo(Code.FAIL, message);
        return resultInfo;
    }

    public static ResultInfo error(String message) {
        ResultInfo resultInfo = new ResultInfo(Code.ERROR, message);
        return resultInfo;
    }

    public ResultInfo(Code code, String message, Map<String, Object> result) {
        this.code = code.getValue();
        this.message = message;
        this.result = result;
    }

    public static ResultInfo success(String message, Map<String, Object> result) {
        ResultInfo resultInfo = new ResultInfo(Code.SUCCESS, message, result);
        return resultInfo;
    }

    public static ResultInfo fail(String message, Map<String, Object> result) {
        ResultInfo resultInfo = new ResultInfo(Code.FAIL, message, result);
        return resultInfo;
    }

    public static ResultInfo error(String message, Map<String, Object> result) {
        ResultInfo resultInfo = new ResultInfo(Code.ERROR, message, result);
        return resultInfo;
    }

    public static ResultInfo success(Map<String, Object> result) {
        ResultInfo resultInfo = new ResultInfo(Code.SUCCESS, null, result);
        return resultInfo;
    }

    public static HashMap<String, Object> makeResultMap(Object object){
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", object);
        return resultMap;
    }
}
