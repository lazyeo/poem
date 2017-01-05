package cn.studyjamscn.s1.sj124.zhangshun1;

import android.app.Application;

import org.json.JSONArray;

/**
 * Created by lazyeo on 4/26/16.
 */
public class MyApplication extends Application {
    public static MyApplication instance;
    public JSONArray originalData;
    public int categoryIndex;
    public int contentIndex;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
