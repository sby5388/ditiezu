package com.by5388.ditiezu.data;

import android.app.Application;
import android.os.Handler;
import android.widget.Toast;

import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.main.ModuleBean;
import com.by5388.ditiezu.start.StartTools;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author Administrator  on 2020/5/13.
 */
public class DataViewModel extends AndroidViewModel {
    private MutableLiveData<List<ModuleBean>> mMutableLiveData;

    public DataViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<ModuleBean>> getMutableLiveData() {
        if (mMutableLiveData == null) {
            mMutableLiveData = new MutableLiveData<>();
            loadData();
        }
        return mMutableLiveData;
    }

    public void update() {
        loadData();
    }

    private void loadData() {
        final StartTools tools = StartTools.getInstance();
        final DitiezuApp ditiezuApp = DitiezuApp.getInstance();
        final Handler handler = ditiezuApp.getHandler();
        final Handler workerHandler = ditiezuApp.getWorkerHandler();
        workerHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    tools.loadData();
                    mMutableLiveData.postValue(tools.getModuleBeans());
                } catch (IOException e) {
                    handler.post(mRunnableUiError);
                    e.printStackTrace();
                }
            }
        });

    }

    private Runnable mRunnableUi = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(DitiezuApp.getInstance(), "网络异常", Toast.LENGTH_SHORT).show();
        }
    };
    private Runnable mRunnableUiError = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(DitiezuApp.getInstance(), "没有获取到正确的数据", Toast.LENGTH_SHORT).show();
        }
    };
}
