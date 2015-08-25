package ru.touchin.hashbot2.api.models;

import com.google.api.client.util.Key;

import java.io.Serializable;

/**
 * Twitter-typical error.
 * Determines twitter error-responses common format.
 *
 * @author Gleb Nikitenko
 * @since Aug 20, 2015
 */
public class TwitterError implements Serializable {
    /** Code of error. */
    @Key("error_code")
    private int mErrorCode;
    /** Message description. */
    @Key("error_msg")
    private String mErrorMsg;

    /** @return {@link #mErrorCode}. */
    public int getErrorCode() {return mErrorCode;}
    /** @return {@link #mErrorMsg}. */
    public String getErrorMsg() {return mErrorMsg;}
}
