package com.image.search.feature.splash;


import android.os.Bundle;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;

import com.image.search.BaseActivity;
import com.image.search.R;
import com.image.search.databinding.SplashScreenActivityBinding;
import com.image.search.feature.search.SearchImagesActivity;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreenActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        Button startSearchButton = binding.startSearchButton;
        startSearchButton.setOnClickListener(v -> {
            SearchImagesActivity.start(this);
            finish();
        });
    }
}