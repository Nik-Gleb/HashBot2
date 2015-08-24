package ru.touchin.hashbot2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.ui.fragments.BaseExecutorFragment;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.adapters.TweetAdapter;
import ru.touchin.hashbot2.api.creators.TweetPagingTaskCreator;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.fragments.base.BaseLoadingFragment;

public class TweetListFragment extends BaseLoadingFragment {

    private RequestPagingProvider<Tweet> provider;
    private TweetAdapter adapter = null;

    TweetPagingTaskCreator taskCreator = null;

    /** The Tweets Fragment ARG TAG. */
    private static final String ARG_TAG = "hash_tag";

    @Override
    protected void onCreateRenewable() {
        super.onCreateRenewable();

        taskCreator = new TweetPagingTaskCreator(this, getHashTag().toString());

        provider = new RequestPagingProvider<>(this, taskCreator);
    }

    @Override
    protected void onDestroyRenewable() {
        super.onDestroyRenewable();
        provider = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TweetAdapter();

        adapter.setProvider(provider);
        this.<ListView>findViewById(R.id.list).setAdapter(adapter);
        this.<ListView>findViewById(R.id.list).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Tweet tweet = adapter.get(position);

                final Bundle options = new Bundle();
                options.putSerializable(TweetDetailsFragment.ARG_TWEET, tweet);

                getMainActivity().pushFragment(TweetDetailsFragment.class, options);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }


    @Override
    protected View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    protected void loadFragmentData(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        provider.initialize(0, executor);
    }

    @Override
    protected void onFragmentDataLoaded(AggregationTaskStageState currentTaskStageState) {

    }

    /** @return hash tag for search. */
    public final CharSequence getHashTag() {
        return getArguments() != null ? getArguments().getCharSequence(ARG_TAG) : null;
    };

    /**
     * Constructs new Blogs Fragment with specified tag for search.
     * @param tag tag for search
     * @return fragment instance
     */
    public static TweetListFragment newInstance(CharSequence tag) {
        if (tag == null || TextUtils.isEmpty(tag.toString()))
            throw new IllegalArgumentException();
        final TweetListFragment blogsFrgament = new TweetListFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(ARG_TAG, tag.toString());
        blogsFrgament.setArguments(arguments);
        return blogsFrgament;
    }

    public boolean isHomeButtonVisible() {
        return false;
    }



}
