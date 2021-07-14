package com.image.search.data.database.entity.mapper;

import com.google.gson.Gson;
import com.image.search.data.database.entity.ImageModelEntity;
import com.image.search.domain.model.ImageModel;
import com.image.search.domain.model.Provider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class ImageModelEntityMapper {

    Gson mGson = new Gson();

    public ImageModel transForm(ImageModelEntity entity) {
        ImageModel imageModel = null;
        if (entity != null) {
            imageModel = new ImageModel();
            imageModel.setUrl(entity.getUrl());
            imageModel.setHeight(entity.getHeight());
            imageModel.setWidth(entity.getWidth());
            imageModel.setThumbnail(entity.getThumbnail());
            imageModel.setThumbnailHeight(entity.getThumbnailHeight());
            imageModel.setThumbnailWidth(entity.getThumbnailWidth());
            imageModel.setName(entity.getName());
            imageModel.setTitle(entity.getTitle());
            imageModel.setProvider(mGson.fromJson(entity.getProvider(), Provider.class));
            imageModel.setImageWebSearchUrl(entity.getImageWebSearchUrl());
            imageModel.setWebpageUrl(entity.getWebpageUrl());
        }
        return imageModel;
    }

    public ImageModelEntity transForm(ImageModel imageModel) {
        ImageModelEntity imageModelEntity = null;
        if (imageModel != null) {
            imageModelEntity = new ImageModelEntity();
            imageModelEntity.setUrl(imageModel.getUrl());
            imageModelEntity.setHeight(imageModel.getHeight());
            imageModelEntity.setWidth(imageModel.getWidth());
            imageModelEntity.setThumbnail(imageModel.getThumbnail());
            imageModelEntity.setThumbnailHeight(imageModel.getThumbnailHeight());
            imageModelEntity.setThumbnailWidth(imageModel.getThumbnailWidth());
            imageModelEntity.setName(imageModel.getName());
            imageModelEntity.setTitle(imageModel.getTitle());
            imageModelEntity.setProvider(mGson.toJson(imageModel.getProvider()));
            imageModelEntity.setImageWebSearchUrl(imageModel.getImageWebSearchUrl());
            imageModelEntity.setWebpageUrl(imageModel.getWebpageUrl());
        }
        return imageModelEntity;
    }

    public ImageModel[] transForm(ImageModelEntity[] imageModelEntities) {
        List<ImageModel> imageModelList = new ArrayList<>();
        ImageModel imageModel;
        for (ImageModelEntity entity : imageModelEntities) {
            imageModel = transForm(entity);
            if (imageModel != null) {
                imageModelList.add(imageModel);
            }
        }
        return imageModelList.toArray(new ImageModel[0]);
    }

    public ImageModelEntity[] transForm(ImageModel[] imageModels) {
        List<ImageModelEntity> modelEntityArrayList = new ArrayList<>();
        ImageModelEntity imageModelEntity;
        for (ImageModel imageModel : imageModels) {
            imageModelEntity = transForm(imageModel);
            if (imageModelEntity != null) {
                modelEntityArrayList.add(imageModelEntity);
            }
        }
        return modelEntityArrayList.toArray(new ImageModelEntity[0]);
    }
}
