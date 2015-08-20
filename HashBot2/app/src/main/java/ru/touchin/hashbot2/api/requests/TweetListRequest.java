package ru.touchin.hashbot2.api.requests;

import com.squareup.okhttp.Request;

import org.zuzuk.tasks.remote.GetJsonRequest;

import java.io.IOException;

public class TweetListRequest extends GetJsonRequest {

    public TweetListRequest(int offset, int limit, Class responseResultType) {
        super(responseResultType);
    }

    public TweetListRequest(int offset, int limit) {
        this(offset, limit, null);
    }

    @Override
    protected String getUrl() {
        return null;
    }


}
