package ru.touchin.hashbot2.api;

import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.Map;
import java.util.SortedSet;

import oauth.signpost.AbstractOAuthConsumer;
import oauth.signpost.basic.UrlStringRequestAdapter;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;

public class GetOAuthConsumer extends AbstractOAuthConsumer {

    public GetOAuthConsumer(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);
    }

    @Override
    protected HttpRequest wrap(Object o) {
        return null;
    }

    public final Map<String, String> getParams() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpRequest req = new UrlStringRequestAdapter("https://api.twitter.com/1.1/search/tweets.json?q=%23android");
        try {


            Log.d("sigg", sign("https://api.twitter.com/1.1/search/tweets.json?q=%23android"));
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
        return req.getAllHeaders();

    }
}
