package com.coderpig.oppoaiunit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.coderpig.oppoaiunit.adapter.CommonViewPagerAdapter;
import com.coderpig.oppoaiunit.module.home.HomeFragment;
import com.coderpig.oppoaiunit.module.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.yechaoa.yutils.SpUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);

            setSupportActionBar(mToolbar);

            initDrawerLayout();

            initFragment();

            initListener();
        }
    /**
     * 给appbar 添加按钮
     */
    private void initDrawerLayout() {
        //Toggle开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar
                ,R.string.navigation_drawer_open
                ,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager());
        //加入fragment
        commonViewPagerAdapter.addFragment(new HomeFragment());
        commonViewPagerAdapter.addFragment(new SettingFragment());

        //预加载页面设置
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(commonViewPagerAdapter);
    }

    private void initListener() {
        mNavView.setNavigationItemSelectedListener(this);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * ViewPager滑动事件
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mBottomNavigation.getMenu().getItem(position).setChecked(true);
                    mToolbar.setTitle(getResources().getString(R.string.title_home));
                    break;
                case 1:
                    mBottomNavigation.getMenu().getItem(position+1).setChecked(true);
                    mToolbar.setTitle(getResources().getString(R.string.title_tree));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 底部菜单事件
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_tree:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_camera:

                    return true;
            }
            return false;
        }
    };

    /**
     * 侧边栏点击事件
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_collect) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            //  dialog提示

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

