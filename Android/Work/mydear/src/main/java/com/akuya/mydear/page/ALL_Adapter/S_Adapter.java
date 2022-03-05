package com.akuya.mydear.page.ALL_Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class S_Adapter extends PagerAdapter {

    private List<View> mlistview;

    public S_Adapter(List<View> mlistview) {
        this.mlistview = mlistview;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mlistview.get(position),0);
        return mlistview.get(position);
    }

    @Override
    public int getCount() {
        return mlistview.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((mlistview.get(position)));
    }
}
