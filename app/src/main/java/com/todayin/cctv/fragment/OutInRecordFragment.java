package com.todayin.cctv.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.library.fragment.BaseFragment;
import com.todayin.cctv.R;
import com.todayin.cctv.adapter.OutInRecordAdapter;
import com.todayin.cctv.utils.DataUtils;
import butterknife.BindView;

public class OutInRecordFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    public static final String RECORD_IN = "In";
    public static final String RECORD_OUT = "Out";
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String mParam1;
    private OutInRecordAdapter outInRecordAdapter;


    public OutInRecordFragment() {
        // Required empty public constructor
    }

    public static OutInRecordFragment newInstance(String param1) {
        OutInRecordFragment fragment = new OutInRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_out_in_record;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        outInRecordAdapter = new OutInRecordAdapter(DataUtils.getStringList(10));
        LinearLayoutManager lm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(outInRecordAdapter);
    }

    @Override
    public void initData() {

    }

}
