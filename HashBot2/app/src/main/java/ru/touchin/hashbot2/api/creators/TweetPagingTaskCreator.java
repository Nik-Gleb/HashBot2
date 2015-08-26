package ru.touchin.hashbot2.api.creators;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.utils.Lc;

import java.util.ArrayList;

import ru.touchin.hashbot2.api.RequestFailListener;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTask;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTaskCreator;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.api.models.TwitterSearchResults;
import ru.touchin.hashbot2.api.requests.TweetListRequest;

public class TweetPagingTaskCreator extends RemoteAggregationPagingTaskCreator<Tweet> {

    private final String mTag;
    private String mMaxId;

    public TweetPagingTaskCreator(RequestFailListener requestFailListener, String tag) {
        super(requestFailListener);
        mTag = tag;

    }



    @Override
    public AggregationPagingTask<Tweet> createPagingTask(final int offset, final int limit) {

        return new RemoteAggregationPagingTask<Tweet>(getRequestFailListener(), offset, limit, mTag) {

            /**
             * Loading data at current stage
             * It is executing in main UI thread.
             *
             * @param executor
             * @param currentTaskStageState
             */
            @Override
            public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {

                executor.executeRequest(new TweetListRequest(offset, limit, mTag, offset!=0 ? mMaxId : null), new RequestListener() {
                    @Override
                    public void onRequestFailure(SpiceException e) {

                    }

                    @Override
                    public void onRequestSuccess(Object o) {

                        final ArrayList<Tweet> tweets = ((TwitterSearchResults) o).getTweets();

                        if (tweets != null && tweets.size() != 0) {
                            mMaxId = ((Tweet)tweets.get(tweets.size() - 1)).getId();
                        }

                        setPageItems(tweets);

                    }
                });
            }
        };
    }


 }
