package com.todayin.cctv.net;

/**
 * Created by yg on 2019/1/11.
 */

public class GetParkCarOutInfoRequest {
    private String teamId;        //park_id,停车场ID
    private String beginTime;    //查询开始时间
    private String endTime;        //是	查询结束时间
    private int parkMark;    //车场标识：1大车场，2小车场
    private int parkType;    //停放类型 1:月租车 2:临时车 3:免费车 4:访客车 5:无牌车 6:月临车 7:共享车
    private int hasPlate;//是否无牌车
    private String outPlateCn;    //出场 含有中文的车牌号
    private int outChannelNo;    //出场通道编码
    private int pageStart;
    private int pageEnd;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getParkMark() {
        return parkMark;
    }

    public void setParkMark(int parkMark) {
        this.parkMark = parkMark;
    }

    public int getParkType() {
        return parkType;
    }

    public void setParkType(int parkType) {
        this.parkType = parkType;
    }

    public int getHasPlate() {
        return hasPlate;
    }

    public void setHasPlate(int hasPlate) {
        this.hasPlate = hasPlate;
    }

    public String getOutPlateCn() {
        return outPlateCn;
    }

    public void setOutPlateCn(String outPlateCn) {
        this.outPlateCn = outPlateCn;
    }

    public int getOutChannelNo() {
        return outChannelNo;
    }

    public void setOutChannelNo(int outChannelNo) {
        this.outChannelNo = outChannelNo;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
}
