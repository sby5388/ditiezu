package com.by5388.ditiezu;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author Administrator  on 2019/12/16.
 */
public class ChooseListFragmentDialog<T extends ChooseListFragmentDialog.MyData> extends DialogFragment {




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public interface MyData {

    }


}
