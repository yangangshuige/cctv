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
import com.todayin.cctv.fragment.LocalStatusFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class SystemStatusActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private BasePagerAdapter pagerAdapter;
    private String[] titles = {"本地状态", "终端报警"};
    @Override
    public int getContentLayout() {
        return R.layout.activity_system_status;
    }
    @Override
    public void initSwipeBackFinish() {
        super.initSwipeBackFinish();
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
    }
    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        tvTitle.setText("系统状态");
        pagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), getFragments(), titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(LocalStatusFragment.newInstance());
        fragments.add(LocalStatusFragment.newInstance());
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
