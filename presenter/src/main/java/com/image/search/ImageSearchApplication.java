package com.image.search;

import androidx.multidex.MultiDexApplication;

import com.image.search.di.component.ApplicationComponent;
import com.image.search.di.component.DaggerApplicationComponent;
import com.image.search.di.module.ApplicationModule;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th og July 2021
 */
public class ImageSearchApplication extends MultiDexApplication {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
