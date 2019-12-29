package com.by5388.ditiezu.article.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ChooseItem;
import com.by5388.ditiezu.databinding.ActivityScrollingBinding;
import com.by5388.ditiezu.MenuTools;
import com.by5388.ditiezu.publish.PublishActivity;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author Administrator  on 2019/12/17.
 */
// FIXME: 2019/12/19 切换屏幕方向时，标题错误
// FIXME: 2019/12/27 旋转后 swipeRefreshLayout 一直没有停下来
public class ArticleListFragment extends Fragment implements ArticleAdapter.PageChangCallback, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ArticleListFragment.class.getSimpleName();
    private static final String FILTER_TAG = "FilterDialogFragment";
    private static final String BASE_URL = "http://www.ditiezu.com/";
    private static final String INTEGER_INDEX = "index";
    private static final String STRING_TITLE = "title";
    private static final String STRING_TITLE2 = "title2";
    private static final String STRING_ICON_URL = "icon_url";
    private static final int DEFAULT_INDEX = 46;
    private static final int REQUEST_CODE_FILTER = 100;
    public String title2;
    private int mIndex = DEFAULT_INDEX;
    private ArticleAdapter mAdapter;
    private ArticleListTool mTool;
    private ActivityScrollingBinding mBinding;
    private List<ChooseItem> mChooseItems;
    private Handler mHandler = new Handler();
    private int mListIndex = 0;
    private String mIconUrl;
    private FilterDialogFragment mDialogFragment;
    private ArticleListTool.QueryParam.Builder mBuild = new ArticleListTool.QueryParam.Builder();

    public static ArticleListFragment newInstance(final int index, final String title, final String title2, String iconUrl) {
        final ArticleListFragment fragment = new ArticleListFragment();
        final Bundle args = new Bundle();
        args.putInt(INTEGER_INDEX, index);
        args.putString(STRING_TITLE, title);
        args.putString(STRING_TITLE2, title2);
        args.putString(STRING_ICON_URL, iconUrl);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setRetainInstance(true);
        setHasOptionsMenu(true);
        final Bundle arguments = getArguments();
        if (arguments != null) {
            mIndex = arguments.getInt(INTEGER_INDEX, DEFAULT_INDEX);
            mIconUrl = arguments.getString(STRING_ICON_URL);
            title2 = arguments.getString(STRING_TITLE2);
            Log.d(TAG, "onCreate: mIconUrl = " + mIconUrl);
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
            Log.d(TAG, "onResume: " + "activity == null");
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
                mTool.loadData(mBuild.build());
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
                    Log.d(TAG, "run: update");
                    mAdapter.setFirstPage(mTool.isFirstPage());
                    mAdapter.setList(mTool.getArticleBeans());
                    final String describe = mTool.getDescribe();
                    if (!TextUtils.isEmpty(describe)) {
//                        final AppCompatActivity activity = (AppCompatActivity) getActivity();
//                        final ActionBar actionBar = activity.getSupportActionBar();
//                        // TODO: 2019/12/28 设置二级标题
////                        actionBar.setDisplayOptions();
//                        actionBar.setSubtitle(describe);
//                        mBinding.texViewDescribe.setText(describe);
                    }
                    mBinding.recyclerView.scrollToPosition(0);
                    mBinding.swipeRefreshLayout.setEnabled(true);
                    mBinding.swipeRefreshLayout.setRefreshing(false);
                    if (mChooseItems == null) {
                        mChooseItems = mTool.getChooseItems();
                    }

                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.activity_scrolling, container, false);
        final AppCompatActivity activity = Objects.requireNonNull((AppCompatActivity) getActivity());
        activity.setSupportActionBar(mBinding.toolbar);
        mBinding.setFragment(this);
        if (!TextUtils.isEmpty(mIconUrl)) {
            Glide.with(this).load(BASE_URL + mIconUrl).into(mBinding.icon);
        }
        if (!TextUtils.isEmpty(title2)) {
            mBinding.texViewDescribe.setText(title2);
        }
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_article_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                showFilterDialog();
                return true;
            case R.id.menu_search:
                showSearchDialog();
                return true;
            case R.id.menu_index:
                MenuTools.toMain(getActivity());
                return true;
            case R.id.menu_me:
                MenuTools.toMe(getContext());
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchDialog() {
        Toast.makeText(getContext(), "搜索", Toast.LENGTH_SHORT).show();
    }

    private void showFilterDialog() {
        if (mChooseItems == null) {
            return;
        }
        final int size = mChooseItems.size();
        if (size == 0) {
            return;
        }
        if (mDialogFragment == null) {
            mDialogFragment = FilterDialogFragment.newInstance(mChooseItems);
            mDialogFragment.setTargetFragment(this, REQUEST_CODE_FILTER);
        }
        if (mDialogFragment.isVisible()) {
            return;
        }
        mDialogFragment.setIndex(mListIndex);
        mDialogFragment.show(Objects.requireNonNull(getFragmentManager()), FILTER_TAG);
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
            mBuild.setPage(mBuild.getPage() - 1);
            mAdapter.clear();
            final DitiezuApp app = DitiezuApp.getInstance();
            app.execute(mRunnableGetData);
        }
    }

    @Override
    public void changeNextPage() {
        if (mTool.enableLoadNextPage()) {
            mBuild.setPage(mBuild.getPage() + 1);
            mAdapter.clear();
            final DitiezuApp app = DitiezuApp.getInstance();
            app.execute(mRunnableGetData);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mTool = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_FILTER) {
            final int listIndex = FilterDialogFragment.getIndex(data);
            if (listIndex != mListIndex) {
                mListIndex = listIndex;
                final ChooseItem chooseItem = mChooseItems.get(mListIndex);
                // TODO: 2019/12/28 刷新数据
                final String[] params = chooseItem.mUrl.split("&");
                boolean filter = false;
                for (String param : params) {
                    final String[] map = param.split("=");
                    if (map.length == 2) {
                        if ("filter".equals(map[0])) {
                            mBuild.setFilter(map[1]);
                            filter = true;
                        } else if ("typeid".equals(map[0])) {
                            mBuild.setTypeid(map[1]);
                            filter = true;
                        }
                    }
                }
                if (!filter) {
                    mBuild.setFilter("");
                    mBuild.setTypeid("");
                }
                mAdapter.clear();
                final DitiezuApp app = DitiezuApp.getInstance();
                app.execute(mRunnableGetData);

            }
        }
    }
}
