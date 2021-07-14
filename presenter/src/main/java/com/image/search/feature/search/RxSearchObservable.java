package com.image.search.feature.search;

import androidx.appcompat.widget.SearchView;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public final class RxSearchObservable {

    private RxSearchObservable() {
        // no instance
    }

    public static Observable<String> fromView(final SearchView searchView, final SearchImagesActivity searchImagesActivity) {

        final PublishSubject<String> subject = PublishSubject.create();
        final AtomicBoolean isSubmitButtonClicked = new AtomicBoolean(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                subject.onComplete();
                searchImagesActivity.hideSoftKeyboard();
                isSubmitButtonClicked.set(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String text) {
                if (isSubmitButtonClicked.get()) {
                    isSubmitButtonClicked.set(false);
                    searchImagesActivity.setUpSearchObservable();
                }
                subject.onNext(text);
                return true;
            }
        });
        return subject;
    }
}
