package com.github.s0nerik.glide_bindingadapter;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.s0nerik.glide_bindingadapter.callbacks.ErrorCallback;
import com.github.s0nerik.glide_bindingadapter.callbacks.SuccessCallback;

import java.util.Objects;

public class GlideBindingAdapter {
    private static <T> RequestBuilder<Drawable> getDrawableRequest(
            ImageView iv,
            T oldPath,
            T newPath,
            Object oldConfigProviderKey,
            Object newConfigProviderKey,
            final SuccessCallback successCallback,
            final ErrorCallback errorCallback
    ) {
        if (!Objects.equals(newPath, oldPath) || !Objects.equals(newConfigProviderKey, oldConfigProviderKey)) {
            return Glide.with(iv.getContext()).load(newPath).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    if (errorCallback != null)
                        errorCallback.onError(new ErrorCallback.Data(e));
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (successCallback != null)
                        successCallback.onSuccess(new SuccessCallback.Data(resource));
                    return false;
                }
            });
        } else {
            return null;
        }
    }

    private static <T> void loadImage(
            ImageView iv,
            T oldPath,
            T newPath,
            Object oldConfigProviderKey,
            Object newConfigProviderKey,
            final SuccessCallback successCallback,
            final ErrorCallback errorCallback
    ) {
        RequestBuilder<Drawable> drawableRequest = getDrawableRequest(iv, oldPath, newPath, oldConfigProviderKey, newConfigProviderKey, successCallback, errorCallback);
        if (drawableRequest != null) {
            GlideBindingConfig.Provider configProvider;
            RequestBuilder<Drawable> requestBuilder;
            if ((configProvider = GlideBindingConfig.getProvider(newConfigProviderKey)) != null) {
                requestBuilder = configProvider.provide(iv, drawableRequest);
            } else if ((configProvider = GlideBindingConfig.getDefaultProvider()) != null) {
                requestBuilder = configProvider.provide(iv, drawableRequest);
            } else {
                requestBuilder = drawableRequest;
            }
            requestBuilder.into(iv);
        }
    }

    @BindingAdapter(value = {"glideImageSrc", "glideConfig", "glideOnSuccess", "glideOnError"}, requireAll = false)
    public static void setGlideImageUriSimpleCallbacks(
            ImageView iv,
            Uri oldUri,
            Object oldGlideConfigProviderKey,
            SuccessCallback oldSuccessCallback,
            ErrorCallback oldErrorCallback,
            Uri newUri,
            Object newGlideConfigProviderKey,
            SuccessCallback newSuccessCallback,
            ErrorCallback newErrorCallback
    ) {
        loadImage(iv, oldUri, newUri, oldGlideConfigProviderKey, newGlideConfigProviderKey, newSuccessCallback, newErrorCallback);
    }

    @BindingConversion
    public static Uri convertUrlToUri(String url) {
        if (url == null || url.isEmpty())
            return Uri.EMPTY;
        return Uri.parse(url);
    }

    @BindingConversion
    public static SuccessCallback convertNoArgsSuccessCallback(final Runnable runnable) {
        return new SuccessCallback() {
            @Override
            public void onSuccess(Data data) {
                runnable.run();
            }
        };
    }

    @BindingConversion
    public static ErrorCallback convertNoArgsErrorCallback(final Runnable runnable) {
        return new ErrorCallback() {
            @Override
            public void onError(Data data) {
                runnable.run();
            }
        };
    }
}
