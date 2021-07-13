package com.image.search.domain.interactor.search_image;

import com.google.common.base.Preconditions;
import com.image.search.domain.executor.PostExecutionThread;
import com.image.search.domain.executor.ThreadExecutor;
import com.image.search.domain.interactor.UseCase;
import com.image.search.domain.model.ImageModel;
import com.image.search.domain.param.SearchImageParams;
import com.image.search.domain.repository.SearchImageRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class GetImagesBasedOnQueryUseCase extends UseCase<ImageModel[], SearchImageParams> {

    private final SearchImageRepository mSearchImageRepository;

    @Inject
    public GetImagesBasedOnQueryUseCase(final ThreadExecutor threadExecutor,
                                        final PostExecutionThread postExecutionThread,
                                        final SearchImageRepository mSearchImageRepository) {
        super(threadExecutor, postExecutionThread);
        this.mSearchImageRepository = mSearchImageRepository;
    }

    @Override
    public Observable<ImageModel[]> buildUseCaseObservable(SearchImageParams params) {
        Preconditions.checkNotNull(params);
        return mSearchImageRepository.getSearchImagesBasedOnQuery(params.getQuery(), params.getPage_number(), params.getPage_size(), params.isInternetAvailable());
    }
}
