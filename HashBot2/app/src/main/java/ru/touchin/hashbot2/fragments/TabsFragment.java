package ru.touchin.hashbot2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zuzuk.ui.fragments.BaseExecutorFragment;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.adapters.PagersAdapter;
import ru.touchin.hashbot2.fragments.base.BaseLoadedFragment;

public class TabsFragment extends BaseLoadedFragment {


    @Override
    protected View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getChildFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer2, new TweetListFragment()).commit();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagersAdapter(getChildFragmentManager()));

        ((TabLayout) findViewById(R.id.tabs))
                .setupWithViewPager(viewPager);
    }

    public boolean isHomeButtonVisible() {
        return false;
    }

    @Override
    public void configureActionBar() {
        super.configureActionBar();
        getActivity().setTitle(R.string.app_name);
    }




}
