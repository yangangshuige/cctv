package com.todayin.cctv.net;


/**
 * Created by ghg on 2016/06/03.
 */
public class ParkRequest extends BaseRequest{

    private String parkId;

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }
}
