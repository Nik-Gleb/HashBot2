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

public class TabsFragment extends BaseExecutorFragment {

    /** ViewPager Widget. */
    private ViewPager mViewPager = null;
    /** Tab Layout Widget. */
    private TabLayout mTabLayout = null;


    @Override
    protected View onCreateViewInternal(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getChildFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer2, new TweetListFragment()).commit();

        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentsAdapter(getChildFragmentManager()));

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /** Fragments Adapter for managing view pager. */
    private static final class FragmentsAdapter extends FragmentStatePagerAdapter {

        /** All Pages (in this implementation hardcoded). */
        private final TweetListFragment[] mItems = new TweetListFragment[] {
                TweetListFragment.newInstance("Android"),
                TweetListFragment.newInstance("Twitter"),
                TweetListFragment.newInstance("Dribble")
        };

        /**
         * Constructs new fragments pager with support fragment manager.
         * @param fragmentManager support fragment manager
         */
        FragmentsAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position position of fragment.
         */
        @Override
        public Fragment getItem(int position) {return mItems[position];}

        /** Return the number of views available. */
        @Override
        public int getCount() {return mItems.length;}

        /**
         * @param position position
         * @return page title by position
         */
        @Override
        public CharSequence getPageTitle(int position) {return "#" + mItems[position].getHashTag();}
    }


}
