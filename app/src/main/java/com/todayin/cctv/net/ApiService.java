package com.todayin.cctv.net;

import com.base.library.bean.ParkInfo;
import com.base.library.bean.RobotConfig;
import com.base.library.net.ApiConstants;
import com.base.library.net.BaseResponse;
import com.todayin.cctv.bean.Car;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yg on 2019/1/11.
 */

public interface ApiService {
    @POST(ApiConstants.PARK_OUT_DETAIL)
    Observable<ListResponse<Car>> GetParkOutDetail(@Body GetParkCarOutInfoRequest request);

    /**
     * 获取机器人配置信息
     *
     * @param request
     * @return
     */
    @POST(ApiConstants.ROBOT_INFO)
    Observable<BaseResponse<RobotConfig>> getRobotConfigs(@Body GetConfigRequest request);

    /**
     * 获取车场信息
     *
     * @return
     */
    @GET(ApiConstants.PARK_INFO)
    Observable<BaseResponse<ParkInfo>> getParkInfo(@Query("robotId") String robotId);


}
