package com.mzj.meetingfilm.utils.util;
/**
 * 基础工具类
 */
public class ToolUtils {

    private ToolUtils(){}

    /**
     * 字符串为空
     */
    public static boolean strIsNull(String str){
        if (str==null || str.trim().length()<=0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 字符串不为空
     */
    public static boolean strIsNotNull(String str){
        if (strIsNull(str)){
            return false;
        }else{
            return false;
        }
    }
}
