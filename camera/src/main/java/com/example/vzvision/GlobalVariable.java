package com.example.vzvision;

import com.database.*;
import com.crash.*;

import com.device.*;
import com.vz.WlistVehicle;

import android.app.Application;

public class GlobalVariable {

    private plateCallbackInfoTable plateTable = null;
    private DeviceSet devSet = null;
    private SnapImageTable snapImageTable = null;
    private WlistVehicle wlistVechile = null;
    private static GlobalVariable globalVariable;

    public GlobalVariable() {
    }

    public static GlobalVariable getInstence() {
        if (globalVariable == null) {
            globalVariable = new GlobalVariable();
        }
        return globalVariable;
    }

    void setplateCallbackInfoTable(plateCallbackInfoTable table) {
        plateTable = table;
    }

    SnapImageTable getSnapImageTable() {
        return snapImageTable;
    }

    void setSnapImageTable(SnapImageTable table) {
        snapImageTable = table;
    }

    plateCallbackInfoTable getplateCallbackInfoTable() {
        return plateTable;
    }

    void setDeviceSet(DeviceSet ds) {
        devSet = ds;
    }

    DeviceSet getDeviceSet() {
        return devSet;
    }

    void setWlistVehicle(WlistVehicle ds) {
        wlistVechile = ds;
    }

    WlistVehicle getWlistVehicle() {
        return wlistVechile;
    }
}
