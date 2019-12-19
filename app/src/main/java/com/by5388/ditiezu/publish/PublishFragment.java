package com.by5388.ditiezu.publish;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentPublishBinding;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author by5388  on 2019/12/15.
 */
public class PublishFragment extends Fragment {
    public static final String TAG = "PublishFragment";
    private static final String PAGE_ID = "pageId";
    private Handler mHandler = new Handler();
    private int mPageId = 46;
    private FragmentPublishBinding mBinding;

    public static PublishFragment newInstance(int id) {
        final PublishFragment fragment = new PublishFragment();
        final Bundle args = new Bundle();
        args.putInt(PAGE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = Objects.requireNonNull(getArguments());
        mPageId = arguments.getInt(PAGE_ID, mPageId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_publish, container, false);
        return mBinding.getRoot();

    }
}
