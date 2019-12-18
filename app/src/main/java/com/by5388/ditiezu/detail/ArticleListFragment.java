package com.by5388.ditiezu.detail;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentArticleListBinding;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

/**
 * @author Administrator  on 2019/12/17.
 */
public class ArticleListFragment extends Fragment implements ArticleAdapter.PageChangCallback {
    private static final String INTEGER_INDEX = "index";
    private static final int DEFAULT_INDEX = 46;
    private int mIndex = DEFAULT_INDEX;
    private ArticleAdapter mAdapter;
    private ArticleListTool mTool;
    private FragmentArticleListBinding mBinding;
    private Handler mHandler = new Handler();

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
        mAdapter.setCallback(this);
        mTool = new ArticleListTool(mIndex);

        final DitiezuApp app = DitiezuApp.getInstance();
        app.getExecutor().execute(mRunnableGetData);

    }


    /**
     * run on workerThread
     */
    private Runnable mRunnableGetData = new Runnable() {
        @Override
        public void run() {
            try {
                mTool.loadData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //run on uiThread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mAdapter == null) {
                        return;
                    }
                    mAdapter.setFirstPage(mTool.isFirstPage());
                    mAdapter.setList(mTool.getArticleBeans());
                    mBinding.recyclerView.scrollToPosition(0);
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false);
        mBinding.setFragment(this);
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        return mBinding.getRoot();
    }

    public ArticleAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void changePrePage() {
        if (mTool.enableLoadPrePage()) {
            mAdapter.clear();
            final DitiezuApp app = DitiezuApp.getInstance();
            app.getExecutor().execute(mRunnableGetData);
        }
    }

    @Override
    public void changeNextPage() {
        if (mTool.enableLoadNextPage()) {
            mAdapter.clear();
            final DitiezuApp app = DitiezuApp.getInstance();
            app.getExecutor().execute(mRunnableGetData);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTool = null;
    }
}
