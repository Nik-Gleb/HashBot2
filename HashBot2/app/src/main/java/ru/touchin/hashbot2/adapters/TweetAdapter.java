package ru.touchin.hashbot2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.ui.adapters.ProviderAdapter;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.api.models.Tweet;

public class TweetAdapter extends ProviderAdapter<Tweet, RequestPagingProvider<Tweet>> {

    private static final int EMPTY_ITEM = 0;
    private static final int ITEM = 1;

    @Override
    protected View newView(int position, LayoutInflater inflater, ViewGroup parent) {
        if(getItemViewType(position) == EMPTY_ITEM) {
            return new ProgressBar(inflater.getContext());
        } else {
            return inflater.inflate(R.layout.view_tweet, parent, false);
        }
    }

    @Override
    protected void bindView(View view, Tweet tweet, int position) {
        if(getItemViewType(position) == ITEM) {
            ((TextView) view.findViewById(R.id.text)).setText(tweet.getTweet());
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return get(position) == null ? EMPTY_ITEM : ITEM;
    }

}
