package com.by5388.ditiezu.detail;

import android.content.Context;
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

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.MenuTools;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentArticleDetailBinding;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author Administrator  on 2019/12/18.
 */
public class ArticleDetailFragment extends Fragment {
    private static final String TAG = "ArticleDetailFragment";
    private static final String ARTICLE_URL = "article_url";
    private static final String BASE_URL = "http://www.ditiezu.com/";
    private String mUrl;
    private FragmentArticleDetailBinding mBinding;
    private CommentAdapter mAdapter;
    private DetailTool mTool;
    private Handler mHandler = new Handler();


    public static ArticleDetailFragment newInstance(String url) {
        final ArticleDetailFragment fragment = new ArticleDetailFragment();
        final Bundle args = new Bundle();
        if (!TextUtils.isEmpty(url)) {
            args.putString(ARTICLE_URL, url);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        final Bundle arguments = getArguments();
        if (arguments == null) {
            finish();
            return;
        }
        mUrl = arguments.getString(ARTICLE_URL);
        if (TextUtils.isEmpty(mUrl)) {
            finish();
            return;
        }
        Log.d(TAG, "onCreate: mUrl = " + mUrl);
        mAdapter = new CommentAdapter();
        mTool = new DetailTool(mUrl);
    }

    private void finish() {
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_commom, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final Context context = Objects.requireNonNull(getContext());
        switch (item.getItemId()) {
            case R.id.menu_index: {
                MenuTools.toMain(getActivity());
                return true;
            }
            case R.id.menu_me: {
                MenuTools.toMe(context);
                return true;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setFragment(this);
        mBinding.recyclerView.setAdapter(mAdapter);
        loadData();
    }

    private void loadData() {
        DitiezuApp.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mTool.loadData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final ArticleDetailBean detailBean = mTool.getDetailBean();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (detailBean == null) {
                            Toast.makeText(getContext(), "数据错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAdapter.setCommentBeans(detailBean);
                        mBinding.buttonMainReply.setText(detailBean.getReply());
                        mBinding.textViewOrigin.setText(detailBean.getMainContent());
                        mBinding.buttonMainReply.setText(detailBean.getReply());
                        mBinding.textViewUserDate.setText(detailBean.getDate());
                        mBinding.buttonSort.setText(detailBean.getSortName());
                        mBinding.buttonUserName.setText(detailBean.getAuthorName());
                    }
                });
            }
        });
    }


    private String getUrl() {
        boolean isTest = false;
        if (isTest) {
            return "http://www.ditiezu.com/member.php?mod=logging&action=login&mobile=yes";
        }
        return BASE_URL + mUrl;
    }


}
