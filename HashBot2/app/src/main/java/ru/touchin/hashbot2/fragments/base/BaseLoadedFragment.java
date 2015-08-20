package ru.touchin.hashbot2.fragments.base;

import android.animation.LayoutTransition;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.zuzuk.ui.fragments.BaseExecutorFragment;

import java.util.List;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.activities.MainActivity;
import ru.touchin.hashbot2.api.RequestFailListener;


public abstract class BaseLoadedFragment extends BaseExecutorFragment implements RequestFailListener {
    protected View loadingRefreshButton;
    protected View loadingProgressBar;
    protected View loadingContentContainer;

    protected abstract View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public View onCreateViewInternal(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, container, false);

        ViewGroup contentContainerView = (ViewGroup) view.findViewById(R.id.loadingContentContainer);
        View contentView = createContentView(inflater, contentContainerView, savedInstanceState);
        contentContainerView.addView(contentView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.topContainer);
        LayoutTransition layoutTransition = frameLayout.getLayoutTransition();
        frameLayout.setLayoutTransition(null);
        loadingRefreshButton = view.findViewById(R.id.loadingRefreshButton);
        loadingRefreshButton.setVisibility(View.GONE);
        loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        loadingProgressBar.setVisibility(View.GONE);
        loadingContentContainer = view.findViewById(R.id.loadingContentContainer);
        loadingContentContainer.setVisibility(getContentVisibilityOnViewCreated());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { // strange animations blinking
            frameLayout.setLayoutTransition(layoutTransition);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadingRefreshButton = null;
        loadingProgressBar = null;
        loadingContentContainer = null;
    }

    protected int getContentVisibilityOnViewCreated() {
        return View.VISIBLE;
    }

    protected MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onRequestFailure(List<Exception> exceptionList) {
        getMainActivity().onRequestFailure(exceptionList);
    }

}
