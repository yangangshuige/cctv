package com.todayin.cctv;

import android.os.Bundle;
import android.view.View;
import com.base.library.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    public static final int StopVedio = 0x20001;
    public static final int StartVedio = 0x20002;
    public static final int SelectVedio = 0x20009;
    public static final int ConfigDeivce = 0x20010;
    public static final int DClickVedio = 0x200011;
    private String TAG="MainActivity";

    @Override
    public int getContentLayout() {
        return R.layout.activity_camera_main;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }
    public String DeviceName = "设备1";
    public static String ip = "192.168.0.113";
    public static int port = 8131;
    public static String username = "admin";
    public static String userpassword = "admin";
    public static int hand = 0;
    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


}
