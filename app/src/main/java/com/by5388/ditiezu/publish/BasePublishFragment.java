package com.by5388.ditiezu.publish;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.by5388.ditiezu.BuildConfig;
import com.by5388.ditiezu.DitiezuApp;
import com.by5388.ditiezu.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

/**
 * @author Administrator  on 2020/1/9.
 */
public abstract class BasePublishFragment extends Fragment {
    private static final String TAG = "BasePublishFragment";
    private static final String DITIEZU_INTENT_FILE_PROVIDER = BuildConfig.APPLICATION_ID + ".fileprovider";

    private static final int REQUEST_OPEN_CAMERA = 200;
    private static final int REQUEST_BROWSER_FILE = 201;

    void onUploadPicture() {
        final Context context = Objects.requireNonNull(getContext());
        final BottomSheetDialog dialog = new BottomSheetDialog(context);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.dialog_botton_upload_picture, (ViewGroup) getView(), false);
        view.findViewById(R.id.button_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.button_browser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserFile();
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.setTitle("选择方式");
        dialog.show();
    }

    protected void openCamera() {
//        Toast.makeText(getContext(), "openCamera", Toast.LENGTH_SHORT).show();
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final ComponentName name = captureImage.resolveActivity(getContext().getPackageManager());
        if (name == null) {
            Toast.makeText(getContext(), "设备不支持打开相机", Toast.LENGTH_SHORT).show();
            return;
        }
        final File myFile = getMyFile(getFileName());
        final Uri uri = FileProvider.getUriForFile(DitiezuApp.getInstance(), DITIEZU_INTENT_FILE_PROVIDER, myFile);

        captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //查询匹配的组件信息
        final List<ResolveInfo> resolveInfos = getContext().getPackageManager()
                .queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);
        //为组件信息授予临时权限来读写指定的位置
        for (ResolveInfo resolveInfo : resolveInfos) {
            getContext().grantUriPermission(resolveInfo.activityInfo.packageName, uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        startActivityForResult(captureImage, REQUEST_OPEN_CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (REQUEST_OPEN_CAMERA == requestCode) {
            final File myFile = getMyFile(getFileName());
            final Uri uri = FileProvider.getUriForFile(DitiezuApp.getInstance(), DITIEZU_INTENT_FILE_PROVIDER, myFile);
            //TODO 撤销权限
            getContext().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            final long length = myFile.length();
            Log.d(TAG, "onActivityResult: length = " + length);
        }

    }

    private File getMyFile(String fileName) {
        final File filesDir = getContext().getFilesDir();
        return new File(filesDir, fileName);
    }


    private String getFileName() {
        // TODO: 2020/1/9 创建随机的文件名称
        return "123.jpg";
    }

    protected void browserFile() {
        Toast.makeText(getContext(), "browserFile", Toast.LENGTH_SHORT).show();
    }


    @NonNull
    public Context getContext() {
        return Objects.requireNonNull(super.getContext());
    }
}
