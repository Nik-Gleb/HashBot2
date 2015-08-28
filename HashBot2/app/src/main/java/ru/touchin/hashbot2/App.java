package ru.touchin.hashbot2;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;

import org.zuzuk.tasks.aggregationtask.TaskExecutorHelper;
import org.zuzuk.tasks.remote.base.TaskExecutorHelperCreator;
import org.zuzuk.utils.Lc;

public class App extends Application implements TaskExecutorHelperCreator {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        if (BuildConfig.DEBUG) {
            Lc.initialize(Log.DEBUG);
            Lc.setRobospiceLogLevel(Log.ERROR);
        } else {
            Lc.initialize(Log.ERROR);
        }

        final OkHttpClient httpClient = new OkHttpClient();
        OkHttpHelper.setTimeouts(httpClient);
        final ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(getApplicationContext(), httpClient).build();
        Fresco.initialize(getApplicationContext(), config);
    }

    @Override
    public TaskExecutorHelper createTaskExecutorHelper() {
        return new TaskExecutorHelper() {};
    }

    public static App getInstance() {
        return instance;
    }
}
