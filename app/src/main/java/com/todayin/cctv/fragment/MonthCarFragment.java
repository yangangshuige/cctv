package com.todayin.cctv.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.base.library.fragment.BaseFragment;
import com.todayin.cctv.R;
import com.todayin.cctv.adapter.MonthCarAdapter;
import com.todayin.cctv.utils.DataUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class MonthCarFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.edit_plate)
    EditText editPlate;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private String mParam1;
    private String mParam2;
    private MonthCarAdapter monthCarAdapter;

    public MonthCarFragment() {
        // Required empty public constructor
    }

    public static MonthCarFragment newInstance(String param1, String param2) {
        MonthCarFragment fragment = new MonthCarFragment();
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
        return R.layout.fragment_month_car;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        monthCarAdapter = new MonthCarAdapter(DataUtils.getStringList(10));
        LinearLayoutManager lm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(monthCarAdapter);
    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.btn_find, R.id.tv_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_find:
                break;
            case R.id.tv_clean:
                break;
        }
    }
}
