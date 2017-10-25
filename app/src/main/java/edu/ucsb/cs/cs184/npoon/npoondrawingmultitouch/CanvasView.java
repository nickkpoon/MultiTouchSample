package edu.ucsb.cs.cs184.npoon.npoondrawingmultitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.util.ArrayList;

/**
 * Created by nickkpoon on 10/23/17.
 */

public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

    Canvas myCanvas;
    Bitmap bmp;

    ArrayList<Path> paths;
    ArrayList<Integer> pointers;
    ArrayList<String> brushColors;

    Paint paintBrush;
    Paint cPaint;

    boolean surfaceExists = false;

    static String firstPaint;
    static String secondPaint;
    static String thirdPaint;
    static String fourthPaint;

    public CanvasView(Context context) {
        super(context);
        paths = new ArrayList<>();
        pointers = new ArrayList<>();
        brushColors = new ArrayList<>();

        paintBrush = new Paint();
        paintBrush.setColor(Color.RED);
        paintBrush.setStrokeWidth(20);
        paintBrush.setStyle(Paint.Style.STROKE);
        paintBrush.setAntiAlias(true);

        cPaint = new Paint(Paint.DITHER_FLAG);
        getHolder().addCallback(this);
        setWillNotDraw(false);


    }

    public CanvasView(Context context, String paint1, String paint2, String paint3, String paint4) {
        super(context);
        paths = new ArrayList<>();
        pointers = new ArrayList<>();
        brushColors = new ArrayList<>();

        paintBrush = new Paint();
        paintBrush.setColor(Color.RED);
        paintBrush.setStrokeWidth(20);
        paintBrush.setStyle(Paint.Style.STROKE);
        paintBrush.setAntiAlias(true);

        cPaint = new Paint(Paint.DITHER_FLAG);
        firstPaint = paint1;
        secondPaint = paint2;
        thirdPaint = paint3;
        fourthPaint = paint4;
        getHolder().addCallback(this);
        setWillNotDraw(false);
    }

    public static void setColor(int i, String color){

        switch(i)
        {
            case(0):
                firstPaint = color;
                break;
            case(1):
                secondPaint = color;
                break;
            case(2):
                thirdPaint = color;
                break;
            case(3):
                fourthPaint = color;
                break;
        }
    }


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
        myCanvas = new Canvas(bmp);
        myCanvas.drawColor(Color.WHITE);

        refreshView();
    }

    public void refreshView() {
        if(!surfaceExists) return;

        // Draw the painting bitmap to the SurfaceView
        Canvas surfaceCanvas = getHolder().lockCanvas();
        // surfaceCanvas is only valid between lockCanvas and unlockCanvasAndPost
        surfaceCanvas.drawBitmap(bmp, 0, 0, null);
        getHolder().unlockCanvasAndPost(surfaceCanvas);
        // unlockCanvasAndPost will show the SurfaceView. invalidate() is not necessary here.
        // invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceExists = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmp, 0, 0, cPaint);
        for (int i=0; i<paths.size(); i++)
        {
            switch(i)
            {
                case(0):
                    paintBrush.setColor(Color.parseColor(brushColors.get(i)));
                    break;
                case(1):
                    paintBrush.setColor(Color.parseColor(secondPaint));
                    break;
                case(2):
                    paintBrush.setColor(Color.parseColor(thirdPaint));
                    break;
                case(3):
                    paintBrush.setColor(Color.parseColor(fourthPaint));
                    break;
                default:
                    paintBrush.setColor(Color.parseColor("#000000"));
                    break;
            }
            //paintBrush.setColor(Color.parseColor("#000000"));
            //paintBrush.setColor(Color.parseColor(brushColors.get(i)));
            canvas.drawPath(paths.get(i), paintBrush);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        int count = 0;
        Path path;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //count++;
                path = new Path();
                path.moveTo(event.getX(index), event.getY(index));
                paths.add(index, path);
                pointers.add(index, id);

                switch(count)
                {
                    case(0):
                        paintBrush.setColor(Color.parseColor(firstPaint));
                        brushColors.add(index, firstPaint);
                        break;
                    case(1):
                        paintBrush.setColor(Color.parseColor(secondPaint));
                        brushColors.add(index, secondPaint);
                        break;
                    case(2):
                        paintBrush.setColor(Color.parseColor(thirdPaint));
                        brushColors.add(index, thirdPaint);
                        break;
                    case(3):
                        paintBrush.setColor(Color.parseColor(fourthPaint));
                        brushColors.add(index, fourthPaint);
                        break;
                    default:
                        paintBrush.setColor(Color.parseColor("#000000"));
                        brushColors.add(index, "#000000");
                        break;
                }
                count++;
                break;

            case MotionEvent.ACTION_MOVE:
                int indexOfId;
                for (int i=0; i<paths.size(); i++)
                {
                    id = event.getPointerId(i);
                    indexOfId = pointers.indexOf(id);
                    path = paths.get(indexOfId);
                    if (path != null)
                        path.lineTo(event.getX(i), event.getY(i));
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                /*HAVE MAJOR TROUBLE GETTING THIS WORKING TO RELEASE COLORS */
                //TODO: fix color switching on release
                paintBrush.setColor(Color.parseColor(brushColors.get(count)));

                indexOfId = pointers.indexOf(id);
                //path = paths.get(indexOfId);
                path = paths.get(count);


                if (path != null)
                {
                    //paintBrush.setColor(Color.parseColor("#000000"));
                    myCanvas.drawPath(path, paintBrush);
                    //paintBrush.setColor(Color.parseColor(brushColors.get(indexOfId)));
                    paths.remove(path);
                    pointers.remove(indexOfId);
                    brushColors.remove(count);
                    count--;
                }
                break;
            default:
                return false;
        }
        refreshView();
        invalidate();
        return true;
    }

}