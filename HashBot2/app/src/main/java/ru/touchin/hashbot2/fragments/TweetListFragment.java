package ru.touchin.hashbot2.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.commons.lang3.StringUtils;
import org.zuzuk.providers.RequestPagingProvider;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.utils.Lc;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.adapters.TweetAdapter;
import ru.touchin.hashbot2.api.creators.TweetPagingTaskCreator;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.fragments.base.BaseListViewFragment;

public class TweetListFragment extends BaseListViewFragment {

    private RequestPagingProvider<Tweet> provider;
    private TweetAdapter adapter;

    private ListView listView;
    private Parcelable listViewInstanceState;

    /** The Tweets Fragment ARG TAG. */
    private static final String ARG_TAG = "hash_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        listView = this.<ListView>findViewById(R.id.fragmentList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onItemClick(int position) {
        super.onItemClick(position);
        final Tweet tweet = adapter.get(position);

        final Bundle options = new Bundle();
        options.putSerializable(TweetDetailsFragment.ARG_TWEET, tweet);

        getMainActivity().pushFragment(TweetDetailsFragment.class, options);
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
        if (provider == null)
            provider = new RequestPagingProvider<Tweet>(this, new TweetPagingTaskCreator(this, getHashTag()));
        provider.initialize(getListPosition(), executor);
    }

    @Override
    protected void onFragmentDataLoaded(AggregationTaskStageState currentTaskStageState) {
        super.onFragmentDataLoaded(currentTaskStageState);
        if (provider == null || !provider.equals(adapter.getProvider()))
            adapter.setProvider(provider);
    }

    /** @return hash tag for search. */
    public String getHashTag() {
        return getArguments() == null ? null : getArguments().getString(ARG_TAG);
    };

    /**
     * Constructs new Blogs Fragment with specified tag for search.
     * @param tag tag for search
     * @return fragment instance
     */
    public static TweetListFragment newInstance(String tag) {
        final TweetListFragment blogsFrgament = new TweetListFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(ARG_TAG, tag);
        blogsFrgament.setArguments(arguments);
        return blogsFrgament;
    }

    public boolean isHomeButtonVisible() {
        return false;
    }

}
