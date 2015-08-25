package ru.touchin.hashbot2;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OkHttpHelper {

    public static void setTimeouts(OkHttpClient okHttpClient) {
        okHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
    }

}
