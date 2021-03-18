package com.by5388.ditiezu.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentMainBinding;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author by5388  on 2019/12/22.
 */
public class MainFragment extends Fragment {
    public static final String INTENT_FILTER_LOAD_FINISH = "intent_filter_load_finish";
    private static final String TAG = "MainFragment";
    private static final String KEY_INDEX = "index";
    private CityAdapter mAdapter;
    private Callback mCallback;
    private FragmentMainBinding mBinding;
    private RefreshBroadcastReceiver mReceiver;

    public static MainFragment newInstance(final int index) {
        final MainFragment fragment = new MainFragment();
        final Bundle args = new Bundle();
        args.putInt(KEY_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    public CityAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int index = Objects.requireNonNull(getArguments()).getInt(KEY_INDEX, 0);
        final DitiezuApp app = DitiezuApp.getInstance();
        final ModuleBean moduleBean = app.getModuleBeans().get(index);
        mAdapter = new CityAdapter(moduleBean.getCityBeans());
        mReceiver = new RefreshBroadcastReceiver();
        requireContext().registerReceiver(mReceiver, new IntentFilter(INTENT_FILTER_LOAD_FINISH));
    }

    @Override
    public void onDestroy() {
        requireContext().unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mBinding.setFragment(this);
        mBinding.swipeRefreshLayout.setColorSchemeColors(getColor(R.color.colorPrimary_0), getColor(R.color.colorPrimaryDark));
        mBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCallback.refresh();
            }
        });
        return mBinding.getRoot();
    }

    private int getColor(int colorId) {
        final Context context = Objects.requireNonNull(getContext());
        return getResources().getColor(colorId, context.getTheme());
    }

    public interface Callback {
        void refresh();
    }

    private class RefreshBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mBinding.swipeRefreshLayout.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
        }
    }
}
