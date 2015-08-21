package ru.touchin.hashbot2.api.creators;

import android.util.Log;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.zuzuk.tasks.aggregationtask.AggregationPagingTask;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import ru.touchin.hashbot2.adapters.TweetAdapter;
import ru.touchin.hashbot2.api.RequestFailListener;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTask;
import ru.touchin.hashbot2.api.creators.base.RemoteAggregationPagingTaskCreator;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.api.models.TwitterSearchResponse;
import ru.touchin.hashbot2.api.models.TwitterSearchResults;
import ru.touchin.hashbot2.api.requests.TweetListRequest;

public class TweetPagingTaskCreator extends RemoteAggregationPagingTaskCreator<Tweet> {

    private TweetAdapter mTweetAdapter = null;

    private final String mTag;

    public TweetPagingTaskCreator(RequestFailListener requestFailListener, String tag) {
        super(requestFailListener);
        mTag = tag;
    }

    @Override
    public AggregationPagingTask<Tweet> createPagingTask(final int offset, final int limit) {

        final String max_id = mTweetAdapter != null && offset != 0 && mTweetAdapter.getItem(offset - 1) != null ?
                ((Tweet)mTweetAdapter.getItem(offset - 1)).getId() : null;

        return new InternalAgregationPaggingTask(getRequestFailListener(), offset, limit, max_id, mTag);
    }

    /** @param tweetAdapter adapter for access to max_id by pos. */
    public final void setAdapter(TweetAdapter tweetAdapter) {
        mTweetAdapter = tweetAdapter;
    }

    private static final class InternalAgregationPaggingTask extends RemoteAggregationPagingTask<Tweet> {

        private final String mMaxId;
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


            System.out.println("max_id - " + mMaxId);

            executor.executeRequest(new TweetListRequest(mOffset, mLimit, mTag, mMaxId), new RequestListener() {
                @Override
                public void onRequestFailure(SpiceException e) {

                }

                @Override
                public void onRequestSuccess(Object o) {
                    setPageItems(((TwitterSearchResults) o).getTweets());
                }
            });
        }
    }
}
