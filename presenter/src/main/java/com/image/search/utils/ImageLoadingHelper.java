package com.image.search.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.image.search.di.module.GlideApp;
import com.image.search.di.module.GlideRequests;

import javax.inject.Inject;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class ImageLoadingHelper {

    private final GlideRequests mGlideRequests;

    @Inject
    public ImageLoadingHelper(final Context context) {
        mGlideRequests = GlideApp.with(context);
    }


    public void load(final String url, final ImageView imageView) {
        try {
            mGlideRequests
                    .load(url)
                    //.placeholder(R.drawable.grey_bg)
                    //.error(R.drawable.error_loading_default_image)
                    .into(imageView);
        } catch (IllegalArgumentException e) {
            Log.e("Glide", "ImageView Tag: " + String.valueOf(imageView.getTag()));
        }
    }

}
