package ru.touchin.hashbot2.api.creators;

import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.util.ArrayList;

import ru.touchin.hashbot2.App;
import ru.touchin.hashbot2.api.RequestFailListener;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTask;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.api.models.TwitterSearchResults;
import ru.touchin.hashbot2.api.requests.TweetListRequest;

public class TweetPagingTask extends RemoteAggregationPagingTask<Tweet> {

    private final String tag;
    private final int limit;
    private final TweetPagingTaskCreator taskCreator;


    protected TweetPagingTask(RequestFailListener requestFailListener, int limit, String tag, TweetPagingTaskCreator taskCreator) {
        super(requestFailListener, offset, limit, tag);
        this.tag = tag;
        this.limit = limit;
        this.taskCreator = taskCreator;
    }

    /**
     * Loading data at current stage
     * It is executing in main UI thread.
     *
     * @param executor
     * @param currentTaskStageState
     */
    @Override
    public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        executor.executeRequest(new TweetListRequest(offset, limit, tag, taskCreator.getMaxId()), new RequestListener() {
            @Override
            public void onRequestFailure(SpiceException e) {
                Toast.makeText(App.getInstance().getApplicationContext(), "Can't load new tweets", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(Object o) {

                final ArrayList<Tweet> tweets = ((TwitterSearchResults) o).getTweets();

                if (tweets != null && tweets.size() != 0)
                    taskCreator.setMaxId(((Tweet) tweets.get(tweets.size() - 1)).getId());

                setPageItems(tweets);

            }
        });
    }
}
