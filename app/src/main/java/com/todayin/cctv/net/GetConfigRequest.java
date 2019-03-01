package com.todayin.cctv.net;

/**
 * Created by ghg on 2016/06/03.
 */
public class GetConfigRequest extends ParkRequest{

    private String robotId;
    private String channelId;
    private String monitorServerIp; //监控中心IP

    public void setMonitorServerIp(String monitorServerIp) {
        this.monitorServerIp = monitorServerIp;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }
}
