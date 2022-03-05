package com.akuya.Android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Mytestview extends TextView{
    public Mytestview(Context context) {
        super(context);
    }

    public Mytestview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Mytestview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
