package com.example.vzvision;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.base.library.activity.BaseActivity;
import com.base.library.net.ApiConstants;
import com.todayin.cctv.R;
import com.todayin.cctv.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.edit_user)
    EditText editUser;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.btn_check)
    CheckBox btnCheck;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        login(ApiConstants.USER_B);
    }
    public  void login(String cacheToken){
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {
                    Log.d("LoginActivity", "--onSuccess" + s);
                   showToast("成功");
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {

                }
            });
        }
    }
    @Override
    public void onRetry() {

    }


}
