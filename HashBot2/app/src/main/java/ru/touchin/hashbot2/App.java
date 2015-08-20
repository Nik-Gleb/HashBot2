package ru.touchin.hashbot2;

import android.app.Application;

import org.zuzuk.tasks.aggregationtask.TaskExecutorHelper;
import org.zuzuk.tasks.remote.base.TaskExecutorHelperCreator;

/**
 * Class title.
 * Class description.
 * Created by Gleb on 20.08.2015.
 */
public class App extends Application implements TaskExecutorHelperCreator {
    @Override
    public TaskExecutorHelper createTaskExecutorHelper() {
        return new TaskExecutorHelper() {};
    }
}
