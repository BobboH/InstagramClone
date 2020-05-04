package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("h6FSf9U7uz427UnUxX2mLKXFtB4pOSCZ0w8ClhtB")
                // if defined
                .clientKey("wGdVxTRLoRMxpdwlmmAyjCEF2oY6L4LizsJU0xol")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
