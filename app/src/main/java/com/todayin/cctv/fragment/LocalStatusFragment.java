package com.todayin.cctv.fragment;

import android.os.Bundle;
import android.view.View;
import com.base.library.fragment.BaseFragment;
import com.todayin.cctv.R;

public class LocalStatusFragment extends BaseFragment {

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

    }

    @Override
    public void initData() {

    }

}
