package com.oscar.nat_at;
import android.app.Application;
import android.content.Context;
import com.oscar.nat_at.Data.Client.GoogleClient;
/**
 * Created by oemy9 on 07/11/2017.
 */
public class ApplicationBase extends Application{
    private static Context context;
    public static ApplicationBase instance;
    private GoogleClient googleClientAPI;


    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }
    public static ApplicationBase getIntance() {
        if(instance==null){
            instance=new ApplicationBase();
        }
        return instance;
    }
    private void initApplication() {
        instance = this;
        context = getApplicationContext();
        googleClientAPI=new GoogleClient();
    }

    public GoogleClient getGoogleClientAPI() {
        return googleClientAPI;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ApplicationBase.context = context;
    }
}
