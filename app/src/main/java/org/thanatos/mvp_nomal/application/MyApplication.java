package org.thanatos.mvp_nomal.application;

import android.app.Application;

import org.thanatos.mvp_nomal.utils.CustomVolly;

/**
 * Created by lxf52 on 2016/12/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CustomVolly.registerVolly(getApplicationContext());
    }

}
