package ru.touchin.hashbot2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.fragments.base.BaseLoadingFragment;

public class TweetDetailsFragment extends BaseLoadingFragment {

    static final String ARG_USER_NAME = "user_name";
    static final String ARG_PROFILE_IMAGE_URL = "profile_image_url";
    static final String ARG_TEXT = "text";
    static final String ARG_DATE = "date";
    static final String ARG_SOURCE = "source";

    @Override
    protected void loadFragmentData(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {

    }

    @Override
    protected void onFragmentDataLoaded(AggregationTaskStageState currentTaskStageState) {
        Log.i("FRAGMENT_ARGUMENT", getArguments().getString(ARG_USER_NAME));
        Log.i("FRAGMENT_ARGUMENT", getArguments().getString(ARG_PROFILE_IMAGE_URL));
        Log.i("FRAGMENT_ARGUMENT", getArguments().getString(ARG_TEXT));
        Log.i("FRAGMENT_ARGUMENT", getArguments().getString(ARG_DATE));


    }

    @Override
    protected View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_tweet_details, container, false);
    }
}
