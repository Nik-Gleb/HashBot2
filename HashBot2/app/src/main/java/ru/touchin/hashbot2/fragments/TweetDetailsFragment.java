package ru.touchin.hashbot2.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.activities.MainActivity;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.fragments.base.BaseLoadedFragment;
import ru.touchin.hashbot2.fragments.base.BaseLoadingFragment;

public class TweetDetailsFragment extends BaseLoadedFragment {

    public static final String ARG_TWEET = "tweet";

    @Override
    protected View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_tweet_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Tweet tweet = (Tweet) getArguments().getSerializable(ARG_TWEET);

        ((SimpleDraweeView)findViewById(R.id.image_view))
                .setImageURI(Uri.parse(tweet.getUser().getProfileImage()));;

        ((TextView)findViewById(R.id.text_view_user_name))
                .setText(tweet.getUser().getName());

        ((TextView)findViewById(R.id.text_view_tweet))
                .setText(tweet.getTweet());

        ((TextView)findViewById(R.id.text_view_date))
                .setText(tweet.getDate());

        ((TextView)findViewById(R.id.text_view_url))
                .setMovementMethod(LinkMovementMethod.getInstance());

        final String url = tweet.getUser().getProfileImage();
        ((TextView)findViewById(R.id.text_view_url))
                .setText(Html.fromHtml("<a href=\"" + url + "\">" + url + "</a>"));

    }

    public boolean isHomeButtonVisible() {
        return true;
    }

    @Override
    public void configureActionBar() {
        super.configureActionBar();
        getActivity().setTitle(R.string.tweets);
    }
}
