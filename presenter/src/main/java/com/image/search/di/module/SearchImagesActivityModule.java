package com.image.search.di.module;

import com.image.search.domain.interactor.search_image.GetImagesBasedOnQueryUseCase;
import com.image.search.feature.search.SearchImagesViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
@Module
public class SearchImagesActivityModule {

    @Provides
    SearchImagesViewModelFactory provideSearchImagesViewModelFactory(GetImagesBasedOnQueryUseCase getImagesBasedOnQueryUseCase) {
        return new SearchImagesViewModelFactory(getImagesBasedOnQueryUseCase);
    }
}
