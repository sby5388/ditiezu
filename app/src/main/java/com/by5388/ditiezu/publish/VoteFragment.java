package com.by5388.ditiezu.publish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author by5388  on 2019/12/15.
 */
public class VoteFragment extends Fragment {

    public static VoteFragment newInstance() {
        return new VoteFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final TextView textView = new TextView(getContext());
        textView.setText("Vote");
        return textView;
    }
}
