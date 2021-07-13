package com.image.search.di.module;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.image.search.BuildConfig;
import com.image.search.utils.JsonFormatHttLogging;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
@GlideModule
public class SearchImageGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull final Context context, final GlideBuilder builder) {
        //builder.setBitmapPool(new LruBitmapPool(GLIDE_DISK_CACHE_SIZE));
        // Size in bytes (20 MB)
        int glideDiskCacheSize = 1024 * 1024 * 20;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, glideDiskCacheSize * 2));

        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));

        // Apply options to the builder here.
        RequestOptions requestOptions = new RequestOptions();
        builder.setDefaultRequestOptions(requestOptions.format(DecodeFormat.PREFER_ARGB_8888));
    }

    @Override
    public void registerComponents(@NonNull final Context context, @NonNull final Glide glide, @NonNull final Registry registry) {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new JsonFormatHttLogging()).setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }
        OkHttpClient client = builder.build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
        //super.registerComponents(context, glide, registry);
    }
}
