package com.wz.latte_ec.sign;

import com.alibaba.fastjson.JSONObject;
import com.wz.latte_core.app.AccountManager;
import com.wz.latte_ec.database.UserProfile;

/**
 * Created by WangZhen on 2019/4/18.
 */
public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSONObject.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);

//        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSONObject.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);

//        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
