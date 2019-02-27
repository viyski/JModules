package com.alien.base.image;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class GlideLoader {

    public static void load(String url, ImageView imageView){
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    public static void loadBlur(String url, ImageView imageView){
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().transform(new BlurTransformation(25, 6)))
                .into(imageView);
    }

    public static void loadBlur(String url,  ImageView imageView, int sampling, int placeholder){
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                .transform(new BlurTransformation(25, sampling))
                .placeholder(placeholder))
                .into(imageView);
    }

    public static void loadRes(int resId,  ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resId)
                .apply(new RequestOptions()
                .placeholder(resId))
                .into(imageView);
    }

    public static void loadCircle(String url,  ImageView imageView, @DrawableRes int resId){
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                .circleCrop()
                .placeholder(resId))
                .into(imageView);
    }

}