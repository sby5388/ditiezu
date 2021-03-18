package com.by5388.ditiezu.start;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.by5388.ditiezu.data.DataViewModel;
import com.by5388.ditiezu.main.MainActivity;
import com.by5388.ditiezu.main.ModuleBean;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author Administrator  on 2020/5/13.
 */
public class StartFragment extends Fragment {
    private DataViewModel mDataViewModel;
    private Handler mHandler;

    public static Fragment newInstance() {
        return new StartFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = DitiezuApp.getInstance().getHandler();
        mDataViewModel = ViewModelProviders.of(requireActivity()).get(DataViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataViewModel.getMutableLiveData().observe(this, new Observer<List<ModuleBean>>() {
            @Override
            public void onChanged(List<ModuleBean> moduleBeans) {
                if (moduleBeans == null || moduleBeans.isEmpty()) {
                    mDataViewModel.update();
                } else {
                    DitiezuApp.getInstance().setModuleBeans(moduleBeans);
                    mRunnableToMain.run();
                }
            }
        });
    }


    private Runnable mRunnableToMain = new Runnable() {
        @Override
        public void run() {
            final Activity activity = requireActivity();
            activity.startActivity(MainActivity.newIntent(activity));
            activity.finish();
        }
    };

}
