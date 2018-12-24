package com.example.vzvision;

import com.base.library.app.BaseApp;
import com.database.*;
import com.crash.*;
import com.device.*;
import com.vz.WlistVehicle;


public class GlobalVariable extends BaseApp {
    
	private plateCallbackInfoTable  plateTable =null;
	private DeviceSet devSet = null;
	private SnapImageTable snapImageTable = null;
	private WlistVehicle wlistVechile = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
//		  CrashHandler crashHandler = CrashHandler.getInstance();
//	      crashHandler.init(getApplicationContext());
	}
	
	void setplateCallbackInfoTable(plateCallbackInfoTable table)
	{
		plateTable = table;
	}
	
	SnapImageTable getSnapImageTable()
	{
		return snapImageTable;
	}
	
	void setSnapImageTable(SnapImageTable table)
	{
		snapImageTable = table;
	}
	
	plateCallbackInfoTable getplateCallbackInfoTable()
	{
		return plateTable;
	}
	
	void setDeviceSet(DeviceSet ds)
	{
		devSet = ds;
	}
	
	DeviceSet getDeviceSet()
	{
		return devSet;
	}
	
	void setWlistVehicle(WlistVehicle ds)
	{
		wlistVechile = ds;
	}
	
	WlistVehicle getWlistVehicle()
	{
		return wlistVechile;
	}
}
