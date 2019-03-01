package com.todayin.cctv.net;


import com.example.vzvision.GlobalVariable;
public class BaseRequest extends BaseModel {

	/**接口共有参数
     * */
    public String v= GlobalVariable.getInstance().getVersionName();//版本名
    public String device = "android";//设备类型
    public String ttid = "1";

    public BaseRequest() {
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

}
