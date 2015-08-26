package ru.touchin.hashbot2.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.zuzuk.tasks.aggregationtask.AggregationTask;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStage;
import org.zuzuk.tasks.aggregationtask.AggregationTaskStageState;
import org.zuzuk.tasks.aggregationtask.RequestAndTaskExecutor;
import org.zuzuk.utils.Lc;

public abstract class BaseLoadingFragment extends BaseLoadedFragment implements AggregationTask {

    private boolean fragmentDataLoaded = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingRefreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reload();
            }
        });
    }

    @Override
    protected int getContentVisibilityOnViewCreated() {
        return fragmentDataLoaded ? View.VISIBLE : View.INVISIBLE;
    }

    protected void reload() {
        fragmentDataLoaded = false;
        Lc.d("reload - " + this);
        loadFragment();
    }

    private void loadFragment() {
        Lc.d("loadFragment - " + this);
        executeAggregationTask(BaseLoadingFragment.this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Lc.d("onResume - " + this);
        loadFragment();
    }

    @Override
    public boolean isLoadingNeeded(AggregationTaskStageState currentTaskStageState) {
        return true;
    }

    @Override
    public boolean isLoaded(AggregationTaskStageState currentTaskStageState) {
        return !currentTaskStageState.hasExceptions() && (fragmentDataLoaded || currentTaskStageState.getTaskStage() != AggregationTaskStage.PRE_LOADING);
    }

    @Override
    public void onLoadingStarted(AggregationTaskStageState currentTaskStageState) {
        if (currentTaskStageState.getTaskStage() == AggregationTaskStage.REAL_LOADING) {
            loadingRefreshButton.setVisibility(View.GONE);
            boolean loadingWithCacheMiss = !currentTaskStageState.getPreviousStageState().isLoaded();
            loadingProgressBar.setVisibility(loadingWithCacheMiss ? View.VISIBLE : View.GONE);
            loadingContentContainer.setVisibility(!loadingWithCacheMiss ? View.VISIBLE : View.INVISIBLE);
            fragmentDataLoaded = !loadingWithCacheMiss;
        }
    }

    @Override
    public void onLoaded(AggregationTaskStageState currentTaskStageState) {
        fragmentDataLoaded = true;
        loadingProgressBar.setVisibility(View.GONE);
        loadingContentContainer.setVisibility(View.VISIBLE);
        loadingRefreshButton.setVisibility(View.GONE);
        onFragmentDataLoaded(currentTaskStageState);
    }

    @Override
    public void onFailed(AggregationTaskStageState currentTaskStageState) {
        if (currentTaskStageState.getTaskStage() == AggregationTaskStage.REAL_LOADING) {
            boolean noDataToShow = !currentTaskStageState.isLoaded()
                    && !currentTaskStageState.getPreviousStageState().isLoaded()
                    && !currentTaskStageState.getPreviousStageState().getPreviousStageState().isLoaded();
            loadingProgressBar.setVisibility(View.GONE);
            loadingContentContainer.setVisibility(noDataToShow ? View.INVISIBLE : View.VISIBLE);
            loadingRefreshButton.setVisibility(!noDataToShow ? View.GONE : View.VISIBLE);
            fragmentDataLoaded = !noDataToShow;
            if (currentTaskStageState.hasExceptions()) {
                onRequestFailure(currentTaskStageState.getExceptions());
            }

            getMainActivity().hideSoftInput();
        }
    }

    @Override
    public void load(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState) {
        Lc.d("load - " + this);
        loadFragmentData(executor, currentTaskStageState);

    }

    public boolean isFragmentDataLoaded() {
        return fragmentDataLoaded;
    }

    protected abstract void loadFragmentData(RequestAndTaskExecutor executor, AggregationTaskStageState currentTaskStageState);

    protected abstract void onFragmentDataLoaded(AggregationTaskStageState currentTaskStageState);

    @Override
    protected void onDestroyRenewable() {
        super.onDestroyRenewable();
        fragmentDataLoaded = false;
    }

}
