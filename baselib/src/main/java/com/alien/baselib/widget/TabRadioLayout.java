package com.alien.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alien.baselib.R;

public class TabRadioLayout extends RadioGroup {

    private int radioType = 0;
    private float drawablePadding = 0f;
    private int color = 0x000000;
    private int textSize = 12;
    private  SparseArray<Integer> sparseArray = new SparseArray();

    public TabRadioLayout(Context context) {
        this(context, null);
    }

    public TabRadioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.TabRadioLayout);

        setOrientation(HORIZONTAL);
        radioType = ty.getInt(R.styleable.TabRadioLayout_tab_type, 0);
        drawablePadding = ty.getDimension(R.styleable.TabRadioLayout_tab_drawable_padding, 0f);
        color = ty.getResourceId(R.styleable.TabRadioLayout_tab_text_color, android.R.color.black);
        textSize = ty.getDimensionPixelOffset(R.styleable.TabRadioLayout_tab_text_size, 12);
        ty.recycle();
    }

    public void addTab(Tab tab){
        sparseArray.put(tab.getId(), getChildCount());
        addView(tab);
    }

    public Tab newTab() {
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1f;

        Tab button = new Tab(getContext());
        button.setButtonDrawable(null);
        button.drawablePadding = (int) drawablePadding;
        button.type = radioType;
        button.setGravity(Gravity.CENTER);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        button.setTextColor(getResources().getColorStateList(color));
        button.setLayoutParams(params);
        button.setId(R.id.tab_id + getChildCount());
        return button;
    }

    public void addOnTabSelectedListener(ViewPagerOnTabSelectedListener listener){
        setOnCheckedChangeListener(listener);
        ((RadioButton)getChildAt(0)).setChecked(true);
    }

    public void onPageSelected(int position){
        int index = sparseArray.indexOfValue(position);
        int checkId = sparseArray.keyAt(index);
        if(findViewById(checkId) != null){
            RadioButton button = findViewById(checkId);
            button.setChecked(true);
        }
    }

    public static class ViewPagerOnTabSelectedListener implements RadioGroup.OnCheckedChangeListener{

        private ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager){
            this.viewPager = viewPager;
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            TabRadioLayout tabLayout = (TabRadioLayout) group;
            viewPager.setCurrentItem(tabLayout.sparseArray.get(checkedId, 0));
        }
    }

    public class Tab extends AppCompatRadioButton {

        private int type;
        private int drawablePadding;

        public Tab(Context context) {
            this(context, null);
        }

        public Tab(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public Tab setIcon(int resId){
            setCompoundDrawablePadding(drawablePadding);
            if (type == 0) {
                setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
            }else{
                setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
            }
            return this;
        }

        public Tab setTabText(String text){
            setText(text);
            return this;
        }

        public Tab setTabText(int res){
            setText(res);
            return this;
        }
    }
}
