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
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by nickkpoon on 10/23/17.
 */

public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

    Paint drawPaint;
    Paint canvasPaint;
    Canvas drawCanvas;
    Bitmap bmp;

    ArrayList<Path> paths;
    ArrayList<Integer> pointers;

    boolean surfaceExists = false;

    static String firstPaint;
    static String secondPaint;
    static String thirdPaint;
    static String fourthPaint;

    int count = -1;



    public CanvasView(Context context) {
        super(context);
        //setupDrawing();
        paths = new ArrayList<>();
        pointers = new ArrayList<>();

        drawPaint = new Paint();
        drawPaint.setColor(Color.RED);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
        getHolder().addCallback(this);
        setWillNotDraw(false);


    }

    public CanvasView(Context context, String paint1, String paint2, String paint3, String paint4) {
        super(context);
        //setupDrawing();
        paths = new ArrayList<>();
        pointers = new ArrayList<>();

        drawPaint = new Paint();
        drawPaint.setColor(Color.RED);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setAntiAlias(true);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
        firstPaint = paint1;
        secondPaint = paint2;
        thirdPaint = paint3;
        fourthPaint = paint4;
        getHolder().addCallback(this);
        setWillNotDraw(false);

        /*final Button button0 = findViewById(R.id.button);
        final Button button1 = findViewById(R.id.button2);
        final Button button2 = findViewById(R.id.button3);
        final Button button3 = findViewById(R.id.button4);

        button0.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                firstPaint = MainActivity.retrieveColor(1);
                button0.setBackgroundColor(Color.parseColor(firstPaint));
            }
        });


        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                secondPaint = MainActivity.retrieveColor(1);
                button1.setBackgroundColor(Color.parseColor(secondPaint));
            }
        });


        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                thirdPaint = MainActivity.retrieveColor(1);
                button2.setBackgroundColor(Color.parseColor(thirdPaint));
            }
        });


        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fourthPaint = MainActivity.retrieveColor(1);
                button3.setBackgroundColor(Color.parseColor(fourthPaint));
            }
        });*/


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
        drawCanvas = new Canvas(bmp);
        drawCanvas.drawColor(Color.WHITE);

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
        //drawCanvas = new Canvas(bmp);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceExists = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmp, 0, 0, canvasPaint);
        for (int i=0; i<paths.size(); i++)
        {
            switch(i)
            {
                case(0):
                    drawPaint.setColor(Color.parseColor(firstPaint));
                    //canvas.drawPath(paths.get(i), drawPaint);
                    break;
                case(1):
                    drawPaint.setColor(Color.parseColor(secondPaint));
                    //canvas.drawPath(paths.get(i), drawPaint);
                    break;
                case(2):
                    drawPaint.setColor(Color.parseColor(thirdPaint));
                    //canvas.drawPath(paths.get(i), drawPaint);
                    break;
                case(3):
                    drawPaint.setColor(Color.parseColor(fourthPaint));
                    //anvas.drawPath(paths.get(i), drawPaint);
                    break;
                default:
                    drawPaint.setColor(Color.parseColor("#000000"));
                    //canvas.drawPath(paths.get(i), drawPaint);
                    break;
            }
            //drawPaint.setColor(Color.parseColor("#000000"));

            canvas.drawPath(paths.get(i), drawPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        /*switch(index)
        {
            case(0):
                drawPaint.setColor(Color.parseColor(firstPaint));
                break;
            case(1):
                drawPaint.setColor(Color.parseColor(secondPaint));
                break;
            case(2):
                drawPaint.setColor(Color.parseColor(thirdPaint));
                break;
            case(3):
                drawPaint.setColor(Color.parseColor(fourthPaint));
                break;
            default:
                drawPaint.setColor(Color.parseColor("#000000"));
                break;
        }*/
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

                    /*switch(indexOfId)
                    {
                        case(0):
                            drawPaint.setColor(Color.parseColor(firstPaint));
                            //canvas.drawPath(paths.get(i), drawPaint);
                            break;
                        case(1):
                            drawPaint.setColor(Color.parseColor(secondPaint));
                            //canvas.drawPath(paths.get(i), drawPaint);
                            break;
                        case(2):
                            drawPaint.setColor(Color.parseColor(thirdPaint));
                            //canvas.drawPath(paths.get(i), drawPaint);
                            break;
                        case(3):
                            drawPaint.setColor(Color.parseColor(fourthPaint));
                            //canvas.drawPath(paths.get(i), drawPaint);
                            break;
                        default:
                            drawPaint.setColor(Color.parseColor("#000000"));
                            //canvas.drawPath(paths.get(i), drawPaint);
                            break;
                    }*/


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
        refreshView();
        invalidate();
        return true;
    }

}