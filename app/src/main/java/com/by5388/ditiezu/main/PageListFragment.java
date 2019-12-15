package com.by5388.ditiezu.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentPageListBinding;

import java.util.Objects;

/**
 * @author by5388  on 2019/12/15.
 */
public class PageListFragment extends Fragment {
    public static PageListFragment newInstance() {
        return new PageListFragment();
    }

    private PageAdapter mAdapter;

    public PageAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new PageAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentPageListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_list, container, false);
        binding.setFragment(this);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        return binding.getRoot();
    }
}
