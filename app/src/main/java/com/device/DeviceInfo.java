package com.device;


public class DeviceInfo {
    public int handle = 0;
    public int id = 0;
    public String DeviceName = "设备1";
    public String ip = "192.168.0.120";
    public int port = 80;
    public String username = "admin";
    public String userpassword = "admin";

    public DeviceInfo(int idInit) {
        id = idInit;
    }


    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object != null && getClass() == object.getClass()) {
            DeviceInfo other = (DeviceInfo) object;

            if (handle == other.handle &&
                    ip == other.ip &&
                    port == other.port &&
                    username == other.username &&
                    userpassword == other.userpassword) {
                return true;
            }
        }


        return false;
    }

    public int hashCode() {
        return handle;
    }
}