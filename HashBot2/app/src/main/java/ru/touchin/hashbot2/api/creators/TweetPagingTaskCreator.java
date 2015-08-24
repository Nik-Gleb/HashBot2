package ru.touchin.hashbot2.api.creators;

import android.util.Log;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.utils.Lc;

import java.util.ArrayList;
import java.util.Locale;

import ru.touchin.hashbot2.adapters.TweetAdapter;
import ru.touchin.hashbot2.api.RequestFailListener;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTask;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTaskCreator;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.api.models.TwitterSearchResponse;
import ru.touchin.hashbot2.api.models.TwitterSearchResults;
import ru.touchin.hashbot2.api.requests.TweetListRequest;

public class TweetPagingTaskCreator extends RemoteAggregationPagingTaskCreator<Tweet> {

    private final String mTag;

    private String mMaxID;

    public TweetPagingTaskCreator(RequestFailListener requestFailListener, String tag) {
        super(requestFailListener);
        mTag = tag;
    }

    @Override
    public AggregationPagingTask<Tweet> createPagingTask(final int offset, final int limit) {

        return new InternalAgregationPaggingTask(getRequestFailListener(), offset, limit, mMaxID, mTag);
    }

    public final void setMaxID(String maxID) {mMaxID = maxID;}

    private static final class InternalAgregationPaggingTask extends RemoteAggregationPagingTask<Tweet> {

        private String mMaxId;
        private final String mTag;
        private final int mOffset;
        private final int mLimit;

        protected InternalAgregationPaggingTask(RequestFailListener requestFailListener, int offset, int limit, String maxId, String tag) {
            super(requestFailListener, offset, limit);
            mMaxId = maxId;
            mTag = tag;
            mOffset = offset;
            mLimit = limit;
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

            executor.executeRequest(new TweetListRequest(mOffset, mLimit, mTag, mMaxId), new RequestListener() {
                @Override
                public void onRequestFailure(SpiceException e) {

                }

                @Override
                public void onRequestSuccess(Object o) {

                    final ArrayList<Tweet> tweets = ((TwitterSearchResults) o).getTweets();

                    mMaxId = mOffset != 0 && tweets.get(tweets.size() - 1) != null ?
                            ((Tweet)tweets.get(tweets.size() - 1)).getId() : null;

                    Log.i(" ==== Tweets ==== ",String.format(Locale.getDefault(), "Offset %s, Limit %s, Maxid - %s - %s", mOffset, mLimit, mMaxId, tweets.size()));

                    for (Tweet tweet : tweets){
                        Log.i("Tweets: ", tweet.getId());
                    }

                    setPageItems(tweets);
                }
            });
        }
    }
}
