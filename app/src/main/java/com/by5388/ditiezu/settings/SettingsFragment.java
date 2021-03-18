package com.by5388.ditiezu.settings;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.service.NoticeService;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

/**
 * @author Administrator  on 2019/12/30.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    public static final String TAG = SettingsFragment.class.getSimpleName();
    private static final String KEY_SWITCH_ENABLE_PUBLISH_SERVICE = "switch_enable_publish_service";

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Log.d(TAG, "onCreatePreferences: rootKey = " + rootKey);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        addPreferencesFromResource(R.xml.settings);
        final SwitchPreference preference = findPreference(KEY_SWITCH_ENABLE_PUBLISH_SERVICE);
        if (preference != null) {
            preference.setOnPreferenceChangeListener(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        if (KEY_SWITCH_ENABLE_PUBLISH_SERVICE.equals(key)) {
            boolean enable = (Boolean) newValue;
            final Context context = Objects.requireNonNull(getContext());
            Toast.makeText(context, String.valueOf(enable), Toast.LENGTH_SHORT).show();
            NoticeService.setPublishEnable(context, enable);
            return true;
        }
        return false;
    }
}
