package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.util.ArrayList;

public final class Tweet extends ObjectFromJson {

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
    public String getTweet() {return mText;}
    /** @return date of create. */
    public String getDate(){return mDate;}
    /** @return source url. */
    public String getSource(){return mSource;}
    /** @return user info. */
    public User getUser() {return mUser;}
    /** @return id. */
    public String getId() {return mId;}

}
