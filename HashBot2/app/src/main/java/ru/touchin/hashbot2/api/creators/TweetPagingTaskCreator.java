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
    private String maxId;

    public TweetPagingTaskCreator(RequestFailListener requestFailListener, String tag) {
        super(requestFailListener);
        mTag = tag;
    }

    final void setMaxId(String maxId) {this.maxId = maxId;}
    final String getMaxId() {return offset!=0 ? this.maxId : null;}

    @Override
    public AggregationPagingTask<Tweet> createPagingTask(final int offset, final int limit) {
        return new TweetPagingTask(getRequestFailListener(), offset, limit, mTag, this);
    }


 }
