package com.base.library.net;

public class ApiConstants {

    public static final String USER_A="2gSFSEl5m/gMwmw27K+nTKwEfIuvPU0SJG4cFOP/48kQMDZpBCVIqHjDG7suE/VjyWxphtyMrey3Qb/6gok2Zg==";
    public static final String USER_B="nVFKociCkFtf+vDaTmzRx3DGJ7zf6Lf5fugdBWa/fsbPbQcs11k8LiXMkPbscC5tm9Ioo+PKOYvMDcZ8H/NyRw==";
    public static final String RESPONSE_SUCCESS="200";

    public static final String BASE_URL = "http://192.168.0.120:8980/yihao01-parkcloud-server/";//原始
    public static final String BASE_URL_NEW = "http://192.168.0.110:6930/";
    public static final String API_HEAD = "api/";
    public static final String QUERY_HEAD = "query/";

    public static final String PARK_INFO = API_HEAD+"parkConfig/getParkInfo";
    public static final String ROBOT_INFO =API_HEAD+ "parkConfig/getRobotConfigs";
    public static final String PARK_OUT_DETAIL ="tinet-park-server/monitor/getParkCarOutPage";//分页查询车场的出场纪录集

}
