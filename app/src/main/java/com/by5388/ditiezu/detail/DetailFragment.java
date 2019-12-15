package com.by5388.ditiezu.detail;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.databinding.FragmentPageDetailBinding;
import com.by5388.ditiezu.main.PageData;
import com.by5388.ditiezu.publish.PublishActivity;

import java.util.Objects;

/**
 * @author by5388  on 2019/12/15.
 */
public class DetailFragment extends Fragment {
    public static final String TAG = "DetailFragment";
    private static final String DATA_KEY = "main_data";
    private static final String STRING_TITLE = "title";
    private static final String INTEGER_ID = "id";

    private PageData mPageData;
    private WebView mWebView;

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

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menu_search:
                break;
            case R.id.menu_publish:
                final Context context = Objects.requireNonNull(getContext());
                context.startActivity(PublishActivity.newIntentPublish(context, mPageData.mIndex));
                return true;
            case R.id.menu_classify:
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
        binding.setFragment(this);
        mWebView = binding.webView;
        mWebView.loadUrl(mPageData.getUrl());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
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


}
