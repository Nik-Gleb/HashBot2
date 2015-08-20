package ru.touchin.hashbot2.api.creators;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import ru.touchin.hashbot2.api.RequestFailListener;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTask;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTaskCreator;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.api.requests.TweetListRequest;

public class TweetPagingTaskCreator extends RemoteAggregationPagingTaskCreator<Tweet> {
    public TweetPagingTaskCreator(RequestFailListener requestFailListener) {
        super(requestFailListener);
    }

    @Override
    public AggregationPagingTask<Tweet> createPagingTask(final int offset, final int limit) {
        return new RemoteAggregationPagingTask<Tweet>(getRequestFailListener(), offset, limit) {
            @Override
            public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
                executor.executeRequest(new TweetListRequest(offset, limit), new RequestListener() {
                    @Override
                    public void onRequestFailure(SpiceException e) {

                    }

                    @Override
                    public void onRequestSuccess(Object o) {
                        setPageItems(null);
                    }
                });
            }
        };
    }
}
