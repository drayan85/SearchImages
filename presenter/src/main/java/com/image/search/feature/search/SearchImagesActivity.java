package com.image.search.feature.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;


import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.image.search.BaseActivity;
import com.image.search.R;
import com.image.search.di.component.DaggerSearchImagesActivityComponent;
import com.image.search.di.module.SearchImageModule;
import com.image.search.di.module.SearchImagesActivityModule;

import javax.inject.Inject;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class SearchImagesActivity extends BaseActivity {

    @Inject
    SearchImagesViewModelFactory mSearchImagesViewModelFactory;

    private SearchImagesViewModel mSearchImagesViewModel;

    public static void start(final Context context) {
        Intent starter = new Intent(context, SearchImagesActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerSearchImagesActivityComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .searchImageModule(new SearchImageModule())
                .searchImagesActivityModule(new SearchImagesActivityModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_images);

        onViewReady(savedInstanceState, getIntent());
        mSearchImagesViewModel = ViewModelProviders.of(this, mSearchImagesViewModelFactory).get(SearchImagesViewModel.class);

        mSearchImagesViewModel.imageModels().observe(this, imageModels -> {
            if (imageModels.length == 0) {
                if (!isInternetAvailable()) {
                    //display no internet
                } else {
                    // display empty
                }
            } else {
                // add to the adapter
                if (mSearchImagesViewModel.mIsNewLiveData.get()) {
                    // set the adapter to null
                    mSearchImagesViewModel.mIsNewLiveData.set(false);
                }
            }
        });

        //TODO test
        mSearchImagesViewModel.getImagesBasedOnQueryString(true, "sea", isInternetAvailable());



    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
