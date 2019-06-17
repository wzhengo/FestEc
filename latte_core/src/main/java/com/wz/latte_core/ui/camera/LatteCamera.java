package com.wz.latte_core.ui.camera;

import android.net.Uri;

import com.wz.latte_core.delegate.PermissionCheckerDelegate;
import com.wz.latte_core.util.FileUtil;

/**
 * 照相机调用类
 *
 * @author wangzhen
 * @date 2019/06/15
 */
public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }


}
