package com.by5388.ditiezu.detail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.by5388.ditiezu.R;
import com.by5388.ditiezu.bean.ChooseItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * @author Administrator  on 2019/12/21.
 */
public class FilterDialogFragment extends DialogFragment {
    private static final String TAG = FilterDialogFragment.class.getSimpleName();
    private static final String KEY = "key";


    public static FilterDialogFragment newInstance(List<ChooseItem> chooseItems) {
        final FilterDialogFragment fragment = new FilterDialogFragment();
        final Bundle args = new Bundle();
        ArrayList<CharSequence> arrayList = new ArrayList<>(chooseItems.size());
        for (ChooseItem item : chooseItems) {
            arrayList.add(item.mName);
        }
        args.putCharSequenceArrayList(KEY, arrayList);
        // FIXME: 2019/12/22
        throw new RuntimeException();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Bundle bundle = getArguments();
        final Context context = Objects.requireNonNull(getContext());
        final View view = getLayoutInflater().inflate(R.layout.dialog_filter, null);
        final ListView listView = view.findViewById(R.id.listView_filter);
//        listView.setAdapter(new ArrayAdapter<Context>());
        return new AlertDialog.Builder(context)
                .create();
    }
}
