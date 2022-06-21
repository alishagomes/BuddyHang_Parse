package com.example.buddyhang;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /** Register your parse models */
        //ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("8sYdmdAkW0VcMfpXfN5Dcor5k5oQS1pR0H0g5HqN")
                .clientKey("LYQv7LNLHGYrMoXHsuenetWM0MSGRE4RgVMk9LHI")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
