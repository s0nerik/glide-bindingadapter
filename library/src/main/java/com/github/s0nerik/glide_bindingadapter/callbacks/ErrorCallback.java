package com.github.s0nerik.glide_bindingadapter.callbacks;

import com.bumptech.glide.load.engine.GlideException;

/**
 * Created by Alex Isaienko on 11/28/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
public interface ErrorCallback {
    class Data {
        final GlideException exception;

        public Data(GlideException exception) {
            this.exception = exception;
        }
    }

    void onError(Data data);
}