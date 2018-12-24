package com.todayin.cctv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.base.library.activity.BaseActivity;
import com.example.vzvision.CameraActivity;
import com.todayin.cctv.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.tv_change, R.id.tv_out, R.id.tv_exit, R.id.tv_detail_in, R.id.tv_detail_out, R.id.tv_detail_car, R.id.tv_out_in, R.id.tv_system, R.id.tv_cctv})
    public void onViewClicked(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.tv_change:
                break;
            case R.id.tv_out:
                break;
            case R.id.tv_exit:
                break;
            case R.id.tv_detail_in:
                intent=new Intent(this,InParkDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_detail_out:
                intent=new Intent(this,OutParkDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_detail_car:
                intent=new Intent(this,CarDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_out_in:
                break;
            case R.id.tv_system:
                intent=new Intent(this,SystemStatusActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_cctv:
                intent=new Intent(this,CameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}
