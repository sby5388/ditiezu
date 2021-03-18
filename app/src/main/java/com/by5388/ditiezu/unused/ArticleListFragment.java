package com.by5388.ditiezu.unused;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.by5388.ditiezu.MenuTools;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentPageDetailBinding;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author Administrator  on 2019/12/18.
 */
@Deprecated
public class ArticleListFragment extends Fragment {
    private static final String TAG = "ArticleDetailFragment";
    private static final String ARTICLE_URL = "article_url";
    private static final String BASE_URL = "http://www.ditiezu.com/";
    private String mUrl;
    private WebView mWebView;

    public static ArticleListFragment newInstance(String url) {
        final ArticleListFragment fragment = new ArticleListFragment();
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
        final FragmentPageDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_detail, container, false);
        mWebView = binding.webView;
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(getUrl());
        mWebView.setWebViewClient(
                new WebViewClient() {
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
                        // Log.e(TAG, "shouldInterceptRequest: url = " + message);
                        return super.shouldInterceptRequest(view, request);
                    }
                }
        );
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

    private String getUrl() {
        boolean isTest = false;
        if (isTest) {
            return "http://www.ditiezu.com/member.php?mod=logging&action=login&mobile=yes";
        }
        return BASE_URL + mUrl;
    }


}
