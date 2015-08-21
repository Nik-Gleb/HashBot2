package ru.touchin.hashbot2;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.zuzuk.tasks.aggregationtask.TaskExecutorHelper;
import org.zuzuk.tasks.remote.base.TaskExecutorHelperCreator;

/**
 * Class title.
 * Class description.
 * Created by Gleb on 20.08.2015.
 */
public class App extends Application implements TaskExecutorHelperCreator {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }

    @Override
    public TaskExecutorHelper createTaskExecutorHelper() {
        return new TaskExecutorHelper() {};
    }
}
