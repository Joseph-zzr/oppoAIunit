package com.coderpig.oppoaiunit.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CommonViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> mFragments = new ArrayList<>();

    public CommonViewPagerAdapter(@NonNull FragmentManager fm, List<String> title) {
        super(fm);
        this.title = title;
    }

    public CommonViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment){
        mFragments.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

}
