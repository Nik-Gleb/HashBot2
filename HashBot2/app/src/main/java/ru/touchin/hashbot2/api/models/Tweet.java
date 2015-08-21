package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;

public class Tweet extends ObjectFromJson {
    @Key("text")
    private String mText;
    /** @return text */
    public final String getTweet() {return mText;}
}
