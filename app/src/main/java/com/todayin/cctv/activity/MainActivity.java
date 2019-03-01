package com.todayin.cctv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.library.activity.BaseActivity;
import com.base.library.bean.ParkInfo;
import com.base.library.bean.RobotConfig;
import com.base.library.net.ApiConstants;
import com.base.library.net.BaseObserver;
import com.base.library.net.BaseResponse;
import com.base.library.net.RxSchedulers;
import com.base.library.net.TestService;
import com.example.vzvision.CameraActivity;
import com.todayin.cctv.R;
import com.todayin.cctv.net.ApiService;
import com.todayin.cctv.net.GetConfigRequest;
import com.todayin.cctv.net.OldApiManager;
import com.todayin.cctv.utils.DataManager;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.callkit.RongCallAction;
import io.rong.callkit.RongVoIPIntent;
import io.rong.imlib.model.Conversation;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    private String TAG = "MainActivity";
    private String androidId = "1000005";

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        getParkInfo();

    }

    public void getParkInfo() {
        OldApiManager.getInstence().getService(ApiService.class).getParkInfo(androidId)
                .compose(RxSchedulers.<BaseResponse<ParkInfo>>applySchedulers())
                .subscribe(new BaseObserver<BaseResponse<ParkInfo>>() {
                    @Override
                    public void onSuccess(BaseResponse<ParkInfo> parkInfoBaseResponse) {
                        Log.e(TAG, parkInfoBaseResponse.getData().toString());
                        if (parkInfoBaseResponse.isResponseSuccess()) {
                            Log.e(TAG, parkInfoBaseResponse.getData().toString());
                            String parkId = parkInfoBaseResponse.getData().getParkNum();
                            DataManager.getInstance().setParkId(parkId);
                            loadRobotConfigs(parkId);
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });

    }

    private void loadRobotConfigs(final String parkId) {        //请求机器人配置信息
        GetConfigRequest request = new GetConfigRequest();
        request.setParkId(parkId);
        request.setChannelId("");
        request.setRobotId(androidId);
        OldApiManager.getInstence().getService(ApiService.class).getRobotConfigs(request)
                .compose(RxSchedulers.<BaseResponse<RobotConfig>>applySchedulers())
                .subscribe(new BaseObserver<BaseResponse<RobotConfig>>() {
                    @Override
                    public void onSuccess(BaseResponse<RobotConfig> robotConfigBaseResponse) {
                        Log.e(TAG, robotConfigBaseResponse.getData().toString());
                        if (robotConfigBaseResponse.isResponseSuccess()) {
                            DataManager.getInstance().saveRobotConf(robotConfigBaseResponse.getData());
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.tv_change, R.id.tv_out, R.id.tv_exit, R.id.tv_detail_in, R.id.tv_detail_out, R.id.tv_detail_car, R.id.tv_out_in, R.id.tv_system, R.id.tv_cctv})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_change:
                 intent = new Intent(RongVoIPIntent.RONG_INTENT_ACTION_VOIP_SINGLEVIDEO);
                intent.putExtra("conversationType", Conversation.ConversationType.PRIVATE.getName().toLowerCase(Locale.US));
                intent.putExtra("targetId", "1");
                intent.putExtra("callAction", RongCallAction.ACTION_OUTGOING_CALL.getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.setPackage(getPackageName());
                getApplicationContext().startActivity(intent);
                break;
            case R.id.tv_out:
                break;
            case R.id.tv_exit:
                break;
            case R.id.tv_detail_in:
                intent = new Intent(this, InParkDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_detail_out:
                intent = new Intent(this, OutParkDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_detail_car:
                intent = new Intent(this, CarDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_out_in:
                intent = new Intent(this, OutInRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_system:
                intent = new Intent(this, SystemStatusActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_cctv:
                intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}
