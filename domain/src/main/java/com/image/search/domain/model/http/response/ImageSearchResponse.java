package com.image.search.domain.model.http.response;

import com.image.search.domain.model.ImageModel;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class ImageSearchResponse {

    private String _type;
    private int totalCount;
    private ImageModel[] value;

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ImageModel[] getValue() {
        return value;
    }

    public void setValue(ImageModel[] value) {
        this.value = value;
    }
}
