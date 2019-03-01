package com.todayin.cctv.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.todayin.cctv.R;
import com.todayin.cctv.bean.Car;
import com.todayin.cctv.utils.DataUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yg on 2018/12/20.
 */

public class ParkOutAdapter extends BaseQuickAdapter<Car,BaseViewHolder> {
    public ParkOutAdapter() {
        super(R.layout.item_out_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, Car item) {
        helper.setText(R.id.tv_plate,item.getOutPlateCn());
        helper.setText(R.id.tv_time_in,item.getEnterTime());
        helper.setText(R.id.tv_time_out,item.getOutTime());
        helper.setText(R.id.tv_park_time,getDuration(item.getEnterTime(),item.getOutTime()));
        helper.setText(R.id.tv_money,item.getReceipt()+"");
        helper.setText(R.id.tv_free,item.getReceipt()+"");
        helper.setText(R.id.tv_park_type, DataUtils.getParkType(item.getParkType()));
        helper.setText(R.id.tv_free_type, item.getFreeType()+"");

    }
    public String getDuration(String start,String end){
        long tt=0;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long s1=simpleDateFormat.parse(start).getTime();
            long s2=simpleDateFormat.parse(end).getTime();
            tt=s2-s1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tt/1000*60+"";
    }
}
