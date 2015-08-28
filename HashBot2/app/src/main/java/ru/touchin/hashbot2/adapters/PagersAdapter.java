package ru.touchin.hashbot2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.touchin.hashbot2.fragments.TweetListFragment;

/** Fragments Adapter for managing view pager. */
public final class PagersAdapter extends FragmentStatePagerAdapter {

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
    public PagersAdapter(FragmentManager fragmentManager){
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
