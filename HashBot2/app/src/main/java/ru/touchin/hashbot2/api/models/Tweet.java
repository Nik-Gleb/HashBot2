package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public final class Tweet extends ObjectFromJson {

    private static final SimpleDateFormat IN_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
    private static final SimpleDateFormat OUT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

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
    public String getDate(){

        try {
            return OUT_DATE_FORMAT.format(IN_DATE_FORMAT.parse(mDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    /** @return source url. */
    public String getSource(){return mSource;}
    /** @return user info. */
    public User getUser() {return mUser;}
    /** @return id. */
    public String getId() {return mId;}

}
