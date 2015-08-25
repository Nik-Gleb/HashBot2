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

/**
 * Class title.
 * Class description.
 * Created by Gleb on 20.08.2015.
 */
public class App extends Application implements TaskExecutorHelperCreator {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Lc.initialize(Log.DEBUG);
            Lc.setRobospiceLogLevel(Log.ERROR);
        } else {
            Lc.initialize(Log.ERROR);
        }

        OkHttpClient httpClient = new OkHttpClient();
        OkHttpHelper.setTimeouts(httpClient);
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(getApplicationContext(), httpClient).build();
        Fresco.initialize(getApplicationContext(), config);
    }

    @Override
    public TaskExecutorHelper createTaskExecutorHelper() {
        return new TaskExecutorHelper() {};
    }
}
