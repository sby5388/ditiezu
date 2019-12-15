package com.by5388.ditiezu.publish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author by5388  on 2019/12/15.
 */
public class PublishFragment extends Fragment {
    private static final String PAGE_ID = "pageId";

    public static PublishFragment newInstance(int id) {
        final PublishFragment fragment = new PublishFragment();
        final Bundle args = new Bundle();
        args.putInt(PAGE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
