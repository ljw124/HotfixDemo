package com.hzcominfo.hotfixdemo;

import android.app.Application;
import com.taobao.sophix.SophixManager;

/**
 * Create by Ljw on 2020/7/7 15:26
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //这里是请求服务器的补丁（后期正式应用这里可以做个开关）
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
