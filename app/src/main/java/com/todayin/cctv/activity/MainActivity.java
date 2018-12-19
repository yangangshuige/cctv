package com.todayin.cctv.activity;

import android.os.Bundle;
import android.view.View;
import com.base.library.activity.BaseActivity;
import com.todayin.cctv.R;

public class MainActivity extends BaseActivity {
//    @BindView(R.id.mGridView)
//    InnerGridView mGridView;
//    HomeAdapter1 homeAdapter;
    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
//        GridLayoutManager gl=new GridLayoutManager(this,2);
//        homeAdapter=new HomeAdapter1(this,R.layout.item_home);
//        mGridView.setAdapter(homeAdapter);
//        homeAdapter.setData(Arrays.asList(getResources().getStringArray(R.array.homeType)));
//        mRecyclerView.setLayoutManager(gl);
//        mRecyclerView.addItemDecoration(new GridSpacesItemDecoration(ContextUtils.dip2px(this, 50.0f), ContextUtils.dip2px(this, 50.0f)));
//        mRecyclerView.setAdapter(homeAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

}
