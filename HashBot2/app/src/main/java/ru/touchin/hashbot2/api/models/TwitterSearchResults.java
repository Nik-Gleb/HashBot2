package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;

public class TwitterSearchResults extends ObjectFromJson {
    @Key("statuses")
    private ArrayList<Tweet> mTweets;
    /** @return Tweets */
    public ArrayList<Tweet> getTweets() {return mTweets;}
}
