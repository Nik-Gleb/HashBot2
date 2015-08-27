package ru.touchin.hashbot2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import ru.touchin.hashbot2.R;
import ru.touchin.hashbot2.api.models.Tweet;
import ru.touchin.hashbot2.fragments.base.BaseLoadedFragment;

public class InfoFragment extends BaseLoadedFragment implements View.OnClickListener {

    @Override
    protected View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_tweet_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((ImageButton)findViewById(R.id.write_image_button)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.call_image_button)).setOnClickListener(this);

    }

    public boolean isHomeButtonVisible() {
        return true;
    }

    @Override
    public void configureActionBar() {
        super.configureActionBar();
        getActivity().setTitle(R.string.info);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_image_button:
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+79289166512")));
                break;
            case R.id.write_image_button:
                try {
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_SEND)
                            .putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com"),
                            "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
