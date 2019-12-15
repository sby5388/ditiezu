package com.by5388.ditiezu.main;

import androidx.fragment.app.Fragment;

import com.by5388.ditiezu.SingleFragmentActivity;

/**
 * @author Administrator
 */
public class MainActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return PageListFragment.newInstance();
    }


}
