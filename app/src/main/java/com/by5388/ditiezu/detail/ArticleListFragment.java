package com.by5388.ditiezu.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentArticleListBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleListFragment extends Fragment {
    private static final String INTEGER_INDEX = "index";
    private static final int DEFAULT_INDEX = 46;
    private int mIndex = DEFAULT_INDEX;
    private ArticleAdapter mAdapter;

    public static ArticleListFragment newInstance(final int index) {
        final ArticleListFragment fragment = new ArticleListFragment();
        final Bundle args = new Bundle();
        args.putInt(INTEGER_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = getArguments();
        if (arguments != null) {
            mIndex = arguments.getInt(INTEGER_INDEX, DEFAULT_INDEX);
        }
        mAdapter = new ArticleAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentArticleListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false);
        binding.setFragment(this);
        return binding.getRoot();
    }

    public ArticleAdapter getAdapter() {
        return mAdapter;
    }
}
