package com.cskaoyan.liu.mmdusedemo.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cskaoyan.liu.mmdusedemo.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private TextView toolbar_title;
    private FrameLayout frame_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        this.setSupportActionBar(toolbar);
        toolbar.setTitle("试试看显示吗");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//这句好像是使toolbar不要显示默认的title



        //将mDrawerLayout与toolbar绑定起来，并且初始化ActionBarDrawerToggle，就是那个会转的按钮，将其添加为mDrawerLayout
        //的监听器，自动监听NavigationView的toggle状态
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);



        //为navigationView添加监听器
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        frame_content = (FrameLayout) findViewById(R.id.frame_content);
        setupDrawerContent(mNavigationView);
        switchToMain();//第一次切换到显示mainFragment
    }


    private void setupDrawerContent(NavigationView mNavigationView) {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_item_main:
                        switchToMain();
                        break;
                    case R.id.navigation_item_tl:
                        switchToExample();
                        break;
                    case R.id.navigation_item_snackbar:
                        switchToBlog();
                        break;
                }

                //切换fragment后将被点击的item设置为checked状态，并且关闭左侧边栏,抽屉？
                item.setCheckable(true);
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void switchToMain() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
        toolbar_title.setText("主页");
    }

    private void switchToExample() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new TextInputLayout()).commit();
        toolbar_title.setText("MD输入框");
    }

    private void switchToBlog() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new Snackbar()).commit();
        toolbar_title.setText("Snackbar");
    }


}
