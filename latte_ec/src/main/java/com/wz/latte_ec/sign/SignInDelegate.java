package com.wz.latte_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_core.util.FileUtil;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WangZhen on 2019/4/18.
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {

    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
//            RestClient.builder()
//                    .url("http://192.168.64.2/RestServer/data/user_profile.json")
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String success) {
//                            LatteLogger.json("USER_PROFILE", success);
//                            SignHandler.onSignIn(success, mISignListener);
//                        }
//                    })
//                    .build()
//                    .post();
            String response = FileUtil.getRawFile(R.raw.user_profile);
            //直接注册成功
            SignHandler.onSignIn(response, mISignListener);
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignUpDelegate());
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }


        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }
}
