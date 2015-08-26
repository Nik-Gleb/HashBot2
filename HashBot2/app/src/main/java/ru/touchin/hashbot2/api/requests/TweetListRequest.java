package ru.touchin.hashbot2.api.requests;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.squareup.okhttp.Request;

import org.zuzuk.tasks.remote.GetJsonRequest;
import org.zuzuk.utils.Lc;

import java.io.IOException;
import java.util.Random;

import ru.touchin.hashbot2.api.models.TwitterSearchResults;

public class TweetListRequest extends GetJsonRequest {

    

    private final Random random = new Random(System.nanoTime());

    private final String mTag;
    private final int mLimit;
    private final String mMaxId;

    public TweetListRequest(int offset, int limit, String tag, String max_id, Class responseResultType) {
        super(responseResultType);
        mTag = tag;
        mLimit = limit;
        mMaxId = max_id;

    }

    public TweetListRequest(int offset, int limit, String tag, String max_id) {
        this(offset, limit, tag, max_id, TwitterSearchResults.class);
    }

    @Override
    protected String getUrl() {
        return "https://api.twitter.com/1.1/search/tweets.json";//?q=%23" + mTag + (mMaxId != null ? "&max_id=" + mMaxId : "");
    }

    @Override
    protected Request.Builder createHttpRequest() throws IOException {

        String auth = "Bearer " + ACCESS_TOKEN;
        return super.createHttpRequest().header("Authorization", auth);

    }

    @Override
    protected void setupUrlParameters(GenericUrl url) {
        url.put("q", "%23" + mTag);
        if (mMaxId != null)
            url.put("max_id", mMaxId);
        url.put("count", mLimit);
        super.setupUrlParameters(url);
    }
}
