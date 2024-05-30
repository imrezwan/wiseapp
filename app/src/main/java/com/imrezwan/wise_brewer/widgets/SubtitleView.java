package com.imrezwan.wise_brewer.widgets;

import static android.view.Gravity.CENTER;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.imrezwan.wise_brewer.R;

public class SubtitleView extends androidx.appcompat.widget.AppCompatTextView {

    public SubtitleView(Context context) {
        super(context);
        init(context, null);
    }

    public SubtitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SubtitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.bottomMargin = 15;
        setLayoutParams(layoutParams);
        setTypeface(getTypeface(), Typeface.BOLD);
        setTextColor(getResources().getColor(R.color.colorWhite));
        setGravity(CENTER);
        setTextSize(20);
        setPadding(dpToPx(12), dpToPx(5), dpToPx(12), dpToPx(5));
        setBackgroundResource(R.drawable.subtitle_bg);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SubtitleView);
            String title = typedArray.getString(R.styleable.SubtitleView_subtitle);
            typedArray.recycle();

            setText(title);
        }
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
