package com.todayin.cctv.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.base.library.activity.BaseActivity;
import com.todayin.cctv.R;
import com.todayin.cctv.adapter.ParkInAdapter;
import com.todayin.cctv.utils.DataUtils;
import com.xjs.example.time.view.DateTimePickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InParkDetailActivity extends BaseActivity {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.edit_plate)
    EditText editPlate;
    @BindView(R.id.sp_car_type)
    Spinner spCarType;
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
    @BindView(R.id.cb_time_order)
    CheckBox cbTimeOrder;
    @BindView(R.id.sp_time_order)
    Spinner spTimeOrder;
    @BindView(R.id.cb_confidence)
    CheckBox cbConfidence;
    @BindView(R.id.sp_confidence)
    Spinner spConfidence;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.edit_current_plate)
    EditText editCurrentPlate;
    @BindView(R.id.sp_province)
    Spinner spProvince;
    @BindView(R.id.edit_new_plate)
    EditText editNewPlate;
    @BindView(R.id.img_plate)
    ImageView imgPlate;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ParkInAdapter parkInAdapter;
    private DateTimePickerView mDateTimePickerView;
    private int m_editDateId;

    @Override
    public int getContentLayout() {
        return R.layout.activity_in_park_detail;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mDateTimePickerView = new DateTimePickerView(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btn_ok) {
                    if (R.id.button_chooseStartDate == m_editDateId) {
                        tvStartDate.setText(mDateTimePickerView.wheelMain.getTime());
                    } else if (R.id.button_chooseEndDate == m_editDateId) {
                        tvEndDate.setText(mDateTimePickerView.wheelMain.getTime());
                    }
                }

                mDateTimePickerView.dismiss();
            }
        });
        mDateTimePickerView.setOutsideTouchable(true);

        ArrayAdapter<CharSequence> carAdapter = ArrayAdapter.createFromResource(
                this, R.array.carType, R.layout.item_sp_view);
        carAdapter.setDropDownViewResource(R.layout.item_sp_dropview);
        spCarType.setAdapter(carAdapter);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(
                this, R.array.order, R.layout.item_sp_view);
        timeAdapter.setDropDownViewResource(R.layout.item_sp_dropview);
        spTimeOrder.setAdapter(timeAdapter);
        ArrayAdapter<CharSequence> conAdapter = ArrayAdapter.createFromResource(
                this, R.array.order, R.layout.item_sp_view);
        conAdapter.setDropDownViewResource(R.layout.item_sp_dropview);
        spConfidence.setAdapter(conAdapter);
        ArrayAdapter<CharSequence> proAdapter = ArrayAdapter.createFromResource(
                this, R.array.province, R.layout.item_sp_view);
        proAdapter.setDropDownViewResource(R.layout.item_sp_dropview);
        spProvince.setAdapter(proAdapter);
    }

    @Override
    public void initData() {
        parkInAdapter = new ParkInAdapter(DataUtils.getStringList(10));
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(parkInAdapter);
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

    @OnClick({R.id.img_back, R.id.button_chooseStartDate, R.id.button_chooseEndDate, R.id.btn_find, R.id.tv_clean, R.id.btn_update})
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
                break;
            case R.id.tv_clean:
                break;
            case R.id.btn_update:
                break;
        }
    }

    @Override
    public void onRetry() {

    }

}
