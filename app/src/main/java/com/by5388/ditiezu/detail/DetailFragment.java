package com.by5388.ditiezu.detail;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Toast;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ChooseItem;
import com.by5388.ditiezu.bean.PageData;
import com.by5388.ditiezu.databinding.FragmentPageDetailBinding;
import com.by5388.ditiezu.publish.PublishActivity;
import com.by5388.ditiezu.temp.GetListByUri;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author by5388  on 2019/12/15.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private static final String DATA_KEY = "main_data";
    private static final String STRING_TITLE = "title";
    private static final String INTEGER_ID = "id";

    private PageData mPageData;
    private WebView mWebView;
    private MenuItem mMenuItemClassify;
    private Handler mHandler = new Handler();
    private List<ChooseItem> mChooseItems = new ArrayList<>();

    public static DetailFragment newInstance(PageData data) {
        if (data == null) {
            Log.e(TAG, "newInstance: ", new Exception());
            return new DetailFragment();
        }
        final DetailFragment fragment = new DetailFragment();
        final Bundle args = new Bundle();
        args.putString(STRING_TITLE, data.mName);
        args.putInt(INTEGER_ID, data.mIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        final Bundle arguments = getArguments();
        if (arguments == null) {
            Log.e(TAG, "onCreate: ", new Exception());
            return;
        }
        mPageData = new PageData(arguments.getInt(INTEGER_ID), arguments.getString(STRING_TITLE));
        Objects.requireNonNull(getActivity()).setTitle(mPageData.mName);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final GetListByUri getListByUri = new GetListByUri(mPageData.mIndex);
                getListByUri.getData();
                final List<ChooseItem> chooseItems = getListByUri.getChooseItems();
                // TODO: 2019/12/16 可能造成内存泄露
                updateItem(chooseItems);

            }
        }).start();

        final Executor executor = DitiezuApp.getInstance().getExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_detail, menu);
        mMenuItemClassify = menu.findItem(R.id.menu_filter);
        mMenuItemClassify.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menu_search:
                startActivity(ArticleListActivity.newIntent(getContext(), mPageData));
                return true;
            case R.id.menu_publish:
                final Context context = Objects.requireNonNull(getContext());
                context.startActivity(PublishActivity.newIntent(context, mPageData.mIndex));
                return true;
            case R.id.menu_filter:
                showDialogFilter();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentPageDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_detail, container, false);
//        binding.setFragment(this);
        mWebView = binding.webView;
        mWebView.loadUrl(mPageData.getUrl());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                final String message = request.getUrl().toString();
                //TODO 拦截谷歌广告、百度广告
                // FIXME: 2019/12/18 页面底部的广告未拦截
                if (message.contains("google") || message.contains("baidu")) {
                    return new WebResourceResponse(null, null, null);
                }
//                Log.e(TAG, "shouldInterceptRequest: url = " + message);
                return super.shouldInterceptRequest(view, request);
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                final FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.setTitle(title);
                }
            }
        });
        return binding.getRoot();
    }


    public boolean isKeyDown(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView == null) {
                return false;
            }
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }

        return false;
    }

    private void updateItem(final List<ChooseItem> chooseItems) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mMenuItemClassify == null) {
                    return;
                }
                Log.d(TAG, "run: count = " + chooseItems.size());
                mMenuItemClassify.setEnabled(true);
                mChooseItems = chooseItems;
            }
        });

    }

    private void showDialogFilter() {
        final int size = mChooseItems.size();
        if (size == 0) {
            return;
        }
        final CharSequence[] items = new CharSequence[size];
        for (int i = 0; i < size; i++) {
            items[i] = mChooseItems.get(i).mName;
        }
        // TODO: 2019/12/21 使用FragmentDialog 来实现，并显示过滤后的内容
        new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final CharSequence item = items[position];
                        Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onItemSelected: item = " + item);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                })
                .setCancelable(true)
                .create()
                .show();

    }


}
