package com.image.search.di.component;

import com.image.search.di.PerActivity;
import com.image.search.di.module.SearchImageModule;
import com.image.search.di.module.SearchImagesActivityModule;
import com.image.search.feature.search.SearchImagesActivity;

import dagger.Component;

/**
 * @author Ilanthirayan Paramanathan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
    modules = {
            SearchImageModule.class,
            SearchImagesActivityModule.class
    }
)
public interface SearchImagesActivityComponent {

    void inject(SearchImagesActivity  searchImagesActivity);
}
