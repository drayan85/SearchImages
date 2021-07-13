package com.image.search.domain.interactor.search_image;

import com.image.search.domain.executor.PostExecutionThread;
import com.image.search.domain.executor.ThreadExecutor;
import com.image.search.domain.param.SearchImageParams;
import com.image.search.domain.repository.SearchImageRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
@RunWith(MockitoJUnitRunner.class)
public class GetImagesBasedOnQueryUseCaseTest extends TestCase {

    private static final String QUERY = "sea";
    private static final boolean IS_INTERNET_AVAILABLE = true;
    private static final int PAGE = 1;
    private static final int PER_PAGE = 10;

    private GetImagesBasedOnQueryUseCase mGetImagesBasedOnQueryUseCase;


    @Mock private SearchImageRepository mockSearchImageRepository;
    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        mGetImagesBasedOnQueryUseCase = new GetImagesBasedOnQueryUseCase(mockThreadExecutor, mockPostExecutionThread, mockSearchImageRepository);
    }

    @Test
    public void testGetImagesBasedOnQueryUseCaseObservableSuccessCase() {
        SearchImageParams params = new SearchImageParams.SearchImageParamsBuilder(IS_INTERNET_AVAILABLE)
                .setPage_number(PAGE)
                .setPage_size(PER_PAGE)
                .setQuery(QUERY)
                .build();

        mGetImagesBasedOnQueryUseCase.buildUseCaseObservable(params);

        verify(mockSearchImageRepository).getSearchImagesBasedOnQuery(params.getQuery(), params.getPage_number(), params.getPage_size(), params.isInternetAvailable());

        verifyNoMoreInteractions(mockSearchImageRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }

    @Test
    public void testShouldFailWhenNoOrEmptyParameters() {
        expectedException.expect(NullPointerException.class);
        mGetImagesBasedOnQueryUseCase.buildUseCaseObservable(null);
    }
}