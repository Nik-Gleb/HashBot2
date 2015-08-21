package ru.touchin.hashbot2.api.requests;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.zuzuk.tasks.remote.GetJsonRequest;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import ru.touchin.hashbot2.api.GetOAuthConsumer;

public class TweetListRequest extends GetJsonRequest {

    private final Random random = new Random(System.nanoTime());

    public TweetListRequest(int offset, int limit, Class responseResultType) {
        super(responseResultType);
    }

    public TweetListRequest(int offset, int limit) {
        this(offset, limit, Object.class);
    }

    @Override
    protected String getUrl() {
        final String CONSUMER_KEY = "nt05bAdGt63qZ3cYsDACrmb2n";
        final String CONSUMER_SECRET = "rvXaJWNQFmUq1XxEktvwO5MDMV3RkSi6g6JoMY5hvG04L2bpyV";
        final String ACCESS_TOKEN = "3432587974-uiL0SwtTwVwhtzkam7JvN8HAElpfcPIj3lMJRuj";
        final String TOKEN_SECRET = "vonOsieN4CJ1w5Zqq7gzpspnB1doqnzxoHB1obe9LkPNV";

        DefaultOAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY,
                CONSUMER_SECRET);

        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String res = null;

        try {
            res = consumer.sign("https://api.twitter.com/1.1/search/tweets.json?q=%23android");
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

        Log.d("HEADER", res);

        return res;
    }

}
