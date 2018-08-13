package com.github.s0nerik.glide_bindingadapter.callbacks;

import android.graphics.drawable.Drawable;

/**
 * Created by Alex Isaienko on 11/28/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
public interface SuccessCallback {
    class Data {
        final Drawable drawable;

        public Data(Drawable drawable) {
            this.drawable = drawable;
        }
    }

    void onSuccess(Data data);
}