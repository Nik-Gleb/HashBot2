package ru.touchin.hashbot2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private TweetAdapter adapter;

    @Override
    protected void onCreateRenewable() {
        super.onCreateRenewable();
        provider = new RequestPagingProvider<>(this, new TweetPagingTaskCreator(this));
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
                adapter.get(position);
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

}
