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
import com.todayin.cctv.fragment.ShareCarFragment;
import com.todayin.cctv.fragment.WhiteOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yg on 2018/12/21.
 */

public class CarDetailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private BasePagerAdapter pagerAdapter;
    private String[] titles = {"月租车查询", "共享车查询", "白名单查询"};

    @Override
    public int getContentLayout() {
        return R.layout.activity_car_detail;
    }

    @Override
    public void initSwipeBackFinish() {
        super.initSwipeBackFinish();
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        tvTitle.setText("车辆信息");
        pagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), getFragments(), titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MonthCarFragment.newInstance("", ""));
        fragments.add(ShareCarFragment.newInstance("", ""));
        fragments.add(WhiteOrderFragment.newInstance("", ""));
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
