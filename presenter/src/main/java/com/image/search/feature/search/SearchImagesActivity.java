package com.image.search.feature.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.image.search.BaseActivity;
import com.image.search.R;
import com.image.search.databinding.SearchImagesActivityBinding;
import com.image.search.di.component.DaggerSearchImagesActivityComponent;
import com.image.search.di.module.SearchImageModule;
import com.image.search.di.module.SearchImagesActivityModule;
import com.image.search.domain.model.ImageModel;
import com.image.search.feature.details.ImageDetailsActivity;
import com.image.search.feature.search.adapter.ImageRecyclerViewAdapter;
import com.image.search.model.mapper.ImageModelParcelableMapper;
import com.image.search.utils.ImageLoadingHelper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class SearchImagesActivity extends BaseActivity implements ImageRecyclerViewAdapter.ImageRecyclerViewAdapterCallback {

    @Inject
    SearchImagesViewModelFactory mSearchImagesViewModelFactory;

    @Inject
    ImageLoadingHelper mImageLoadingHelper;

    private SearchImagesViewModel mSearchImagesViewModel;

    private SearchView searchView;
    private View mNoInternetConnectionView;
    private View mSearchImageLoadingLayout;
    private View mEmptyResultLayout;
    private RecyclerView mRecyclerView;
    private ImageRecyclerViewAdapter mRecyclerViewAdapter;

    private boolean startDownloading;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private final int visibleThreshold = 1; // The minimum amount of items to have below your current scroll position before loading more.
    private int firstVisibleItem, visibleItemCount, totalItemCount;


    private String mQuery;

    public static void start(final Context context) {
        Intent starter = new Intent(context, SearchImagesActivity.class);
        context.startActivity(starter);
    }

    //ImageRecyclerViewAdapter.ImageRecyclerViewAdapterCallback
    @Override
    public void onItemClick(final ImageModel imageModel) {
        ImageDetailsActivity.start(this, new ImageModelParcelableMapper().transForm(imageModel));
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
        SearchImagesActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search_images);

        Toolbar mToolbar = binding.searchToolbar;
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowCustomEnabled(true); //disable a custom view inside the actionbar
            supportActionBar.setDisplayShowTitleEnabled(false); //show the title in the action bar
            setSearchActionBarCustomView(supportActionBar);
        }

        mNoInternetConnectionView = binding.noInternetLinearLayout;
        mSearchImageLoadingLayout = binding.searchImageLoadingLayout;
        mEmptyResultLayout = binding.emptyResultLayout;
        mRecyclerView = binding.searchImageRecyclerView;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                visibleItemCount = mRecyclerView.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if (totalItemCount > previousTotal + 1) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold) && !startDownloading) {
                    loading = true; // End has been reached
                    onLoadMore();
                }
            }
        });

        onViewReady(savedInstanceState, getIntent());
        mSearchImagesViewModel = ViewModelProviders.of(this, mSearchImagesViewModelFactory).get(SearchImagesViewModel.class);
        startObservingImageResults();
    }

    private void startObservingImageResults() {
        mSearchImagesViewModel.imageModels().observe(this, imageModels -> {
            if (imageModels.length == 0) {
                if (!isInternetAvailable()) {
                    mNoInternetConnectionView.setVisibility(View.VISIBLE);
                    mEmptyResultLayout.setVisibility(View.GONE);
                } else {
                    mNoInternetConnectionView.setVisibility(View.GONE);
                    mEmptyResultLayout.setVisibility(View.VISIBLE);
                }
                mSearchImageLoadingLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
            } else { // add to the adapter
                mNoInternetConnectionView.setVisibility(View.GONE);
                mSearchImageLoadingLayout.setVisibility(View.GONE);
                mEmptyResultLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

                if (mSearchImagesViewModel.mIsNewLiveData.get()) {
                    mRecyclerViewAdapter = null;
                    mSearchImagesViewModel.mIsNewLiveData.set(false);
                }

                if (mRecyclerViewAdapter == null) {
                    mRecyclerView.setHasFixedSize(true);
                    //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerViewAdapter = new ImageRecyclerViewAdapter(Arrays.asList(imageModels), SearchImagesActivity.this, mImageLoadingHelper);
                    mRecyclerView.setAdapter(mRecyclerViewAdapter);
                } else {
                    mRecyclerViewAdapter.removeNullObjectToDisableLoadMoreProgress(() -> {
                        mRecyclerViewAdapter.addMoreItemsAtTheBottomOfTheList(Arrays.asList(imageModels));
                    });
                }
            }
            startDownloading = false;
        });
    }

    private void onLoadMore() {
        if (mSearchImagesViewModel.mTotalItemCount < getTotalNumberOfItemsInAdapter() || mRecyclerViewAdapter == null) {
            return;
        }

        mRecyclerViewAdapter.addNullObjectToEnableLoadMoreProgress(() -> {
            startDownloading = true;
            mSearchImagesViewModel.getImagesBasedOnQueryString(false, mQuery, isInternetAvailable());
        });
    }

    private int getTotalNumberOfItemsInAdapter() {
        if (mRecyclerViewAdapter != null) {
            return mRecyclerViewAdapter.getItemCount();
        }
        return 0;
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setSearchActionBarCustomView(final ActionBar supportActionBar) {
        View customView = getLayoutInflater().inflate(R.layout.actionbar_search, null);
        supportActionBar.setCustomView(customView);

        searchView = (SearchView) customView.findViewById(R.id.search_view);
        searchView.onActionViewExpanded();

        // Enable/Disable Submit button in the keyboard
        searchView.setSubmitButtonEnabled(false);

        // Change search close button image
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.avd_ic_close_black_24dp, null);
        closeButton.setImageDrawable(vectorDrawableCompat);

        // set hint and the text colors
        @SuppressLint("CutPasteId")
        EditText txtSearch = ((EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text));
        txtSearch.setTextColor(Color.WHITE);
        txtSearch.setHint(getResources().getString(R.string.search_hint));
        txtSearch.setHintTextColor(Color.WHITE);

        //set the background with 9 patch image to display the bottom line
        searchView.setBackgroundResource(R.drawable.abc_textfield_search_default_mtrl_alpha);

        // set the cursor
        @SuppressLint("CutPasteId")
        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            //This sets the cursor resource ID to 0 or @null which will make it visible on white background
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);
        customView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(final Animation animation) {
                if (!isInternetAvailable()) {
                    searchView.clearFocus();
                }
            }

            @Override
            public void onAnimationEnd(final Animation animation) {

            }

            @Override
            public void onAnimationRepeat(final Animation animation) {

            }
        });
        setUpSearchObservable();
    }

    protected void setUpSearchObservable() {
        RxSearchObservable.fromView(searchView, this)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(query -> {
                    if (!isInternetAvailable()) {
                        //handle activity has been killed before execute this line since has time delay
                        try {
                            displayToastMessage(getResources().getString(R.string.no_internet_title_text));
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                        }
                    } else if (!TextUtils.isEmpty(query)) {
                        runOnUiThread(() -> {
                            mNoInternetConnectionView.setVisibility(View.GONE);
                            mEmptyResultLayout.setVisibility(View.GONE);
                            mSearchImageLoadingLayout.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.GONE);
                        });

                        mQuery = query.trim();
                        startDownloading = true;
                        mSearchImagesViewModel.getImagesBasedOnQueryString(true, mQuery, isInternetAvailable());
                        return true;
                    } else {
                        runOnUiThread(() -> {
                            mNoInternetConnectionView.setVisibility(View.GONE);
                            mEmptyResultLayout.setVisibility(View.VISIBLE);
                            mSearchImageLoadingLayout.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.GONE);
                        });
                    }
                    return false;
                })
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
