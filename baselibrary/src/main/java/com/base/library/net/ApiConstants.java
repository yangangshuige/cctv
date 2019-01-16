package com.base.library.net;

public class ApiConstants {
    public static final String RESPONSE_SUCCESS="200";

    public static final String BASE_URL = "http://192.168.0.120:8980/yihao01-parkcloud-server/";
    public static final String API_HEAD = "api/";
    public static final String QUERY_HEAD = "query/";

    public static final String PARK_INFO = API_HEAD+"parkConfig/getParkInfo";
    public static final String ROBOT_INFO =API_HEAD+ "parkConfig/getRobotConfigs";
    public static final String PARK_CAR_OUT_INFO =QUERY_HEAD+ "getParkCarOutPage";//分页查询车场的出场纪录集



}
