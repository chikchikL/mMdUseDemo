package com.cskaoyan.liu.mmdusedemo.ui.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.cskaoyan.liu.mmdusedemo.R;
import com.cskaoyan.liu.mmdusedemo.entity.RecyclerBean;
import com.cskaoyan.liu.mmdusedemo.ui.fragment.DetailFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecyclerDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private ImageView ivImage;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private RecyclerBean main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_detail);

        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//在toolbar的左边显示一个返回按钮

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();//执行返回操作
            }
        });

        main = (RecyclerBean) getIntent().getSerializableExtra("main");
        collapsing_toolbar.setTitle(main.getTitle());//用collapsing_toolbar设置标题才会有渐变效果

        ivImage.setBackgroundResource(main.getImg());

        //初始化下方的ViewPager,这是在vp中用fragment展示内容
        setupViewPager(viewpager);


        //将tablayout与ViewPager绑定
        tabLayout.addTab(tabLayout.newTab().setText("测试1"));
        tabLayout.addTab(tabLayout.newTab().setText("测试2"));
        tabLayout.addTab(tabLayout.newTab().setText("测试3"));
        tabLayout.setupWithViewPager(viewpager);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(main.getTitle()), "测试");
        adapter.addFragment(DetailFragment.newInstance(main.getTitle()), "测试");
        adapter.addFragment(DetailFragment.newInstance(main.getTitle()), "测试");
        mViewPager.setAdapter(adapter);
    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
