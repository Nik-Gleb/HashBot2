package ru.touchin.hashbot2.activities;

import android.os.Bundle;
import android.widget.Toast;

import org.zuzuk.ui.activities.BaseExecutorActivity;
import org.zuzuk.utils.Lc;

import java.util.List;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.api.RequestFailListener;
import ru.touchin.hashbot2.fragments.TabsFragment;

public class MainActivity extends BaseExecutorActivity implements RequestFailListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
