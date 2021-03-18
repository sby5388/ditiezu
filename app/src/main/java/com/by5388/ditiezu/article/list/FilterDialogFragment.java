package com.by5388.ditiezu.article.list;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.by5388.ditiezu.bean.ChooseItem;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2019/12/21.
 */
public class FilterDialogFragment extends DialogFragment {
    private static final String TAG = FilterDialogFragment.class.getSimpleName();
    private static final String CHOOSE_LIST_ARRAY = "choose_list_array";
    private static final String LIST_INDEX = "list_index";
    private int mIndex = 0;
    private String[] mArray;


    public static FilterDialogFragment newInstance(List<ChooseItem> chooseItems) {
        final FilterDialogFragment fragment = new FilterDialogFragment();
        final Bundle args = new Bundle();
        final String[] array = new String[chooseItems.size()];
        for (int i = 0; i < chooseItems.size(); i++) {
            array[i] = chooseItems.get(i).mName;
        }
        args.putCharSequenceArray(CHOOSE_LIST_ARRAY, array);
        fragment.setArguments(args);
        return fragment;
    }

    public static int getIndex(@Nullable Intent intent) {
        final int defaultIndex = 0;
        if (intent == null) {
            return defaultIndex;
        }
        return intent.getIntExtra(LIST_INDEX, defaultIndex);
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mArray = bundle.getStringArray(CHOOSE_LIST_ARRAY);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Context context = Objects.requireNonNull(getContext());
        if (mArray == null) {
            return new AlertDialog.Builder(context).create();
        }

        return new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setTitle("分类")
                .setSingleChoiceItems(mArray, mIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: which = " + which);
                        mIndex = which;
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final Fragment fragment = getTargetFragment();
                        final int requestCode = getTargetRequestCode();
                        if (fragment == null) {
                            Log.e(TAG, "onClick: ", new Exception("fragment == null"));
                            return;
                        }
                        Intent intent = new Intent();
                        intent.putExtra(LIST_INDEX, mIndex);
                        fragment.onActivityResult(requestCode, Activity.RESULT_OK, intent);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setCancelable(true)
                .create();
    }
}
