package com.mobile.financialapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomPieChartView extends View {

    private Paint paint;
    private RectF rectF;
    private float[] values = {40f, 60f};
    private int[] colors = {Color.RED, Color.GREEN};

    public CustomPieChartView(Context context) {
        super(context);
        init();
    }

    public CustomPieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF(100, 100, 500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float startAngle = 0f;
        for (int i = 0; i < values.length; i++) {
            paint.setColor(colors[i]);
            float sweepAngle = (values[i] / 100f) * 360f;
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            startAngle += sweepAngle;
        }
    }
}

