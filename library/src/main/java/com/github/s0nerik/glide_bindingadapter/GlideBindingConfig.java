package com.github.s0nerik.glide_bindingadapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

public class GlideBindingConfig {
    public interface Provider {
        @NonNull
        RequestBuilder<Drawable> provide(ImageView target, RequestBuilder<Drawable> request);
    }

    private static Map<Object, Provider> providers = new HashMap<>();
    private static Provider defaultProvider = null;

    static Provider getProvider(Object key) {
        return providers.get(key);
    }

    static Provider getDefaultProvider() {
        return defaultProvider;
    }

    public static void registerProvider(Object key, Provider provider) {
        providers.put(key, provider);
    }

    public static void setDefault(Object key) {
        defaultProvider = providers.get(key);
    }
}
