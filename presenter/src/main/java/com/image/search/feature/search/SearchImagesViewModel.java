package com.image.search.feature.search;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.image.search.data.exception.RetrofitException;
import com.image.search.domain.interactor.DefaultObserver;
import com.image.search.domain.interactor.search_image.GetImagesBasedOnQueryUseCase;
import com.image.search.domain.model.ImageModel;
import com.image.search.domain.model.http.response.ImageSearchResponse;
import com.image.search.domain.param.SearchImageParams;
import com.image.search.model.APIError;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class SearchImagesViewModel extends ViewModel {

    private final GetImagesBasedOnQueryUseCase mGetImagesBasedOnQueryUseCase;

    private MutableLiveData<ImageModel[]> imageModelsLiveData = new MutableLiveData<>();

    private MutableLiveData<String> messagesLiveData = new MutableLiveData<>();

    protected final ObservableBoolean mIsNewLiveData = new ObservableBoolean();

    private final AtomicInteger page = new AtomicInteger(0);
    private static final int PER_PAGE = 10;

    public SearchImagesViewModel(GetImagesBasedOnQueryUseCase getImagesBasedOnQueryUseCase) {
        this.mGetImagesBasedOnQueryUseCase = getImagesBasedOnQueryUseCase;
    }

    public void getImagesBasedOnQueryString(final boolean forceUpdate, final String query, final boolean isInternetAvailable) {
        if (forceUpdate) {
            page.set(0);
        }
        mGetImagesBasedOnQueryUseCase.execute(new ImageSearchResponseObserver() {
            @Override
            public void onNext(ImageSearchResponse imageSearchResponse) {
                if (page.get() == 1) {
                    //TODO need to clear the existing items on the view
                }
                imageModelsLiveData.postValue(imageSearchResponse.getValue());
            }

            @Override
            public void onError(Throwable e) {
                retrofitExceptionHandler(e);
            }

        }, new SearchImageParams
                .SearchImageParamsBuilder(isInternetAvailable)
                .setQuery(query).setPage_size(PER_PAGE)
                .setPage_number(page.incrementAndGet())
                .build());
    }

    private void retrofitExceptionHandler(final Throwable e) {
        APIError errorBody = null;
        if (e instanceof RetrofitException) {
            try {
                RetrofitException retrofitException = (RetrofitException) e;
                errorBody = retrofitException.getErrorBodyAs(APIError.class);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        messagesLiveData.postValue(errorBody != null ? errorBody.getMessage() : e.getMessage());
    }

    /**
     * Exposes the toast messages so the UI can observe it
     */
    public LiveData<String> toastMessage() {
        return messagesLiveData;
    }


    /**
     * Exposes the latest images so the UI can observe it
     */
    public LiveData<ImageModel[]> imageModels() {
        return imageModelsLiveData;
    }

    @Override
    protected void onCleared() {
        mGetImagesBasedOnQueryUseCase.dispose();
    }

    /**
     * class to Observe {@link ImageSearchResponse} Observer.
     */
    private abstract class ImageSearchResponseObserver extends DefaultObserver<ImageSearchResponse> {
    }
}
