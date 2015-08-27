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

    private static final String LIST_INSTANCE_STATE = "list_instance_state";

    private ListView listView;
    private Parcelable listViewInstanceState;

    /** The Tweets Fragment ARG TAG. */
    private static final String ARG_TAG = "hash_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            listViewInstanceState = savedInstanceState.getParcelable(LIST_INSTANCE_STATE);

    }

    @Override
    protected void onCreateRenewable() {
        super.onCreateRenewable();
        provider = new RequestPagingProvider<Tweet>(this, new TweetPagingTaskCreator(this, getHashTag().toString()));
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
        provider.initialize(getListPosition(), executor);
        Lc.d("loadFragmentData - " + getHashTag() + " - " +  currentTaskStageState.isTaskWrapped());
    }

    @Override
    protected void onFragmentDataLoaded(AggregationTaskStageState currentTaskStageState) {
        if (listViewInstanceState!=null)
            listView.onRestoreInstanceState(listViewInstanceState);
    }

    /** @return hash tag for search. */
    public CharSequence getHashTag() {
        return getArguments() == null ? null : getArguments().getCharSequence(ARG_TAG);
    };

    /**
     * Constructs new Blogs Fragment with specified tag for search.
     * @param tag tag for search
     * @return fragment instance
     */
    public static TweetListFragment newInstance(CharSequence tag) {
        if (StringUtils.isBlank(tag))
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        if (listView != null)
            outState.putParcelable(LIST_INSTANCE_STATE, listView.onSaveInstanceState());
    }


}
