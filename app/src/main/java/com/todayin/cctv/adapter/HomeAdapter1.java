package com.todayin.cctv.adapter;

import android.content.Context;
import com.base.library.adapter.CommonListAdapter;
import com.todayin.cctv.R;

/**
 * Created by yg on 2018/12/18.
 */

public class HomeAdapter1 extends CommonListAdapter<String> {
    public HomeAdapter1(Context context, int itemLayoutId) {
        super(context, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, String itemBean, int position) {
        holder.setText(R.id.tv_item,itemBean);
    }
}
