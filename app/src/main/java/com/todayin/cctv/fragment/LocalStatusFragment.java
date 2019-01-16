package com.todayin.cctv.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.library.fragment.BaseFragment;
import com.todayin.cctv.R;
import com.todayin.cctv.adapter.DeviceStatusAdapter;
import com.todayin.cctv.adapter.MonthCarAdapter;
import com.todayin.cctv.utils.DataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LocalStatusFragment extends BaseFragment {

    @BindView(R.id.tv_local)
    TextView tvLocal;
    @BindView(R.id.tv_cloud)
    TextView tvCloud;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private DeviceStatusAdapter deviceStatusAdapter;

    public LocalStatusFragment() {
        // Required empty public constructor
    }

    public static LocalStatusFragment newInstance() {
        LocalStatusFragment fragment = new LocalStatusFragment();
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_local_status;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        deviceStatusAdapter = new DeviceStatusAdapter(DataUtils.getStringList(10));
        LinearLayoutManager lm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(deviceStatusAdapter);
    }

    @Override
    public void initData() {

    }

}
