package com.app.lukas.llxcodeeditor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.MultiAutoCompleteTextView;

/**
 * Created by Lukas on 10.01.2015.
 */
public class LineNumberedMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements GestureDetector.OnGestureListener{

    String Tag = "TextView";
    private Rect drawingRect, lineBounds;
    private int linePadding, padding;
    private float scale;
    private Paint paintNumbers;
    private int paddingDP = 0;
    private GestureDetector detector;


    public LineNumberedMultiAutoCompleteTextView(Context context,AttributeSet attrs) {
        super(context,attrs);
        paintNumbers = new Paint();
        paintNumbers.setTypeface(Typeface.MONOSPACE);
        paintNumbers.setAntiAlias(true);
        paintNumbers.setTextSize(getLineHeight() - 2);
        scale = context.getResources().getDisplayMetrics().density;
        padding = (int) (paddingDP * scale);
        drawingRect = new Rect();
        lineBounds = new Rect();
        detector = new GestureDetector(getContext(),this);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas){
        getDrawingRect(drawingRect);
        int count = getLineCount();
        int padding = (int) (Math.floor(Math.log10(count)) + 1);
        padding = (int) ((padding * paintNumbers.getTextSize()) + this.padding);
        if (linePadding != padding) {
            linePadding = padding;
            setPadding(linePadding, this.padding, this.padding, this.padding);
        }
        int baseline;
        int lineX = (int) (drawingRect.left + this.padding - (getLineHeight()
                * scale * 0.5));
        int min = 0;
        int max = count;
        getLineBounds(0, lineBounds);
        int startBottom = lineBounds.bottom;
        int startTop = lineBounds.top;
        getLineBounds(count - 1, lineBounds);
        int endBottom = lineBounds.bottom;
        int endTop = lineBounds.top;
        if (count > 1 && endBottom > startBottom && endTop > startTop) {
            min = Math.max(min, ((drawingRect.top - startBottom) * (count - 1)) / (endBottom - startBottom));
            max = Math.min(max, ((drawingRect.bottom - startTop) * (count - 1)) / (endTop - startTop) + 1);
        }
        int lineOverLength = 0;
        for (int i = min; i < max; i++) {
            if(i==0||getText().toString().substring(getLayout().getLineStart(i-1),getLayout().getLineEnd(i-1)).contains("\n")) {
                baseline = getLineBounds(i, lineBounds);
                canvas.drawText("" + (i + 1 - lineOverLength), drawingRect.left + this.padding,
                        baseline, paintNumbers);
                canvas.drawLine(lineX, drawingRect.top, lineX,
                        drawingRect.bottom, paintNumbers);
            }
            else lineOverLength++;
        }
        getLineBounds(count - 1, lineBounds);
        super.onDraw(canvas);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(Tag,"Down");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(Tag,"SingleTapUp");
            ((InputMethodManager) getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).showSoftInput(this,
                    InputMethodManager.SHOW_IMPLICIT);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        super.onTouchEvent(event);
        return detector == null || detector.onTouchEvent(event);
    }
}
