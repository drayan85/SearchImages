package com.image.search.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.image.search.BaseActivity;
import com.image.search.R;
import com.image.search.databinding.ImageDetailsActivityBinding;
import com.image.search.model.ImageModelParcelable;

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
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageDetailsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_image_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extras.setClassLoader(getClass().getClassLoader());
            mImageModelParcelable = extras.getParcelable(EXTRAS_IMAGE_MODEL_DETAILS_OBJECT);
        }
    }
}
