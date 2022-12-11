package com.strive.reggie.common;

/**
 * @author lzp moonlight
 * @create 2022-12-03 20:37
 * 基于ThreadLocal 封装工具类，用于保存和获取当前用户id
 */

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
       return threadLocal.get();
    }
}
