package com.todayin.cctv.net;

import com.base.library.net.ApiConstants;
import com.base.library.net.BaseResponse;
import com.todayin.cctv.bean.Car;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by yg on 2019/1/11.
 */

public interface ApiService {
    @POST(ApiConstants.PARK_CAR_OUT_INFO)
    Observable<BaseResponse<Car>> GetParkInInfo(@Body GetParkCarOutInfoRequest request);
}
