package com.by5388.ditiezu.user;

import android.content.Context;
import android.content.Intent;

import com.by5388.ditiezu.BaseSingleFragmentActivity;
import com.by5388.ditiezu.bean.UserBean;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/20.
 */
public final class LoginActivity extends BaseSingleFragmentActivity implements LoginFragment.Callback {
    private boolean mTest = false;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public Fragment createFragment() {
        if (mTest) {
            return TempLoginFragment.newInstance();
        }
        return LoginFragment.newInstance();
    }

    @Override
    public void onLoginSuccess(UserBean userBean) {
        // TODO: 2019/12/23  
    }

    @Override
    public void onLoginFail(String message) {
        // TODO: 2019/12/23

    }
}
