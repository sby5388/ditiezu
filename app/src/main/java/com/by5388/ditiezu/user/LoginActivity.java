package com.by5388.ditiezu.user;

import com.by5388.ditiezu.SingleFragmentActivity;
import com.by5388.ditiezu.bean.UserBean;

import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/20.
 */
public final class LoginActivity extends SingleFragmentActivity implements LoginFragment.Callback {

    @Override
    public Fragment createFragment() {
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
