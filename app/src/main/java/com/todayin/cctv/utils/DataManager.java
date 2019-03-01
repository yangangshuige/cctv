package com.todayin.cctv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.base.library.bean.ParkConfig;
import com.base.library.bean.RobotConf;
import com.example.vzvision.GlobalVariable;


/**
 * Created by yg on 2019/2/20.
 */

public class DataManager {
    private static final String PREF_NAME = "robot";
    private static SharedPreferences mPref;
    private volatile static DataManager instance;
    private static String PARK_ID = "park_id";
    private static final String CACHE_CONF_ROBOT = "cache_conf_robot";
    private static final String CACHE_CONF_PARK = "cache_conf_park";
    private ACache mACache;

    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager();
                }
            }
        }
        return instance;

    }

    private DataManager() {
        init(GlobalVariable.getInstance());
    }

    private void init(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mACache = ACache.get(context);
    }

    public static void setParkId(String parkId) {
        mPref.edit().putString(PARK_ID, parkId).apply();
    }

    public static String getParkId() {
        return mPref.getString(PARK_ID, "");
    }

    public ParkConfig getCurParkConf() {
        return (ParkConfig) mACache.getAsObject(CACHE_CONF_PARK);
    }

    public void saveParkConf(ParkConfig config) {
        mACache.put(CACHE_CONF_PARK, config);
    }

    public RobotConf getCurRobotConf() {
        return (RobotConf) mACache.getAsObject(CACHE_CONF_ROBOT);
    }

    public void saveRobotConf(RobotConf config) {
        mACache.put(CACHE_CONF_ROBOT, config);
    }
}
