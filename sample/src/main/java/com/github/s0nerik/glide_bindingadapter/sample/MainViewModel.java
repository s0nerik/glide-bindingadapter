package com.github.s0nerik.glide_bindingadapter.sample;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

/**
 * Created by Alex Isaienko on 8/13/18.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
public class MainViewModel extends ViewModel {
    public final ObservableField<GlideConfig> glideConfig = new ObservableField<>(GlideConfig.CONFIG_1);
    public final ObservableField<String> imageSrc = new ObservableField<>("https://picsum.photos/200/300?image=0");
}
