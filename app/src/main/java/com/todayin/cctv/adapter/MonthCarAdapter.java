package com.todayin.cctv.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.todayin.cctv.R;

import java.util.List;

/**
 * Created by yg on 2018/12/21.
 */

public class MonthCarAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MonthCarAdapter(@Nullable List<String> data) {
        super(R.layout.item_month_car,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
