package com.by5388.ditiezu.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentArticleListBinding;
import com.by5388.ditiezu.publish.PublishActivity;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author Administrator  on 2019/12/17.
 */
// FIXME: 2019/12/19 切换屏幕方向时，标题错误
public class ArticleListFragment extends Fragment implements ArticleAdapter.PageChangCallback, SwipeRefreshLayout.OnRefreshListener {
    private static final String INTEGER_INDEX = "index";
    private static final String STRING_TITLE = "title";
    private static final int DEFAULT_INDEX = 46;
    private int mIndex = DEFAULT_INDEX;
    private ArticleAdapter mAdapter;
    private ArticleListTool mTool;
    private FragmentArticleListBinding mBinding;
    private Handler mHandler = new Handler();

    public static ArticleListFragment newInstance(final int index, final String title) {
        final ArticleListFragment fragment = new ArticleListFragment();
        final Bundle args = new Bundle();
        args.putInt(INTEGER_INDEX, index);
        args.putString(STRING_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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

    @Override
    public void onResume() {
        super.onResume();
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        final Bundle arguments = getArguments();
        if (arguments != null) {
            final String title = arguments.getString(STRING_TITLE);
            if (TextUtils.isEmpty(title)) {
                return;
            }
            activity.setTitle(title);
        }
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
                    mBinding.swipeRefreshLayout.setEnabled(true);
                    mBinding.swipeRefreshLayout.setRefreshing(false);

                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false);
        mBinding.setFragment(this);
        mBinding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = view.getContext();
                final Intent intent = PublishActivity.newIntent(context, mIndex);
                context.startActivity(intent);
            }
        });
        // TODO: 2019/12/19 设置渐变颜色
        final Context context = Objects.requireNonNull(getContext());
        mBinding.swipeRefreshLayout.setColorSchemeColors(getColor(R.color.colorPrimary_0), getColor(R.color.colorPrimaryDark));
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        mBinding.swipeRefreshLayout.setEnabled(false);
        mBinding.swipeRefreshLayout.setRefreshing(true);
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(context), DividerItemDecoration.VERTICAL));
        return mBinding.getRoot();
    }

    @Override
    public void onRefresh() {
        mBinding.swipeRefreshLayout.setEnabled(false);
        mBinding.swipeRefreshLayout.setRefreshing(true);
        mAdapter.clear();
        final DitiezuApp app = DitiezuApp.getInstance();
        app.getExecutor().execute(mRunnableGetData);
    }

    private int getColor(int colorId) {
        final Context context = Objects.requireNonNull(getContext());
        return getResources().getColor(colorId, context.getTheme());
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
