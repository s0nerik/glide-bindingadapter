package com.github.s0nerik.glide_bindingadapter.sample;

import android.app.Application;

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
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        for (GlideConfig cfg : GlideConfig.values()) {
            GlideBindingConfig.registerProvider(cfg, cfg.provider);
        }
    }
}
