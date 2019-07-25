package com.redis.demo.util;

import java.util.List;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/11 13:54
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
public class ListUtil {

    /**
     * 判断集合为空
     * @param list
     * @return
     */
    public static boolean isBlankList(List<?> list) {
        if(list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合不为空
     * @param list
     * @return
     */
    public static boolean isNotBlankList(List<?> list) {
        if(list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}
