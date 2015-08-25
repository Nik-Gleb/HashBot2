package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class User extends ObjectFromJson {

    @Key("name")
    private String mName;
    @Key("profile_image_url")
    private String mProfileImageUrl;

    /** @return the name of user. */
    public String getName() {return mName;}
    /** @return profile image. */
    public String getProfileImage(){return mProfileImageUrl;}

}
