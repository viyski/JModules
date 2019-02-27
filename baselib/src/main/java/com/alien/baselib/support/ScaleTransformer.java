package com.alien.baselib.support;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ScaleTransformer implements ViewPager.PageTransformer {

    private float SCALE_MAX = 0.90f;
    private float ALPHA_MAX = 0.85f;

    @Override
    public void transformPage(View page, float position) {
        float scale = position < 0 ? (1f - SCALE_MAX) * position + 1f: (SCALE_MAX - 1f) * position + 1f;
        float alpha = position < 0 ? (1f - ALPHA_MAX) * position + 1f : (ALPHA_MAX - 1f) * position + 1f;

        if (position < 0) {
            page.setPivotX(page.getWidth());
            page.setPivotY(page.getHeight() / 2.f);
        } else {
            page.setPivotX(0);
            page.setPivotY(page.getHeight() / 2.f);
        }
        page.setScaleX(scale);
        page.setScaleY(scale);
        page.setAlpha(Math.abs(alpha));
    }
}