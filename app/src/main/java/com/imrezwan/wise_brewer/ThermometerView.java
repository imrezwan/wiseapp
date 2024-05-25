package com.imrezwan.wise_brewer;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ThermometerView extends View {

    private Path clipPath;

    public ThermometerView(Context context) {
        super(context);
        init();
    }

    public ThermometerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThermometerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // Initialize the clip path here
        clipPath = new Path();
        // Add the path to the clip path
        // Replace this with your own path coordinates
        clipPath.addRect(0, 0, 200, 200, Path.Direction.CW);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // Create a new canvas with the clip path
        canvas.clipPath(clipPath);
        // Call the super onDraw method to draw the view
        super.onDraw(canvas);
    }
}

