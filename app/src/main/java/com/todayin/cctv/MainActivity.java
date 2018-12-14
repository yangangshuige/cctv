package com.todayin.cctv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.base.library.activity.BaseActivity;
import com.device.DeviceInfo;
import com.device.DeviceSet;
import com.example.vzvision.DeviceActivity;
import com.example.vzvision.ViewSetInnerType;
import com.todayin.cctv.adapter.DeviceAdapter;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class MainActivity extends BaseActivity {
    public static final int StopVedio = 0x20001;
    public static final int StartVedio = 0x20002;
    public static final int SelectVedio = 0x20009;
    public static final int ConfigDeivce = 0x20010;
    public static final int DClickVedio = 0x200011;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String TAG = "MainActivity";
    private DeviceAdapter adapter;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int vediosetid = msg.arg1;
            switch (msg.what) {
                case SelectVedio:
                    Toast.makeText(MainActivity.this, "选中设备", Toast.LENGTH_SHORT).show();
                    break;
                case DClickVedio:
                    Toast.makeText(MainActivity.this, "双击设备", Toast.LENGTH_SHORT).show();
                    break;
                case ConfigDeivce:
                    Toast.makeText(MainActivity.this, "配置设备", Toast.LENGTH_SHORT).show();
                case StopVedio:
                    Toast.makeText(MainActivity.this, "停止播放", Toast.LENGTH_SHORT).show();
                    break;
                case StartVedio:
                    Toast.makeText(MainActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(MainActivity.this, "未知消息", Toast.LENGTH_SHORT).show();
                    break;
            }

        }

    };
    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
    }

    @Override
    public void initData() {
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deviceInfos.add(new DeviceInfo(10 + i));
        }
        adapter = new DeviceAdapter(this,handler, deviceInfos);
        GridLayoutManager gm = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gm);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRetry() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }



}
