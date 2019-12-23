package com.by5388.ditiezu.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.UserBean;
import com.by5388.ditiezu.databinding.FragmentLoginBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/20.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding mBinding;
    private boolean mShowSecureQueestion = false;
    private Callback mCallback;

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

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        mBinding.switchShowQuestion.setChecked(mShowSecureQueestion);
        mBinding.switchShowQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mShowSecureQueestion = isChecked;
                mBinding.spinnerSecureQuestion.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                mBinding.editTextSecureAnswer.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    public boolean isShowSecureQueestion() {
        return mShowSecureQueestion;
    }
}
