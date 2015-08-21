package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;

public class Tweet extends ObjectFromJson {

    @Key("text")
    private String mText;
    @Key("created_at")
    private String mDate;
    @Key("source")
    private String mSource;
    @Key("user")
    private User mUser;
    @Key("id_str")
    private String mId;

    /** @return text. */
    public final String getTweet() {return mText;}
    /** @return date of create. */
    public final String getDate(){return mDate;}
    /** @return source url. */
    public final String getSource(){return mSource;}
    /** @return user info. */
    public final User getUser() {return mUser;}
    /** @return id. */
    public final String getId() {return mId;}

}
