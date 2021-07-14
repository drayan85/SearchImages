package com.image.search.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.image.search.BaseActivity;
import com.image.search.R;
import com.image.search.databinding.ImageDetailsActivityBinding;
import com.image.search.model.ImageModelParcelable;
import com.image.search.utils.ImageLoadingHelper;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class ImageDetailsActivity extends BaseActivity {

    private static final String EXTRAS_IMAGE_MODEL_DETAILS_OBJECT = "extras_image_model_details_object";

    private ImageModelParcelable mImageModelParcelable;

    public static void start(final Context context, final ImageModelParcelable imageModelParcelable) {
        Intent starter = new Intent(context, ImageDetailsActivity.class);
        starter.putExtra(EXTRAS_IMAGE_MODEL_DETAILS_OBJECT, imageModelParcelable);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageDetailsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_image_details);
        ImageView fullImageView = binding.fullImageView;
        TextView imageWebSearchUrlTextView = binding.imageWebSearchUrlTextView;
        TextView webPageUrlTextView = binding.webPageUrlTextView;

        Toolbar toolbar = binding.imageDetailsToolbar;
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowCustomEnabled(true); //disable a custom view inside the actionbar
            supportActionBar.setDisplayShowTitleEnabled(false); //show the title in the action bar
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extras.setClassLoader(getClass().getClassLoader());
            mImageModelParcelable = extras.getParcelable(EXTRAS_IMAGE_MODEL_DETAILS_OBJECT);
        }

        binding.setImageModelParcelable(mImageModelParcelable);
        binding.executePendingBindings();

        //SET UP THE IMAGE HEIGHT ACCORDING TO THE SCREEN WIDTH
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        fullImageView.getLayoutParams().height = (int) ((dm.widthPixels) * mImageModelParcelable.getHeight() / mImageModelParcelable.getWidth());

        new ImageLoadingHelper(this).load(mImageModelParcelable.getUrl(), fullImageView);

        Linkify.addLinks(imageWebSearchUrlTextView, Linkify.WEB_URLS);
        Linkify.addLinks(webPageUrlTextView, Linkify.WEB_URLS);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
