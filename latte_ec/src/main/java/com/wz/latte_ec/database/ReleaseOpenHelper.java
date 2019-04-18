package com.wz.latte_ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WangZhen on 2019/4/18.
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
