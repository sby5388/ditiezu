package com.by5388.ditiezu.bean;

import java.util.Objects;

/**
 * @author Administrator  on 2019/12/20.
 */
public class UserBean {
    public static final UserBean mUnLogin = new UserBean(-1, "pass");
    private int mUserId;
    private String sName;

    public UserBean(int userId, String sName) {
        mUserId = userId;
        this.sName = sName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBean)) return false;
        UserBean userBean = (UserBean) o;
        return mUserId == userBean.mUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUserId);
    }
}
