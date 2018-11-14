package com.soyummyrecips.anna.soyummyrecipes.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.soyummyrecips.anna.soyummyrecipes.Utils.Constants;

public class AppSoyummyrecips extends Application {

    static String APPLICATION_ID = "1:752597879379:android:1b6c89c4e2632065";
    static String YOUR_API_KEY = "AIzaSyC_6a1dApr4oRyw9inR1Jz45ohPhQ92DwU";
    static String YOUR_DATABASE_URL = "1:752597879379:android:1b6c89c4e2632065";
    @Override
    public void onCreate() {
        super.onCreate();
        /*FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId(APPLICATION_ID) // Required for Analytics.
                .setApiKey(YOUR_API_KEY) // Required for Auth.
               // .setDatabaseUrl(YOUR_DATABASE_URL) // Required for RTDB.
                // ...
                .build();
        FirebaseApp.initializeApp(this);
        boolean isMain = isMainProcess(this);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(isMain).build();
        FirebaseFirestore.getInstance().setFirestoreSettings(settings);
        if (!isMain) {
            // other things
            return;
        }*/
        // other things
    }

    private boolean isMainProcess(Context context) {
        if (null == context) {
            return true;
        }
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            String name = processInfo.processName;
            if ( pid == processInfo.pid && name.equals(APPLICATION_ID)) {
                return true;
            }
        }
        return false;
    }
}
