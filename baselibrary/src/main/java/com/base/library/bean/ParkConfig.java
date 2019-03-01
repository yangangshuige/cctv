package com.base.library.bean;


import java.io.Serializable;

/**
 * Entity mapped to table "parkCfgTbl".
 */
public class ParkConfig implements Serializable {

    private Long id;
    private Integer parkId;//String	是	车场ID
    private String cityNo;//String	是	市编码
    private Integer expenseStandardNo;//String	是	收费标准编码
    private Integer parkMode;//String	是	车场模式 1标准 2嵌套
    private Integer expenseStandardType;//String	是	临时车收费类型:1收费 2不收费
    private Integer isDiscount;//String	是	启用优惠打折 1启用 0不启用
    private Integer isCautionDay;//String	是	月租车是否有提醒日期，0没有，大于0表示提醒天数
    private Integer isPrintInvoice;//String	是	是否启用发票机，1启用，0不启用
    private Integer isEnableFree;//String	是	启用免费车 1启用 0不启用
    private Integer isEnableMonthlyTempPark;//String	是	启用月临车收费 1启用 0不启用
    private Integer isVideoSurveillance;//String	是	月租车进出未授权通道是否做异常处理，1启用，0不启用(是否开启视频监听 1是 0否)
    private Integer isBluetoothRegulate;//String	是	启用蓝牙识别 1启用 0不启用
    private Integer useOverDays;//String	是	月租车过期后可使用天数
    private Integer isEnableCarBlackList;//String	是	启用黑名单 1启用 0不启用
    private String playSoundTime;//String	是	语音播放时段
    private String carNoFirstName;//String	是	车牌录入省份第一个汉字
    private Integer carReliability;//String	是	识别可信度阀值
    private Integer basementCarportCnt;//String	是	室内/地下 车位总数
    private Integer groundCarportCnt;//String	是	室外/地上 车位总数
    private Integer tempCarportCnt;//String	是	临时车位数
    private Integer usedTempCarportCnt;//String	是	已用户临时车车位数
    private Integer tempEnterOutMode;//String	是	临时车进场管控模式 1宽进 2严进
    private Integer fuzzyMatchMode;//String	是	车牌模糊比对方式 1 五位对比，2 四位对比
    private Integer tempCarMinMoney;//String	是	临时车无匹配入场记录收费金额
    private Integer payTimeOverLimit;//String	是	缴费有效时间限制，APP申请审核通过后，完成支付时间限制，单位小时
    private String pictureShareFolder;//String	是	共享图片存储路径
    private String displayContent;//String	是	显示屏 显示内容
    private Integer isTempOut;//String	是	临时车无入场记录允许正常出场 1允许 0不允许
    private Integer isMonthOut;//String	是	月租车无入场记录允许出场 1允许 0不允许
    private Integer isCenterCharge;//String	是	是否中央收费模式，0非，1是
    private Integer isOutCharge;//String	是	是否允许出口收费，0非，1是，该字段配合中央收费模式使用
    private Integer isTempFastOut;//String	是	临时车无停留金额快速出场，1启用，0不启用
    private String tempCarChannelRight;//String	是	临时车通道权限设置
    private Integer activeSetting;//String	是	激活设置，1已激活，0未激活
    private Integer countDays;//String	是	场内车辆统计天数，在该时间段内车场参与统计
    private String isOnlinePay;//String	是	启用线上支付 1启用 0不启用
    private Integer tempOnlinePayFreeMinute;//String	是	线上支付免费分钟内离场
    private Integer tempOnlinePayTimeoutStd;//String	是	线上支付超时收费标准编号，默认没有
    private String isUnionpay;//String	是	是否启用银联支付 1启用 0不启用
    private String createTime;//String	是	创建时间
    private Integer enableChange;//Int	是	0 未开启找零支付 1 已开启找零支付
    private Integer enablePutMoneyCall;//Int	是	找零支付放钞呼叫 0 放钞不呼叫 1放钞呼叫
    private Integer noAnswerWait;//Integer	是	不接听等待时间  默认 10秒
    private Integer positionOneInit;//Integer	是(已过期)	0 放置发票 1 放置二维码
    private Integer positionTwoInit;//Integer	是(已过期)	0 放置发票 1 放置二维码
    private Integer positionOneMoney;//Integer	是	第1个槽位发票金额
    private Integer positionTwoMoney;//Integer	是	第2个槽位发票金额
    private Integer noAvailableWait;//Integer	是	无法接通等待时间 默认5秒
    private Integer isPrintMesssage;//Integer	是	0 不打印  1 打印
    private String printMessage;//String	否	打印内容  json字符串
    private String changeMessage;//String	否	找零提示信息
    private String invoiceMessage;//String	否	发票提示信息
    private Integer isInvoiceVoucher;//Integer	是	打印机打印凭证 0 不打印 1 打印
    private String invoiceVoucherMessage;//String 	否	  打印凭证提示信息
    private Integer isNoPlateVoucher;//Integer	是	  是否开启无牌车凭条 0 关闭 1 开启
    private Integer isMotoVoucher;//Integer	是	  是否开启摩托车凭条0 关闭 1 开启
    private String motoChargeType;//String	是	  摩托车收费标准
    private String noPlatePaymentUrl;//String	是	无牌车扫码支付地址
    private Integer isCarMatch;//Integer	否	是否开启车型识别0不开启 1 开启
    private String carMatchDegree;//	Integer	否	车型匹配度
    private String carModeServerUrl;//String	否	车型识别服务URL
    private String carMatchServerUrl;//String	否	车型匹配服务URL
    private String monthOnlineRenewalType;//Integer	是	月租车线上续费类型 0:不开启 1:线上微信 2:线上支付宝 3:线上微信和支付宝
    private String isCarTypeCharge;//	Integer	否	是否启用车型计费，0不启用，1启用
    private String carRecognizeDegree;//Integer	否	车型识别度（isCarTypeCharge=1时有效）
    private String compareCarType;//	String	否	比对车型（isCarMatch=1有效）
    private String isEnterConfirm;//	Integer	否	异常开启大车入场确认，0不启用，1启用
    private String isConfirmOpenStrobe;//	Integer	否	车型异常入口确认开闸，0不启用，1启用
    private String strictEnterDays = "0";//	String	否	严进有效时间
    private String strictEnterIgnoreDates;//	Stirng	否	严进不生效日期
    private Integer isActivatePoliceCar; //是否启用警车:0未启用，1启用
    private Integer isActivateSoldierCar; //是否启用军车:0未启用，1启用

    public Integer getIsActivatePoliceCar() {
        return isActivatePoliceCar;
    }

    public void setIsActivatePoliceCar(Integer isActivatePoliceCar) {
        this.isActivatePoliceCar = isActivatePoliceCar;
    }

    public Integer getIsActivateSoldierCar() {
        return isActivateSoldierCar;
    }

    public void setIsActivateSoldierCar(Integer isActivateSoldierCar) {
        this.isActivateSoldierCar = isActivateSoldierCar;
    }

    public String getStrictEnterDays() {
        return strictEnterDays;
    }

    public void setStrictEnterDays(String strictEnterDays) {
        this.strictEnterDays = strictEnterDays;
    }

    public String getStrictEnterIgnoreDates() {
        return strictEnterIgnoreDates;
    }

    public void setStrictEnterIgnoreDates(String strictEnterIgnoreDates) {
        this.strictEnterIgnoreDates = strictEnterIgnoreDates;
    }

    public String getCarModeServerUrl() {
        return carModeServerUrl;
    }

    public void setCarModeServerUrl(String carModeServerUrl) {
        this.carModeServerUrl = carModeServerUrl;
    }

    public String getCarMatchServerUrl() {
        return carMatchServerUrl;
    }

    public void setCarMatchServerUrl(String carMatchServerUrl) {
        this.carMatchServerUrl = carMatchServerUrl;
    }

    public Integer getIsCarMatch() {
        return isCarMatch;
    }

    public void setIsCarMatch(Integer isCarMatch) {
        this.isCarMatch = isCarMatch;
    }

    public String getCarMatchDegree() {
        return carMatchDegree;
    }

    public void setCarMatchDegree(String carMatchDegree) {
        this.carMatchDegree = carMatchDegree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public Integer getExpenseStandardNo() {
        return expenseStandardNo;
    }

    public void setExpenseStandardNo(Integer expenseStandardNo) {
        this.expenseStandardNo = expenseStandardNo;
    }

    public Integer getParkMode() {
        return parkMode;
    }

    public void setParkMode(Integer parkMode) {
        this.parkMode = parkMode;
    }

    public Integer getExpenseStandardType() {
        return expenseStandardType;
    }

    public void setExpenseStandardType(Integer expenseStandardType) {
        this.expenseStandardType = expenseStandardType;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getIsCautionDay() {
        return isCautionDay;
    }

    public void setIsCautionDay(Integer isCautionDay) {
        this.isCautionDay = isCautionDay;
    }

    public Integer getIsPrintInvoice() {
        return isPrintInvoice;
    }

    public void setIsPrintInvoice(Integer isPrintInvoice) {
        this.isPrintInvoice = isPrintInvoice;
    }

    public Integer getIsEnableFree() {
        return isEnableFree;
    }

    public void setIsEnableFree(Integer isEnableFree) {
        this.isEnableFree = isEnableFree;
    }

    public Integer getIsEnableMonthlyTempPark() {
        return isEnableMonthlyTempPark;
    }

    public void setIsEnableMonthlyTempPark(Integer isEnableMonthlyTempPark) {
        this.isEnableMonthlyTempPark = isEnableMonthlyTempPark;
    }

    public Integer getIsVideoSurveillance() {
        return isVideoSurveillance;
    }

    public void setIsVideoSurveillance(Integer isVideoSurveillance) {
        this.isVideoSurveillance = isVideoSurveillance;
    }

    public Integer getIsBluetoothRegulate() {
        return isBluetoothRegulate;
    }

    public void setIsBluetoothRegulate(Integer isBluetoothRegulate) {
        this.isBluetoothRegulate = isBluetoothRegulate;
    }

    public Integer getUseOverDays() {
        return useOverDays;
    }

    public void setUseOverDays(Integer useOverDays) {
        this.useOverDays = useOverDays;
    }

    public Integer getIsEnableCarBlackList() {
        return isEnableCarBlackList;
    }

    public void setIsEnableCarBlackList(Integer isEnableCarBlackList) {
        this.isEnableCarBlackList = isEnableCarBlackList;
    }

    public String getPlaySoundTime() {
        return playSoundTime;
    }

    public void setPlaySoundTime(String playSoundTime) {
        this.playSoundTime = playSoundTime;
    }

    public String getCarNoFirstName() {
        return carNoFirstName;
    }

    public void setCarNoFirstName(String carNoFirstName) {
        this.carNoFirstName = carNoFirstName;
    }

    public Integer getCarReliability() {
        return carReliability;
    }

    public void setCarReliability(Integer carReliability) {
        this.carReliability = carReliability;
    }

    public Integer getBasementCarportCnt() {
        return basementCarportCnt;
    }

    public void setBasementCarportCnt(Integer basementCarportCnt) {
        this.basementCarportCnt = basementCarportCnt;
    }

    public Integer getGroundCarportCnt() {
        return groundCarportCnt;
    }

    public void setGroundCarportCnt(Integer groundCarportCnt) {
        this.groundCarportCnt = groundCarportCnt;
    }

    public Integer getTempCarportCnt() {
        return tempCarportCnt;
    }

    public void setTempCarportCnt(Integer tempCarportCnt) {
        this.tempCarportCnt = tempCarportCnt;
    }

    public Integer getUsedTempCarportCnt() {
        return usedTempCarportCnt;
    }

    public void setUsedTempCarportCnt(Integer usedTempCarportCnt) {
        this.usedTempCarportCnt = usedTempCarportCnt;
    }

    public Integer getTempEnterOutMode() {
        return tempEnterOutMode;
    }

    public void setTempEnterOutMode(Integer tempEnterOutMode) {
        this.tempEnterOutMode = tempEnterOutMode;
    }

    public Integer getFuzzyMatchMode() {
        return fuzzyMatchMode;
    }

    public void setFuzzyMatchMode(Integer fuzzyMatchMode) {
        this.fuzzyMatchMode = fuzzyMatchMode;
    }

    public Integer getTempCarMinMoney() {
        return tempCarMinMoney;
    }

    public void setTempCarMinMoney(Integer tempCarMinMoney) {
        this.tempCarMinMoney = tempCarMinMoney;
    }

    public Integer getPayTimeOverLimit() {
        return payTimeOverLimit;
    }

    public void setPayTimeOverLimit(Integer payTimeOverLimit) {
        this.payTimeOverLimit = payTimeOverLimit;
    }

    public String getPictureShareFolder() {
        return pictureShareFolder;
    }

    public void setPictureShareFolder(String pictureShareFolder) {
        this.pictureShareFolder = pictureShareFolder;
    }

    public String getDisplayContent() {
        return displayContent;
    }

    public void setDisplayContent(String displayContent) {
        this.displayContent = displayContent;
    }

    public Integer getIsTempOut() {
        return isTempOut;
    }

    public void setIsTempOut(Integer isTempOut) {
        this.isTempOut = isTempOut;
    }

    public Integer getIsMonthOut() {
        return isMonthOut;
    }

    public void setIsMonthOut(Integer isMonthOut) {
        this.isMonthOut = isMonthOut;
    }

    public Integer getIsCenterCharge() {
        return isCenterCharge;
    }

    public void setIsCenterCharge(Integer isCenterCharge) {
        this.isCenterCharge = isCenterCharge;
    }

    public Integer getIsOutCharge() {
        return isOutCharge;
    }

    public void setIsOutCharge(Integer isOutCharge) {
        this.isOutCharge = isOutCharge;
    }

    public Integer getIsTempFastOut() {
        return isTempFastOut;
    }

    public void setIsTempFastOut(Integer isTempFastOut) {
        this.isTempFastOut = isTempFastOut;
    }

    public String getTempCarChannelRight() {
        return tempCarChannelRight;
    }

    public void setTempCarChannelRight(String tempCarChannelRight) {
        this.tempCarChannelRight = tempCarChannelRight;
    }

    public Integer getActiveSetting() {
        return activeSetting;
    }

    public void setActiveSetting(Integer activeSetting) {
        this.activeSetting = activeSetting;
    }

    public Integer getCountDays() {
        return countDays;
    }

    public void setCountDays(Integer countDays) {
        this.countDays = countDays;
    }

    public Integer getTempOnlinePayFreeMinute() {
        return tempOnlinePayFreeMinute;
    }

    public void setTempOnlinePayFreeMinute(Integer tempOnlinePayFreeMinute) {
        this.tempOnlinePayFreeMinute = tempOnlinePayFreeMinute;
    }

    public Integer getTempOnlinePayTimeoutStd() {
        return tempOnlinePayTimeoutStd;
    }

    public void setTempOnlinePayTimeoutStd(Integer tempOnlinePayTimeoutStd) {
        this.tempOnlinePayTimeoutStd = tempOnlinePayTimeoutStd;
    }

    public String getIsUnionpay() {
        return isUnionpay;
    }

    public void setIsUnionpay(String isUnionpay) {
        this.isUnionpay = isUnionpay;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getNoAnswerWait() {
        return noAnswerWait;
    }

    public void setNoAnswerWait(Integer noAnswerWait) {
        this.noAnswerWait = noAnswerWait;
    }

    public Integer getNoAvailableWait() {
        return noAvailableWait;
    }

    public void setNoAvailableWait(Integer noAvailableWait) {
        this.noAvailableWait = noAvailableWait;
    }

    public Integer getEnableChange() {
        return enableChange;
    }

    public void setEnableChange(Integer enableChange) {
        this.enableChange = enableChange;
    }

    public Integer getEnablePutMoneyCall() {
        return enablePutMoneyCall;
    }

    public void setEnablePutMoneyCall(Integer enablePutMoneyCall) {
        this.enablePutMoneyCall = enablePutMoneyCall;
    }


    public Integer getPositionOneMoney() {
        return positionOneMoney;
    }

    public void setPositionOneMoney(Integer positionOneMoney) {
        this.positionOneMoney = positionOneMoney;
    }

    public Integer getPositionTwoMoney() {
        return positionTwoMoney;
    }

    public void setPositionTwoMoney(Integer positionTwoMoney) {
        this.positionTwoMoney = positionTwoMoney;
    }

    public Integer getIsPrintMesssage() {
        return isPrintMesssage;
    }

    public void setIsPrintMesssage(Integer isPrintMesssage) {
        this.isPrintMesssage = isPrintMesssage;
    }

    public String getPrintMessage() {
        return printMessage;
    }

    public void setPrintMessage(String printMessage) {
        this.printMessage = printMessage;
    }


    public Integer getIsInvoiceVoucher() {
        return isInvoiceVoucher;
    }

    public void setIsInvoiceVoucher(Integer isInvoiceVoucher) {
        this.isInvoiceVoucher = isInvoiceVoucher;
    }

    public String getInvoiceVoucherMessage() {
        return invoiceVoucherMessage;
    }

    public void setInvoiceVoucherMessage(String invoiceVoucherMessage) {
        this.invoiceVoucherMessage = invoiceVoucherMessage;
    }

    public Integer getIsNoPlateVoucher() {
        return isNoPlateVoucher;
    }

    public void setIsNoPlateVoucher(Integer isNoPlateVoucher) {
        this.isNoPlateVoucher = isNoPlateVoucher;
    }

    public Integer getIsMotoVoucher() {
        return isMotoVoucher;
    }

    public void setIsMotoVoucher(Integer isMotoVoucher) {
        this.isMotoVoucher = isMotoVoucher;
    }

    public String getNoPlatePaymentUrl() {
        return noPlatePaymentUrl;
    }

    public void setNoPlatePaymentUrl(String noPlatePaymentUrl) {
        this.noPlatePaymentUrl = noPlatePaymentUrl;
    }

    public String getMonthOnlineRenewalType() {
        return monthOnlineRenewalType;
    }

    public void setMonthOnlineRenewalType(String monthOnlineRenewalType) {
        this.monthOnlineRenewalType = monthOnlineRenewalType;
    }

    public String getIsCarTypeCharge() {
        return isCarTypeCharge;
    }

    public void setIsCarTypeCharge(String isCarTypeCharge) {
        this.isCarTypeCharge = isCarTypeCharge;
    }

    public String getCarRecognizeDegree() {
        return carRecognizeDegree;
    }

    public void setCarRecognizeDegree(String carRecognizeDegree) {
        this.carRecognizeDegree = carRecognizeDegree;
    }

    public String getCompareCarType() {
        return compareCarType;
    }

    public void setCompareCarType(String compareCarType) {
        this.compareCarType = compareCarType;
    }

    public String getIsEnterConfirm() {
        return isEnterConfirm;
    }

    public void setIsEnterConfirm(String isEnterConfirm) {
        this.isEnterConfirm = isEnterConfirm;
    }

    public String getIsConfirmOpenStrobe() {
        return isConfirmOpenStrobe;
    }

    public void setIsConfirmOpenStrobe(String isConfirmOpenStrobe) {
        this.isConfirmOpenStrobe = isConfirmOpenStrobe;
    }

    @Override
    public String toString() {
        return "ParkConfig{" +
                "id=" + id +
                ", parkId=" + parkId +
                ", cityNo='" + cityNo + '\'' +
                ", expenseStandardNo=" + expenseStandardNo +
                ", parkMode=" + parkMode +
                ", expenseStandardType=" + expenseStandardType +
                ", isDiscount=" + isDiscount +
                ", isCautionDay=" + isCautionDay +
                ", isPrintInvoice=" + isPrintInvoice +
                ", isEnableFree=" + isEnableFree +
                ", isEnableMonthlyTempPark=" + isEnableMonthlyTempPark +
                ", isVideoSurveillance=" + isVideoSurveillance +
                ", isBluetoothRegulate=" + isBluetoothRegulate +
                ", useOverDays=" + useOverDays +
                ", isEnableCarBlackList=" + isEnableCarBlackList +
                ", playSoundTime='" + playSoundTime + '\'' +
                ", carNoFirstName='" + carNoFirstName + '\'' +
                ", carReliability=" + carReliability +
                ", basementCarportCnt=" + basementCarportCnt +
                ", groundCarportCnt=" + groundCarportCnt +
                ", tempCarportCnt=" + tempCarportCnt +
                ", usedTempCarportCnt=" + usedTempCarportCnt +
                ", tempEnterOutMode=" + tempEnterOutMode +
                ", fuzzyMatchMode=" + fuzzyMatchMode +
                ", tempCarMinMoney=" + tempCarMinMoney +
                ", payTimeOverLimit=" + payTimeOverLimit +
                ", pictureShareFolder='" + pictureShareFolder + '\'' +
                ", displayContent='" + displayContent + '\'' +
                ", isTempOut=" + isTempOut +
                ", isMonthOut=" + isMonthOut +
                ", isCenterCharge=" + isCenterCharge +
                ", isOutCharge=" + isOutCharge +
                ", isTempFastOut=" + isTempFastOut +
                ", tempCarChannelRight='" + tempCarChannelRight + '\'' +
                ", activeSetting=" + activeSetting +
                ", countDays=" + countDays +
                ", isOnlinePay='" + isOnlinePay + '\'' +
                ", tempOnlinePayFreeMinute=" + tempOnlinePayFreeMinute +
                ", tempOnlinePayTimeoutStd=" + tempOnlinePayTimeoutStd +
                ", isUnionpay='" + isUnionpay + '\'' +
                ", createTime='" + createTime + '\'' +
                ", enableChange=" + enableChange +
                ", enablePutMoneyCall=" + enablePutMoneyCall +
                ", noAnswerWait=" + noAnswerWait +
                ", positionOneInit=" + positionOneInit +
                ", positionTwoInit=" + positionTwoInit +
                ", positionOneMoney=" + positionOneMoney +
                ", positionTwoMoney=" + positionTwoMoney +
                ", noAvailableWait=" + noAvailableWait +
                ", isPrintMesssage=" + isPrintMesssage +
                ", printMessage='" + printMessage + '\'' +
                ", changeMessage='" + changeMessage + '\'' +
                ", invoiceMessage='" + invoiceMessage + '\'' +
                ", isInvoiceVoucher=" + isInvoiceVoucher +
                ", invoiceVoucherMessage='" + invoiceVoucherMessage + '\'' +
                ", isNoPlateVoucher=" + isNoPlateVoucher +
                ", isMotoVoucher=" + isMotoVoucher +
                ", motoChargeType='" + motoChargeType + '\'' +
                ", noPlatePaymentUrl='" + noPlatePaymentUrl + '\'' +
                ", isCarMatch=" + isCarMatch +
                ", carMatchDegree='" + carMatchDegree + '\'' +
                ", carModeServerUrl='" + carModeServerUrl + '\'' +
                ", carMatchServerUrl='" + carMatchServerUrl + '\'' +
                ", monthOnlineRenewalType='" + monthOnlineRenewalType + '\'' +
                ", isCarTypeCharge='" + isCarTypeCharge + '\'' +
                ", carRecognizeDegree='" + carRecognizeDegree + '\'' +
                ", compareCarType='" + compareCarType + '\'' +
                ", isEnterConfirm='" + isEnterConfirm + '\'' +
                ", isConfirmOpenStrobe='" + isConfirmOpenStrobe + '\'' +
                ", strictEnterDays='" + strictEnterDays + '\'' +
                ", strictEnterIgnoreDates='" + strictEnterIgnoreDates + '\'' +
                ", isActivatePoliceCar=" + isActivatePoliceCar +
                ", isActivateSoldierCar=" + isActivateSoldierCar +
                '}';
    }
}
