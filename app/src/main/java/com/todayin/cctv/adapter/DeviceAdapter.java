package com.todayin.cctv.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.device.DeviceInfo;
import com.device.VedioSetVeiw;
import com.todayin.cctv.R;
import java.util.List;

/**
 * Created by yg on 2018/12/14.
 */

public class DeviceAdapter extends BaseQuickAdapter<DeviceInfo,BaseViewHolder> {
    private Context mContext;
    private Handler handler;
    public DeviceAdapter(Context context,Handler handler,@Nullable List<DeviceInfo> data) {
        super(R.layout.item_device, data);
        mContext=context;
        this.handler=handler;
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceInfo item) {
       FrameLayout frameLayout= helper.getView(R.id.container);
        VedioSetVeiw vsv = new VedioSetVeiw(mContext);
        vsv.sethandle(handler);
        vsv.setId(item.id);
       frameLayout.addView(vsv);
    }
}
