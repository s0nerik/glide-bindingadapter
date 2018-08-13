package com.github.s0nerik.glide_bindingadapter.sample;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.s0nerik.glide_bindingadapter.GlideBindingConfig;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * Created by Alex Isaienko on 8/13/18.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
public enum GlideConfig {
    CONFIG_1(
            (target, request) -> request.apply(RequestOptions.centerCropTransform())
    ),

    CONFIG_2(
            (target, request) -> request.apply(
                    RequestOptions.bitmapTransform(new MultiTransformation<>(
                                    new CenterCrop(),
                                    new GrayscaleTransformation()
                            )
                    )
            )
    ),

    CONFIG_3(
            (target, request) -> request.apply(
                    RequestOptions.bitmapTransform(new MultiTransformation<>(
                                    new CenterCrop(),
                                    new BlurTransformation()
                            )
                    )
            )
    );

    public final GlideBindingConfig.Provider provider;

    GlideConfig(GlideBindingConfig.Provider provider) {
        this.provider = provider;
    };
}
