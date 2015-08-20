package ru.touchin.hashbot2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zuzuk.ui.fragments.BaseExecutorFragment;

import ru.touchin.hashbot2.R;

public class TabsFragment extends BaseExecutorFragment {

    @Override
    protected View onCreateViewInternal(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getChildFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer2, new TweetListFragment()).commit();
    }

}
