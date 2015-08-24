package ru.touchin.hashbot2.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.zuzuk.ui.activities.BaseExecutorActivity;
import org.zuzuk.ui.fragments.BaseFragment;
import org.zuzuk.utils.Lc;

import java.util.List;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.api.RequestFailListener;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.fragments.TabsFragment;
import ru.touchin.hashbot2.fragments.TweetDetailsFragment;
import ru.touchin.hashbot2.fragments.base.BaseLoadedFragment;

public class MainActivity extends BaseExecutorActivity implements RequestFailListener {

    private BaseLoadedFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            setFirstFragment(TabsFragment.class);
        }

    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.mainFragmentContainer;
    }

    @Override
    public void onRequestFailure(List<Exception> exceptionList) {
        Exception error = exceptionList.get(0);

        if (error.getCause() instanceof Exception) {
            error = (Exception) error.getCause();
        }
        Lc.e(error.getMessage());

        String msg = error.getMessage();

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                upButtonClicked();
                return true;
            case R.id.action_info:
                final Tweet tweet = new Tweet();

                final Bundle options = new Bundle();
                options.putSerializable(TweetDetailsFragment.ARG_TWEET, tweet);

                pushFragment(TweetDetailsFragment.class, options);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void updateActionBarState() {
        boolean homeButtonVisible = currentFragment == null || currentFragment.isHomeButtonVisible();
        boolean drawerIndicatorEnabled = isCurrentFragmentTop() && homeButtonVisible;
        if (currentFragment != null) {
            currentFragment.configureActionBar();
        }
        if (!drawerIndicatorEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (!homeButtonVisible) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentFragment = null;
    }

    @Override
    public void onBackStackChanged() {
        super.onBackStackChanged();
        updateActionBarState();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        updateActionBarState();
    }

    @Override
    public void onFragmentStarted(BaseFragment fragment) {
        super.onFragmentStarted(fragment);
        currentFragment = (BaseLoadedFragment) fragment;
        updateActionBarState();
    }


}
