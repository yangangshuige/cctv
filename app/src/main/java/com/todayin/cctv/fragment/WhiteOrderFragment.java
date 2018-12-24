package com.todayin.cctv.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.base.library.fragment.BaseFragment;
import com.todayin.cctv.R;
import com.todayin.cctv.adapter.ChannelAdapter;
import com.todayin.cctv.adapter.MonthCarAdapter;
import com.todayin.cctv.utils.DataUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WhiteOrderFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycle_channel)
    RecyclerView recycleChannel;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private String mParam1;
    private String mParam2;
    private ChannelAdapter channelAdapter;
    private MonthCarAdapter monthCarAdapter;

    public WhiteOrderFragment() {
    }

    public static WhiteOrderFragment newInstance(String param1, String param2) {
        WhiteOrderFragment fragment = new WhiteOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_white_order;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        channelAdapter=new ChannelAdapter(DataUtils.getStringList(5));
        LinearLayoutManager lm=new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recycleChannel.setLayoutManager(lm);
        recycleChannel.setAdapter(channelAdapter);

        monthCarAdapter = new MonthCarAdapter(DataUtils.getStringList(10));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(monthCarAdapter);
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.btn_find)
    public void onViewClicked() {
    }
}
