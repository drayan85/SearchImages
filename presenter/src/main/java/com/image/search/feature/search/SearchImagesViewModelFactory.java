package com.image.search.feature.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
class SearchImagesViewModelFactory implements ViewModelProvider.Factory {

    //private final GetSearchImagesBasedOnQuery mGetSearchImagesBasedOnQuery;


    public SearchImagesViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchImagesViewModel.class)) {
            return (T) new SearchImagesViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
