package com.by5388.ditiezu.publish;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.SingleFragmentActivity;

/**
 * @author by5388  on 2019/12/15.
 */
public class PublishActivity extends SingleFragmentActivity {
    public static final int TYPE_PUBLISH = 0;
    public static final int TYPE_VOTE = 1;
    private static final String TYPE_ACTION = "type_action";
    private static final String PAGE_ID = "pageId";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    public Fragment createFragment() {
        final int beijingIndex = 7;
        final int actionType = getIntent().getIntExtra(TYPE_ACTION, TYPE_PUBLISH);
        final int pageId = getIntent().getIntExtra(PAGE_ID, beijingIndex);
        if (actionType == TYPE_PUBLISH) {
            return PublishFragment.newInstance(pageId);
        } else if (actionType == TYPE_VOTE) {
            return VoteFragment.newInstance();
        }
        return PublishFragment.newInstance(pageId);
    }

    public static Intent newIntentVote(Context context, int id) {
        return newIntent(context, TYPE_VOTE, id);
    }

    public static Intent newIntentPublish(Context context, int id) {
        return newIntent(context, TYPE_PUBLISH, id);
    }

    private static Intent newIntent(Context context, int type, int id) {
        final Intent intent = new Intent(context, PublishActivity.class);
        intent.putExtra(TYPE_ACTION, type);
        intent.putExtra(PAGE_ID, id);
        return intent;
    }


}
