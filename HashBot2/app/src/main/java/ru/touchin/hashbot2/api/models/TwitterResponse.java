package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

/**
 * Twitter-typical response.
 * Determines twitter responses common format.
 *
 * @param <T> the type of received content.
 *
 * @author Gleb Nikitenko
 * @since Aug 20, 2015
 */
public class TwitterResponse<T> extends ObjectFromJson {

    /** Received content. */
    @Key
    private T mResponse = null;
    /** Typical error response. */
    @Key
    private TwitterError mError = null;

    /** @return {@link #mResponse}. */
    public T getResponse() {return mResponse; }
    /** @return {@link #mError}. */
    public TwitterError getError() {return mError;}

}
