package com.wz.latte_ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.net.RestClient;
import com.wz.latte_core.util.LatteLogger;
import com.wz.latte_core.util.callback.CallbackManager;
import com.wz.latte_core.util.callback.CallbackType;
import com.wz.latte_core.util.callback.IGlobalCallback;
import com.wz.latte_ec.R;
import com.wz.latte_ec.main.personal.list.ListBean;
import com.wz.latte_ui.date.DateDialogUtil;

/**
 * @author wangzhen
 * @date 2019/06/12
 */
public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开始照相机或选择图片
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP,
                        (IGlobalCallback<Uri>) args -> {
                            LatteLogger.d("ON_CROP", args);
                            final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                            Glide.with(DELEGATE)
                                    .load(args)
                                    .into(avatar);

                            RestClient.builder()
                                    .url(UploadConfig.UPLOAD_IMG)
                                    .loader(DELEGATE.getContext())
                                    .file(args.getPath())
                                    .success(response -> {
                                        LatteLogger.d("ON_CROP_UPLOAD", response);
                                        final String path = JSON.parseObject(response).getJSONObject("result")
                                                .getString("path");
                                        RestClient.builder()
                                                .url("user_profile.php")
                                                .params("avatar", path)
                                                .success(response1 -> {
                                                    //获取更新后的用户信息，然后更新本地数据库
                                                    //没有本地数据的APP，每次打开APP都请求API，获取信息
                                                })
                                                .build()
                                                .post();
                                    })
                                    .build()
                                    .upload();
                        });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate delegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(delegate);
                break;
            case 3:
                getGenderDialog((dialog, which) -> {
                    final TextView textView = view.findViewById(R.id.tv_arrow_value);
                    textView.setText(mGenders[which]);
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(date -> {
                    final TextView textView = view.findViewById(R.id.tv_arrow_value);
                    textView.setText(date);
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }

    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
