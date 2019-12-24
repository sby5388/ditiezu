package com.by5388.ditiezu.user;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.UserBean;
import com.by5388.ditiezu.databinding.FragmentLoginBinding;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/20.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    private FragmentLoginBinding mBinding;
    private boolean mShowSecureQuestion = false;
    private Handler mHandler = new Handler();
    private Callback mCallback;
    private LoginTools mTools;
    private boolean mFirst = true;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    public interface Callback {
        void onLoginSuccess(UserBean userBean);

        void onLoginFail(String message);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mTools = new LoginTools();

    }

    // TODO: 2019/12/24 生命周期：先 onCreateView 再 onResume
    @Override
    public void onResume() {
        super.onResume();
        if (mFirst) {
            loadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        mBinding.switchShowQuestion.setChecked(mShowSecureQuestion);
        mBinding.switchShowQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mShowSecureQuestion = isChecked;
                mBinding.spinnerSecureQuestion.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                mBinding.editTextSecureAnswer.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    public boolean isShowSecureQuestion() {
        return mShowSecureQuestion;
    }

    private void loadData() {
        final DitiezuApp app = DitiezuApp.getInstance();
        app.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mTools.loadData();
                    final String codeUrl = mTools.getSecureCodeUrl();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(LoginFragment.this)
                                    // TODO: 2019/12/24
                                    .load(codeUrl)
                                    .into(mBinding.imageViewSecure);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
