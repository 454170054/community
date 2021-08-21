package com.yoona.community.dto;

import com.yoona.community.Exception.CustomizeErrorCode;
import com.yoona.community.Exception.CustomizeException;

public class ResultDTO<T> {
    private String message;
    private int code;
    private T data;

    public static <T> ResultDTO<T> errorOf(Integer code, String message){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static <T> ResultDTO<T> errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> ResultDTO<T> okOf(){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO<T> okOf(T t){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }

    public static <T> ResultDTO<T> errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(), ex.getMessage());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
