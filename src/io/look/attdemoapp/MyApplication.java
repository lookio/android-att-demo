package io.look.attdemoapp;

import android.os.Build;
import com.liveperson.mobile.android.service.ApplicationLifecycleHandler;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

	@Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyApplicaton onCreate", "MyApplicaton onCreate");
        
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // Register to the ActivityLifecycleCallbacks in order to support notifications
            registerActivityLifecycleCallbacks(new ApplicationLifecycleHandler());
        }
    }
}
