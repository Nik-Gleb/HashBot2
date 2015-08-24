package ru.touchin.hashbot2.api.requests;

import android.util.Log;

import org.zuzuk.tasks.remote.GetJsonRequest;

import java.util.Random;

import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import ru.touchin.hashbot2.api.models.TwitterSearchResults;

public class TweetListRequest extends GetJsonRequest {

    /* Credentials. */
    private static final String CONSUMER_KEY = "nt05bAdGt63qZ3cYsDACrmb2n";
    private static final String CONSUMER_SECRET = "rvXaJWNQFmUq1XxEktvwO5MDMV3RkSi6g6JoMY5hvG04L2bpyV";
    private static final String ACCESS_TOKEN = "3432587974-uiL0SwtTwVwhtzkam7JvN8HAElpfcPIj3lMJRuj";
    private static final String TOKEN_SECRET = "vonOsieN4CJ1w5Zqq7gzpspnB1doqnzxoHB1obe9LkPNV";

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
        Log.d("LIMIT", String.valueOf(max_id));
    }

    @Override
    protected String getUrl() {


        DefaultOAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY,
                CONSUMER_SECRET);

        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String res = null;

        try {
            res = consumer.sign("https://api.twitter.com/1.1/search/tweets.json?q=%23" + mTag + (mMaxId != null ? "&max_id=" + mMaxId : "") + "&result_type=recent&count=" + String.valueOf(mLimit));
        } catch (OAuthMessageSignerException e) {
            throw new RuntimeException(e);
        } catch (OAuthExpectationFailedException e) {
            throw new RuntimeException(e);
        } catch (OAuthCommunicationException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

}
