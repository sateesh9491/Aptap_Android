package com.app.aptap.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by aditya on 8/31/2017.
 */

public class CustomEditText extends EditText {

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        /*if (!isInEditMode()) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/minaeff_ect_bold.ttf"));
        }*/
    }

}