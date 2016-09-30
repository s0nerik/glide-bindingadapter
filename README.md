# glide-bindingadapter
[![](https://jitpack.io/v/s0nerik/glide-bindingadapter.svg)](https://jitpack.io/#s0nerik/glide-bindingadapter)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-glide--bindingadapter-green.svg?style=true)](https://android-arsenal.com/details/1/4426)

`@BindingAdapter` for loading the images with [Glide](https://github.com/bumptech/glide) without writing a single line of code using [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html).

## Usage
Register configuration providers like this:
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerGlideConfigs();
    };

    private void registerGlideConfigs() {
        GlideBindingConfig.registerProvider("config1", (iv, request) ->
            request.centerCrop()
                   .placeholder(R.color.md_black_1000)
                   .error(R.drawable.no_image)
        );
        GlideBindingConfig.registerProvider("config2", (iv, request) ->
            request.centerCrop()
                   .placeholder(iv.getDrawable())
                   .error(R.drawable.error)
        );
        GlideBindingConfig.setDefault("config1");
    }
}
```
You can use any type as a config key (I'd recommend you to use enums if you want some more safety).

Then, in your layout:
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    >
    <data>
        <variable name="song" type="project.models.Song" />
    </data>
    <ImageView
        android:id="@+id/background"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:glideImageSrc='@{song.albumArtUri}'
        app:glideConfig='@{"config2"}'
        />
</layout>
```
`app:glideImageSrc` can be either a `String` or `Uri`. 

`app:glideConfig` specifies a configuration that will be used to load the image. If not specified, the default configuration will be used.

### Installation
- Add this to your project level `build.gradle`:
```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
- Add this to your app's `build.gradle`:
```
compile 'com.github.s0nerik:glide-bindingadapter:{latest version}'
```

### License

```
Copyright 2016 Alex Isaienko (s0nerik)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
