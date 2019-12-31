package com.hori.grms.util;

import com.hori.grms.enums.ResultCode;

public class Result<T> {
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;

    public Result() {
		// TODO Auto-generated constructor stub
	}
    
    public Result(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
    
    public  Result success(T data) {
    	Result<T> result = new Result<>(ResultCode.SUCCESS, data);
        return result;
    }

    

    public  Result error() {
        Result result = new Result<>(ResultCode.ERROR);
        return result;
    }
    
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}