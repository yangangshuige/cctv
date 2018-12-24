package com.todayin.cctv.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yg on 2018/12/20.
 */

public class DataUtils {
    public static List<String> getStringList(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(i + "");
        }
        return list;
    }
}
