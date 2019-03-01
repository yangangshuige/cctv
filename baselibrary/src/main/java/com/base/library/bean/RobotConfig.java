package com.base.library.bean;


import java.io.Serializable;
import java.util.List;

public class RobotConfig extends RobotConf {
    @Override
    public String toString() {
        return "RobotConfig{" +
                "channelList=" + channelList +
                '}';
    }

    private List<ChannelListBean> channelList;

    public List<ChannelListBean> getChannelList() {
        return channelList;
    }

    public static class ChannelListBean implements Serializable {

        private String channelName = "channel";
        private String recognizerIp;
        private String channelId = "-1";//String	否	通道Id
        private String recognizerPort;//String	否	控制器端口
        private String controllerIp;//String	否	控制器IP
        private String panoramaIp;//String	否	全景相机ip
        private String wechatUrl;//String	否	微信支付二维码url串
        private String alipayUrl;//String	否	支付宝支付二维码url串
        private String sentryBoxId;//String	否	岗亭Id
        private String sentryBoxIp;//String	否	岗亭IP,带端口。如：127.0.0.1:8980
        private String accessType;//String	否	出入类型：1:标准进 2:标准出 3:单通道
        private String channelType;//String	否	通道类型： 1:大车场通道 2:小车场通道
        private String voiceBeginTime1;//String	否	第一时间段开始时间
        private String voiceEndTime1;//String	否	第一时间段结束时间
        private String voiceVolume1;//String	否	第一时间段音量
        private String voiceBeginTime2;//String	否	第二时间段开始时间
        private String voiceEndTime2;//String	否	第二时间段结束时间
        private String voiceVolume2;//String	否	第二时间段音量
        private String displayContent;//String	否	屏幕显示
        private String twoRecognitionIp;//String	否	二次识别相机ip
        private String isPanorama;//String	否	是否开启全景相机 0不开启 1开启
        private String isTwoRecognition;//String	否	是否开启二次识别 0不开启 1开启
        private String addressMac;//String	否	通道机器人mac地址
        private String monitorServerIp;//String	否	监控服务器IP
        private String monitorServerPort;//String	否	监控服务器可视对讲端口
        private String secondIdentifyCamera;
        private String invoiceMachine;//	Integer	Shi	发票机 0 无  1 有
        private String haveDisplay;
        private String callMode;//	Integer	1 轮呼模式 2 全呼模式
        private String printeMachine;//	Integer	是	打印机 0 无  1 有
        private String moneyMachine;//	Integer	是	投币机 0 无   1 有

        private String allowPassTimeBegin;//"": "01:00:00",
        private String allowPassTimeEnd;//     "": "03:00:00"

        public String getAllowPassTimeBegin() {
            return allowPassTimeBegin;
        }

        public void setAllowPassTimeBegin(String allowPassTimeBegin) {
            this.allowPassTimeBegin = allowPassTimeBegin;
        }

        public String getAllowPassTimeEnd() {
            return allowPassTimeEnd;
        }

        public void setAllowPassTimeEnd(String allowPassTimeEnd) {
            this.allowPassTimeEnd = allowPassTimeEnd;
        }

        public String getPrinteMachine() {
            return printeMachine;
        }

        public void setPrinteMachine(String printeMachine) {
            this.printeMachine = printeMachine;
        }

        public String getMoneyMachine() {
            return moneyMachine;
        }

        public void setMoneyMachine(String moneyMachine) {
            this.moneyMachine = moneyMachine;
        }

        public String getCallMode() {
            return callMode;
        }

        public void setCallMode(String callMode) {
            this.callMode = callMode;
        }

        public String getHaveDisplay() {
            return haveDisplay;
        }

        public void setHaveDisplay(String haveDisplay) {
            this.haveDisplay = haveDisplay;
        }

        public String getInvoiceMachine() {
            return invoiceMachine;
        }

        public void setInvoiceMachine(String invoiceMachine) {
            this.invoiceMachine = invoiceMachine;
        }

        public String getSecondIdentifyCamera() {
            return secondIdentifyCamera;
        }

        public void setSecondIdentifyCamera(String secondIdentifyCamera) {
            this.secondIdentifyCamera = secondIdentifyCamera;
        }

        public String getMonitorServerIp() {
            return monitorServerIp;
        }

        public void setMonitorServerIp(String monitorServerIp) {
            this.monitorServerIp = monitorServerIp;
        }

        public String getMonitorServerPort() {
            return monitorServerPort;
        }

        public void setMonitorServerPort(String monitorServerPort) {
            this.monitorServerPort = monitorServerPort;
        }

        public String getAccessType() {
            return accessType;
        }

        public void setAccessType(String accessType) {
            this.accessType = accessType;
        }

        public String getAlipayUrl() {
            return alipayUrl;
        }

        public void setAlipayUrl(String alipayUrl) {
            this.alipayUrl = alipayUrl;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelType() {
            return channelType;
        }

        public void setChannelType(String channelType) {
            this.channelType = channelType;
        }

        public String getControllerIp() {
            return controllerIp;
        }

        public void setControllerIp(String controllerIp) {
            this.controllerIp = controllerIp;
        }

        public String getDisplayContent() {
            return displayContent;
        }

        public void setDisplayContent(String displayContent) {
            this.displayContent = displayContent;
        }

        public String getPanoramaIp() {
            return panoramaIp;
        }

        public void setPanoramaIp(String panoramaIp) {
            this.panoramaIp = panoramaIp;
        }

        public String getRecognizerIp() {
            return recognizerIp;
        }

        public void setRecognizerIp(String recognizerIp) {
            this.recognizerIp = recognizerIp;
        }

        public String getRecognizerPort() {
            return recognizerPort;
        }

        public void setRecognizerPort(String recognizerPort) {
            this.recognizerPort = recognizerPort;
        }

        public String getSentryBoxId() {
            return sentryBoxId;
        }

        public void setSentryBoxId(String sentryBoxId) {
            this.sentryBoxId = sentryBoxId;
        }

        public String getTwoRecognitionIp() {
            return twoRecognitionIp;
        }

        public void setTwoRecognitionIp(String twoRecognitionIp) {
            this.twoRecognitionIp = twoRecognitionIp;
        }

        public String getVoiceBeginTime1() {
            return voiceBeginTime1;
        }

        public void setVoiceBeginTime1(String voiceBeginTime1) {
            this.voiceBeginTime1 = voiceBeginTime1;
        }

        public String getVoiceBeginTime2() {
            return voiceBeginTime2;
        }

        public void setVoiceBeginTime2(String voiceBeginTime2) {
            this.voiceBeginTime2 = voiceBeginTime2;
        }

        public String getVoiceEndTime1() {
            return voiceEndTime1;
        }

        public void setVoiceEndTime1(String voiceEndTime1) {
            this.voiceEndTime1 = voiceEndTime1;
        }

        public String getVoiceEndTime2() {
            return voiceEndTime2;
        }

        public void setVoiceEndTime2(String voiceEndTime2) {
            this.voiceEndTime2 = voiceEndTime2;
        }

        public String getVoiceVolume1() {
            return voiceVolume1;
        }

        public void setVoiceVolume1(String voiceVolume1) {
            this.voiceVolume1 = voiceVolume1;
        }

        public String getVoiceVolume2() {
            return voiceVolume2;
        }

        public void setVoiceVolume2(String voiceVolume2) {
            this.voiceVolume2 = voiceVolume2;
        }

        public String getWechatUrl() {
            return wechatUrl;
        }

        public void setWechatUrl(String wechatUrl) {
            this.wechatUrl = wechatUrl;
        }

        public String getIsPanorama() {
            return isPanorama;
        }

        public void setIsPanorama(String isPanorama) {
            this.isPanorama = isPanorama;
        }

        public String getIsTwoRecognition() {
            return isTwoRecognition;
        }

        public void setIsTwoRecognition(String isTwoRecognition) {
            this.isTwoRecognition = isTwoRecognition;
        }

        public String getAddressMac() {
            return addressMac;
        }

        public void setAddressMac(String addressMac) {
            this.addressMac = addressMac;
        }

        public String getSentryBoxIp() {
            return sentryBoxIp;
        }

        public void setSentryBoxIp(String sentryBoxIp) {
            this.sentryBoxIp = sentryBoxIp;
        }

        @Override
        public String toString() {
            return "ChannelListBean{" +
                    "channelName='" + channelName + '\'' +
                    ", recognizerIp='" + recognizerIp + '\'' +
                    ", channelId='" + channelId + '\'' +
                    ", recognizerPort='" + recognizerPort + '\'' +
                    ", controllerIp='" + controllerIp + '\'' +
                    ", panoramaIp='" + panoramaIp + '\'' +
                    ", wechatUrl='" + wechatUrl + '\'' +
                    ", alipayUrl='" + alipayUrl + '\'' +
                    ", sentryBoxId='" + sentryBoxId + '\'' +
                    ", sentryBoxIp='" + sentryBoxIp + '\'' +
                    ", accessType='" + accessType + '\'' +
                    ", channelType='" + channelType + '\'' +
                    ", voiceBeginTime1='" + voiceBeginTime1 + '\'' +
                    ", voiceEndTime1='" + voiceEndTime1 + '\'' +
                    ", voiceVolume1='" + voiceVolume1 + '\'' +
                    ", voiceBeginTime2='" + voiceBeginTime2 + '\'' +
                    ", voiceEndTime2='" + voiceEndTime2 + '\'' +
                    ", voiceVolume2='" + voiceVolume2 + '\'' +
                    ", displayContent='" + displayContent + '\'' +
                    ", twoRecognitionIp='" + twoRecognitionIp + '\'' +
                    ", isPanorama='" + isPanorama + '\'' +
                    ", isTwoRecognition='" + isTwoRecognition + '\'' +
                    ", addressMac='" + addressMac + '\'' +
                    ", monitorServerIp='" + monitorServerIp + '\'' +
                    ", monitorServerPort='" + monitorServerPort + '\'' +
                    ", secondIdentifyCamera='" + secondIdentifyCamera + '\'' +
                    ", invoiceMachine='" + invoiceMachine + '\'' +
                    ", haveDisplay='" + haveDisplay + '\'' +
                    ", callMode='" + callMode + '\'' +
                    ", printeMachine='" + printeMachine + '\'' +
                    ", moneyMachine='" + moneyMachine + '\'' +
                    ", allowPassTimeBegin='" + allowPassTimeBegin + '\'' +
                    ", allowPassTimeEnd='" + allowPassTimeEnd + '\'' +
                    '}';
        }
    }

}
