package com.by5388.ditiezu.publish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.by5388.ditiezu.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author by5388  on 2019/12/15.
 */
public class PublishActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    public static final String TAG = "PublishActivity";
    private static final int TYPE_PUBLISH = 0;
    private static final int TYPE_VOTE = 1;
    private static final String TYPE_ACTION = "type_action";
    private static final String PAGE_ID = "pageId";
    private RadioButton mRadioButtonPublish;
    private RadioButton mRadioButtonVote;
    private RadioGroup mRadioGroup;
    private PublishFragment mPublishFragment;
    private VoteFragment mVoteFragment;
    private static final String FRAGMENT_TAG_PUBLISH = "PublishFragment";
    private static final String FRAGMENT_TAG_VOTE = "VoteFragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        mRadioGroup = findViewById(R.id.button_group);
        mRadioButtonVote = findViewById(R.id.button_vote);
        mRadioButtonPublish = findViewById(R.id.button_publish);
        mRadioGroup.setOnCheckedChangeListener(this);

        changeFragment(FRAGMENT_TAG_PUBLISH);
    }

    private void changeFragment(final String tag) {
        Log.d(TAG, "changeFragment: tag = " + tag);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = createFragmentByTag(tag);
        }
        if (fragment == null) {
            Log.e(TAG, "changeFragment: error fragment");
            finish();
            return;
        }
        fragmentManager.beginTransaction().replace(R.id.container, fragment, tag).commit();
    }

    private Fragment createFragmentByTag(String tag) {
        final int beijingIndex = 7;
        final int pageId = getIntent().getIntExtra(PAGE_ID, beijingIndex);
        if (FRAGMENT_TAG_PUBLISH.equals(tag)) {
            return PublishFragment.newInstance(pageId);
        } else if (FRAGMENT_TAG_VOTE.equals(tag)) {
            return VoteFragment.newInstance();
        }
        return PublishFragment.newInstance(pageId);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.button_publish) {
            changeFragment(FRAGMENT_TAG_PUBLISH);
        } else if (checkedId == R.id.button_vote) {
            changeFragment(FRAGMENT_TAG_VOTE);
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PublishFragment) {
            mPublishFragment = (PublishFragment) fragment;
        } else if (fragment instanceof VoteFragment) {
            mVoteFragment = (VoteFragment) fragment;
        }
    }


    public static Intent newIntent(Context context, int id) {
        final Intent intent = new Intent(context, PublishActivity.class);
        intent.putExtra(PAGE_ID, id);
        return intent;
    }


}
