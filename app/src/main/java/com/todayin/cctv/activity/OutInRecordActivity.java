package com.todayin.cctv.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.base.library.activity.BaseActivity;
import com.base.library.adapter.BasePagerAdapter;
import com.todayin.cctv.R;
import com.todayin.cctv.fragment.MonthCarFragment;
import com.todayin.cctv.fragment.OutInRecordFragment;
import com.todayin.cctv.fragment.ShareCarFragment;
import com.todayin.cctv.fragment.WhiteOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OutInRecordActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private BasePagerAdapter pagerAdapter;
    private String[] titles = {"入场", "出场"};

    @Override
    public void initSwipeBackFinish() {
        super.initSwipeBackFinish();
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_out_in_record;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        tvTitle.setText("出入流水");
        pagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), getFragments(), titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(OutInRecordFragment.newInstance(OutInRecordFragment.RECORD_IN));
        fragments.add(OutInRecordFragment.newInstance(OutInRecordFragment.RECORD_OUT));
        return fragments;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
