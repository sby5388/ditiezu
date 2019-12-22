package com.by5388.ditiezu.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.by5388.ditiezu.DitiezuApp;

/**
 * @author by5388  on 2019/12/22.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    MainFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return DitiezuApp.getInstance().getModuleBeans().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return DitiezuApp.getInstance().getModuleBeans().get(position).getName();
    }
}
