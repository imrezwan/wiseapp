package com.imrezwan.wise_brewer.widgets;

import static android.view.Gravity.CENTER;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout.LayoutParams;

import com.imrezwan.wise_brewer.R;

public class TitleView extends androidx.appcompat.widget.AppCompatTextView {

    public TitleView(Context context) {
        super(context);
        init(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        layoutParams.bottomMargin = 15;
        setLayoutParams(layoutParams);
        setTypeface(getTypeface(), Typeface.BOLD);
        setTextColor(getResources().getColor(R.color.colorWhite));
        setGravity(CENTER);
        setTextSize(32);
        setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));
        setBackgroundResource(R.drawable.title_bg);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
            String title = typedArray.getString(R.styleable.TitleView_title);
            typedArray.recycle();

            setText(title);
        }
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
