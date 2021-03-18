package com.by5388.ditiezu.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author Administrator  on 2020/1/2.
 */
public final class TempLoginFragment extends Fragment {
    public static final String TAG = "TempLoginFragment";
    private WebView mWebView;

    public static TempLoginFragment newInstance() {
        return new TempLoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        DitiezuApp.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                final LoginTools tools = new LoginTools();
                try {
                    tools.loadData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        final View view = inflater.inflate(R.layout.fragment_temp_login, container, false);
        mWebView = view.findViewById(R.id.webView_login);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(getUrl());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                final CookieManager cookieManager = CookieManager.getInstance();
                final String cookie = cookieManager.getCookie(url);
                Log.d(TAG, "onPageFinished: cookie = " + cookie);

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


    }

    private String getUrl() {
        if (false) {
            return "http://www.baidu.com/";
        }
        return "http://www.ditiezu.com/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&mobile=yes";
    }
}
//http://www.ditiezu.com/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&mobile=yes"
//http://www.ditiezu.com/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&mobile=yes
//http://www.ditiezu.com/member.php?mod=logging&action=login&mobile=yes