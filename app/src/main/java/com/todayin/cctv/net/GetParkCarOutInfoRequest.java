package com.todayin.cctv.net;

/**
 * Created by yg on 2019/1/11.
 */

public class GetParkCarOutInfoRequest {
    private String teamId;        //park_id,停车场ID
    private String beginTime;    //查询开始时间
    private String endTime;        //是	查询结束时间
    private Integer parkMark=1;    //车场标识：1大车场，2小车场
    private Integer parkType;    //停放类型 1:月租车 2:临时车 3:免费车 4:访客车 5:无牌车 6:月临车 7:共享车
    private Integer hasPlate;//是否无牌车
    private String outPlateCn;    //出场 含有中文的车牌号
    private Integer outChannelNo;    //出场通道编码
    private Integer pageNo=1;
    private Integer pageSize=10;

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

    public Integer getParkMark() {
        return parkMark;
    }

    public void setParkMark(Integer parkMark) {
        this.parkMark = parkMark;
    }

    public Integer getParkType() {
        return parkType;
    }

    public void setParkType(Integer parkType) {
        this.parkType = parkType;
    }

    public Integer getHasPlate() {
        return hasPlate;
    }

    public void setHasPlate(Integer hasPlate) {
        this.hasPlate = hasPlate;
    }

    public String getOutPlateCn() {
        return outPlateCn;
    }

    public void setOutPlateCn(String outPlateCn) {
        this.outPlateCn = outPlateCn;
    }

    public Integer getOutChannelNo() {
        return outChannelNo;
    }

    public void setOutChannelNo(Integer outChannelNo) {
        this.outChannelNo = outChannelNo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
