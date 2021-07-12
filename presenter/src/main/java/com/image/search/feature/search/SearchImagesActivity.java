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

import javax.inject.Inject;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class SearchImagesActivity extends BaseActivity {

    @Inject
    SearchImagesViewModelFactory mSearchImagesViewModelFactory;

    private SearchImagesViewModel mSearchImagesViewModel;


    private LifecycleRegistry registry = new LifecycleRegistry(this);

    public static void start(final Context context) {
        Intent starter = new Intent(context, SearchImagesActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void resolveDaggerDependency() {

    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        onViewReady(savedInstanceState, getIntent());
        mSearchImagesViewModel = new ViewModelProvider(this).get(SearchImagesViewModel.class);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
