package com.example.vzvision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.base.library.activity.BaseActivity;
import com.todayin.cctv.R;
import com.todayin.cctv.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    }

    @Override
    public void onRetry() {

    }


}
