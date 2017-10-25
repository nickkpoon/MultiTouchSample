package edu.ucsb.cs.cs184.npoon.npoondrawingmultitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by nickkpoon on 10/23/17.
 */

public class CanvasView extends View implements SurfaceHolder.Callback {

    private Paint drawPaint, canvasPaint;
    private Canvas drawCanvas;
    private Bitmap bmp;

    private ArrayList<Path> paths;
    private ArrayList<Integer> pointers;

    boolean surfaceExists = false;


    public CanvasView(Context context) {
        super(context);
        //setupDrawing();
        paths = new ArrayList<>();
        pointers = new ArrayList<>();

        drawPaint = new Paint();
        drawPaint.setColor(Color.RED);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);

        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }


    /*private void setupDrawing() {
        paths = new ArrayList<>();
        pointers = new ArrayList<>();

        drawPaint = new Paint();
        drawPaint.setColor(Color.RED);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }*/

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        surfaceExists = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        // Create our bitmap
        bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // Let painting canvas draw to the bitmap
        drawCanvas = new Canvas(bmp);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(bmp);
        //onDraw(drawCanvas);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceExists = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmp, 0, 0, canvasPaint);
        for (int i=0; i<paths.size(); i++) {
            canvas.drawPath(paths.get(i), drawPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        Path path;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                path = new Path();
                path.moveTo(event.getX(index), event.getY(index));
                paths.add(index, path);
                pointers.add(index, id);
                break;

            case MotionEvent.ACTION_MOVE:
                int indexOfId;
                for (int i=0; i<paths.size(); i++)
                {
                    id = event.getPointerId(i);
                    indexOfId = pointers.indexOf(id);
                    path = paths.get(indexOfId);
                    if (path != null) path.lineTo(event.getX(i), event.getY(i));
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                indexOfId = pointers.indexOf(id);
                path = paths.get(indexOfId);
                if (path != null)
                {
                    drawCanvas.drawPath(path, drawPaint);
                    paths.remove(path);
                    pointers.remove(indexOfId);
                }
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

}