package com.strive.reggie.common;

/**
 * @author lzp moonlight
 * @create 2022-12-03 22:32
 * 自定业务异常类
 */

public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }

}
