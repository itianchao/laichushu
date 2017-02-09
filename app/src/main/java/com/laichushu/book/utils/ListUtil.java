package com.laichushu.book.utils;

import java.util.List;

/**
 * 交换集合元素
 * Created by wangtong on 2017/2/8.
 */

public class ListUtil {
    public static <T> List<T> indexExChange(List<T> list, int index1, int index2){
        T t = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, t);
        return list;
    }
}
