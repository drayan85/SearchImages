package com.image.search.model.mapper;

import androidx.annotation.NonNull;

import com.image.search.domain.model.ImageModel;
import com.image.search.model.ImageModelParcelable;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class ImageModelParcelableMapper {

    public ImageModelParcelable transForm(@NonNull ImageModel imageModel) {
        ImageModelParcelable imageModelParcelable = new ImageModelParcelable();
        imageModelParcelable.setUrl(imageModel.getUrl());
        imageModelParcelable.setHeight(imageModel.getHeight());
        imageModelParcelable.setWidth(imageModel.getWidth());
        imageModelParcelable.setThumbnail(imageModel.getThumbnail());
        imageModelParcelable.setThumbnailHeight(imageModel.getThumbnailHeight());
        imageModelParcelable.setThumbnailWidth(imageModel.getThumbnailWidth());
        imageModelParcelable.setName(imageModel.getName());
        imageModelParcelable.setTitle(imageModel.getTitle());
        imageModelParcelable.setProvider(imageModel.getProvider());
        imageModelParcelable.setImageWebSearchUrl(imageModel.getImageWebSearchUrl());
        imageModelParcelable.setWebpageUrl(imageModel.getWebpageUrl());
        return imageModelParcelable;
    }
}
