package com.image.search.feature.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.image.search.domain.interactor.search_image.GetImagesBasedOnQueryUseCase;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class SearchImagesViewModelFactory implements ViewModelProvider.Factory {

    private final GetImagesBasedOnQueryUseCase mGetImagesBasedOnQueryUseCase;


    public SearchImagesViewModelFactory(final GetImagesBasedOnQueryUseCase getImagesBasedOnQueryUseCase) {
        this.mGetImagesBasedOnQueryUseCase = getImagesBasedOnQueryUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchImagesViewModel.class)) {
            return (T) new SearchImagesViewModel(mGetImagesBasedOnQueryUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
