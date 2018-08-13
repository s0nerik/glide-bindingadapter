package com.github.s0nerik.glide_bindingadapter.sample;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.github.s0nerik.glide_bindingadapter.sample.databinding.ActivityMainBinding;

import java.util.Random;

/**
 * Created by Alex Isaienko on 8/13/18.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
public class MainActivity extends FragmentActivity {
    private final String[] images = {
            "https://picsum.photos/200/300?image=1",
            "https://picsum.photos/200/300?image=2",
            "https://picsum.photos/200/300?image=3",
    };

    private MainViewModel vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setVariable(BR.vm, vm);

        changeImage(0, 0);
    }

    private void changeImage(int imgIndex, int cfgIndex) {
        vm.imageSrc.set(images[imgIndex % images.length]);
        vm.glideConfig.set(GlideConfig.values()[cfgIndex % GlideConfig.values().length]);

        Random rnd = new Random(System.currentTimeMillis());

        new Handler().postDelayed(() -> changeImage(imgIndex + rnd.nextInt(10), cfgIndex + rnd.nextInt(10)), 3000);
    }
}