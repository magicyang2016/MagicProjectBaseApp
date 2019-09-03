package com.magic.magicprojectbaseapp.utils;

import java.util.List;

/**
 * 集合数组数据工具类
 */

public class ToolUtil {
    public static boolean isEmpty(List strList){
        if(strList != null && strList.size() != 0){
            return false;
        }
        return true;
    }
}
