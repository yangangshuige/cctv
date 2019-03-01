package com.todayin.cctv.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.base.library.activity.BaseActivity;
import com.base.library.net.BaseObserver;
import com.base.library.net.RxSchedulers;
import com.todayin.cctv.R;
import com.todayin.cctv.adapter.ParkOutAdapter;
import com.todayin.cctv.bean.Car;
import com.todayin.cctv.net.ApiManager;
import com.todayin.cctv.net.ApiService;
import com.todayin.cctv.net.GetParkCarOutInfoRequest;
import com.todayin.cctv.net.ListResponse;
import com.todayin.cctv.utils.DataManager;
import com.todayin.cctv.utils.DataUtils;
import com.xjs.example.time.view.DateTimePickerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import butterknife.BindView;
import butterknife.OnClick;

public class OutParkDetailActivity extends BaseActivity {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.edit_plate)
    EditText editPlate;
    @BindView(R.id.cb_big)
    CheckBox cbBig;
    @BindView(R.id.cb_small)
    CheckBox cbSmall;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.sp_park_type)
    Spinner spParkType;
    @BindView(R.id.sp_money)
    Spinner spMoney;
    @BindView(R.id.sp_user)
    Spinner spUser;
    @BindView(R.id.btn_find)
    Button btnFind;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.img_in_plate)
    ImageView imgInPlate;
    @BindView(R.id.img_out_plate)
    ImageView imgOutPlate;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ParkOutAdapter parkOutAdapter;
    private DateTimePickerView mDateTimePickerView;
    private int m_editDateId;
    GetParkCarOutInfoRequest parkCarOutInfoRequest=new GetParkCarOutInfoRequest();
    @Override
    public int getContentLayout() {
        return R.layout.activity_out_park_detail;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        tvTitle.setText("出场明细");
        mDateTimePickerView = new DateTimePickerView(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btn_ok) {
                    if (R.id.button_chooseStartDate == m_editDateId) {
                        tvStartDate.setText(mDateTimePickerView.wheelMain.getTime());
                        parkCarOutInfoRequest.setBeginTime(mDateTimePickerView.wheelMain.getTime());
                    } else if (R.id.button_chooseEndDate == m_editDateId) {
                        tvEndDate.setText(mDateTimePickerView.wheelMain.getTime());
                        parkCarOutInfoRequest.setEndTime(mDateTimePickerView.wheelMain.getTime());
                    }
                }

                mDateTimePickerView.dismiss();
            }
        });
        mDateTimePickerView.setOutsideTouchable(true);

        ArrayAdapter<CharSequence> carAdapter = ArrayAdapter.createFromResource(
                this, R.array.carType, R.layout.item_sp_view);
        carAdapter.setDropDownViewResource(R.layout.item_sp_dropview);
        spParkType.setAdapter(carAdapter);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(
                this, R.array.money, R.layout.item_sp_view);
        timeAdapter.setDropDownViewResource(R.layout.item_sp_dropview);
        spMoney.setAdapter(timeAdapter);
        ArrayAdapter<CharSequence> proAdapter = ArrayAdapter.createFromResource(
                this, R.array.user, R.layout.item_sp_view);
        proAdapter.setDropDownViewResource(R.layout.item_sp_dropview);
        spUser.setAdapter(proAdapter);

        cbSmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbAll.setChecked(false);
                    cbBig.setChecked(false);
                }
            }
        });
        cbBig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbAll.setChecked(false);
                    cbSmall.setChecked(false);
                }
            }
        });
        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbSmall.setChecked(false);
                    cbBig.setChecked(false);
                }
            }
        });
    }

    @Override
    public void initData() {
        parkOutAdapter = new ParkOutAdapter();
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(parkOutAdapter);

        parkCarOutInfoRequest.setTeamId(DataManager.getParkId());
    }
    private void editDate(int editDateId) {
        m_editDateId = editDateId;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();    //静态方法初始化，默认得到当前
        switch (m_editDateId) {
            case R.id.button_chooseStartDate: {
                String DateTime = tvStartDate.getText().toString();

                try {
                    Date date = dateFormat.parse(DateTime);

                    calendar.setTime(date);

                    mDateTimePickerView.showAsDropDown(tvStartDate);
                } catch (ParseException e) {
                    Toast.makeText(this, "分析日期出错", Toast.LENGTH_SHORT).show();
                }


            }
            break;
            case R.id.button_chooseEndDate: {
                String DateTime = tvEndDate.getText().toString();

                try {
                    Date date = dateFormat.parse(DateTime);

                    calendar.setTime(date);

                    mDateTimePickerView.showAsDropDown(tvEndDate);
                } catch (ParseException e) {
                    Toast.makeText(this, "分析日期出错", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }



    @OnClick({R.id.img_back, R.id.button_chooseStartDate, R.id.button_chooseEndDate, R.id.tv_clean,R.id.btn_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.button_chooseStartDate:
                editDate(R.id.button_chooseStartDate);
                break;
            case R.id.button_chooseEndDate:
                editDate(R.id.button_chooseEndDate);
                break;
            case R.id.btn_find:
                parkCarOutInfoRequest.setOutPlateCn(editPlate.getText().toString());
                Integer parkMark=null;
                if(cbSmall.isChecked()){
                    parkMark=2;
                }
                if(cbBig.isChecked()){
                    parkMark=1;
                }
                parkCarOutInfoRequest.setParkMark(parkMark);
                parkCarOutInfoRequest.setBeginTime("2018-01-01 00:00:00");
                parkCarOutInfoRequest.setEndTime("2019-02-01 00:00:00");
                parkCarOutInfoRequest.setParkType( spParkType.getSelectedItemPosition());
                parkCarOutInfoRequest.setPageNo(1);
                ApiManager.getInstence().getService(ApiService.class).GetParkOutDetail(parkCarOutInfoRequest)
                        .compose(RxSchedulers.<ListResponse<Car>>applySchedulers())
                        .subscribe(new BaseObserver<ListResponse<Car>>() {
                    @Override
                    public void onSuccess(ListResponse<Car> carBaseResponse) {
                        parkOutAdapter.setNewData(carBaseResponse.getList());
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
                break;
            case R.id.tv_clean:
                parkCarOutInfoRequest=new GetParkCarOutInfoRequest();
                editPlate.setText("");
                tvStartDate.setText("");
                tvEndDate.setText("");
                spUser.setSelection(0);
                spMoney.setSelection(0);
                spParkType.setSelection(0);
                cbSmall.setChecked(false);
                cbBig.setChecked(false);
                cbAll.setChecked(false);
                tvResult.setText("");
                break;
        }
    }
    @Override
    public void onRetry() {

    }
}
