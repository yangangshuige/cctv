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
//    停放类型 1:月租车 2:临时车 3:免费车 4:访客车 5:无牌车 6:月临车 7:共享车
    public static String getParkType(int type){
        String parkType="";
        switch (type){
            case 1:
                parkType="月租车";
                break;
            case 2:
                parkType="临时车";
                break;
            case 3:
                parkType="免费车";
                break;
            case 4:
                parkType="访客车";
                break;
            case 5:
                parkType="无牌车";
                break;
            case 6:
                parkType="月临车";
                break;
            case 7:
                parkType="共享车";
                break;
        }
        return parkType;
    }
}
