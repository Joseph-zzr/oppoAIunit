package com.coderpig.oppoaiunit.base;

import java.io.Serializable;

/**
 * 基础bean类
 * @param <T>
 */

public class BaseBean<T> implements Serializable {

    public BaseBean(int code,String data){
        this.errorCode = code;
        this.data = (T) data;
    }

    public int errorCode;
    public String errorMsg;
    public T data;
}
