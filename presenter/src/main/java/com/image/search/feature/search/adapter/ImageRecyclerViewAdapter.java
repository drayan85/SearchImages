package com.image.search.feature.search.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.image.search.R;
import com.image.search.databinding.SearchImageAdapterBinding;
import com.image.search.domain.model.ImageModel;
import com.image.search.utils.ImageLoadingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public final class ImageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ImageModel> mImageModelList = new ArrayList<>();
    private final ImageRecyclerViewAdapterCallback mImageRecyclerViewAdapterCallback;
    private final ImageLoadingHelper mImageLoadingHelper;
    private final Handler mHandler = new Handler();

    private static final int VIEW_PROG = 0;
    private static final int VIEW_ITEM = 1;

    public interface ImageRecyclerViewAdapterCallback {

        void onItemClick(ImageModel imageModel);
    }

    public ImageRecyclerViewAdapter(final List<ImageModel> imageModelList,
                                    final ImageRecyclerViewAdapterCallback imageRecyclerViewAdapterCallback,
                                    final ImageLoadingHelper imageLoadingHelper) {
        this.mImageModelList.addAll(imageModelList);
        this.mImageRecyclerViewAdapterCallback = imageRecyclerViewAdapterCallback;
        this.mImageLoadingHelper = imageLoadingHelper;
    }

    @Override
    public int getItemViewType(final int position) {
        return mImageModelList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == VIEW_ITEM) {
            SearchImageAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_search_image, viewGroup, false);
            viewHolder = new SearchImageViewHolder(binding);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_progress_item, viewGroup, false);
            viewHolder = new ProgressViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SearchImageViewHolder) {
            ((SearchImageViewHolder) holder).bind(holder);
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mImageModelList.size();
    }

    /**
     * Display Loading progress display while loading next pagination after user hit the bottom of the RecyclerView.
     */
    private final class ProgressViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        private ProgressViewHolder(final View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    private final class SearchImageViewHolder extends RecyclerView.ViewHolder {

        private final SearchImageAdapterBinding mSearchImageAdapterBinding;
        private final ImageView mSearchImageThumbnailImageView;

        private SearchImageViewHolder(final SearchImageAdapterBinding binding) {
            super(binding.getRoot());
            mSearchImageAdapterBinding = binding;
            mSearchImageThumbnailImageView = binding.searchImageThumbnailImageView;
        }

        private void bind(final RecyclerView.ViewHolder viewHolder) {
            ImageModel imageModel = mImageModelList.get(viewHolder.getAbsoluteAdapterPosition());
            mSearchImageAdapterBinding.setImageModel(imageModel);
            mSearchImageAdapterBinding.executePendingBindings();

            mImageLoadingHelper.load(imageModel.getThumbnail(), mSearchImageThumbnailImageView);

            mSearchImageAdapterBinding.getRoot().setOnClickListener(v -> mImageRecyclerViewAdapterCallback.onItemClick(imageModel));
        }
    }

    public interface AdapterAlterCallback {

        void onFinish();
    }

    public void addNullObjectToEnableLoadMoreProgress(final AdapterAlterCallback adapterAlterCallback) {
        Runnable r = () -> {
            mImageModelList.add(null);
            notifyItemInserted(mImageModelList.size() - 1);
            adapterAlterCallback.onFinish();
        };
        mHandler.post(r);
    }

    public void removeNullObjectToDisableLoadMoreProgress(final AdapterAlterCallback adapterAlterCallback) {
        Runnable r = () -> {
            if (mImageModelList.get(mImageModelList.size() - 1) == null) {
                mImageModelList.remove(mImageModelList.size() - 1);
                notifyItemRemoved(mImageModelList.size());
            }
            adapterAlterCallback.onFinish();
        };
        mHandler.post(r);

    }

    public void addMoreItemsAtTheBottomOfTheList(final List<ImageModel> imageModelList) {
        Runnable r = () -> {
            int start = mImageModelList.size() - 1;
            mImageModelList.addAll(imageModelList);
            notifyItemRangeInserted(start, imageModelList.size());
        };
        mHandler.post(r);
    }
}
