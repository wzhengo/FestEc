package com.wz.latte_core.delegate;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.wz.latte_core.ui.camera.CameraImageBean;
import com.wz.latte_core.ui.camera.LatteCamera;
import com.wz.latte_core.ui.camera.RequestCodes;
import com.wz.latte_core.util.callback.CallbackManager;
import com.wz.latte_core.util.callback.CallbackType;
import com.wz.latte_core.util.callback.IGlobalCallback;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by WangZhen on 2019/4/11.
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    //不是直接调用方法，仅仅是生成代码所用，所以不能是private或static
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startCamera() {
        LatteCamera.start(this);
    }

    //这个是真正调用的方法
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Toast.makeText(getContext(), "永久拒绝拍照权限", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onCameraRational(PermissionRequest request) {
        showRationalDialog(request);
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onExternalDenied() {
        Toast.makeText(getContext(), "无存储权限", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onExternalNever() {
        Toast.makeText(getContext(), "永久拒绝存储权限", Toast.LENGTH_SHORT).show();
    }

    private void showRationalDialog(PermissionRequest request) {
        if (getContext() != null) {
            new AlertDialog.Builder(getContext())
                    .setPositiveButton("同意使用", (dialog, which) -> {
                        request.proceed();
                    })
                    .setNegativeButton("拒绝使用", ((dialog, which) -> {
                        request.cancel();
                    }))
                    .setCancelable(false)
                    .setMessage("权限管理")
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)//相同的Uri，剪裁过的覆盖原图
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要个路径存放剪裁过的图片
                        final String pickCropResult = LatteCamera.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropResult))//相同的Uri，剪裁过的覆盖原图
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCodes.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    @SuppressWarnings("unchecked") final IGlobalCallback callback =
                            CallbackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
